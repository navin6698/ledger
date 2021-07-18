package com.ledger.service.impl;

import com.ledger.models.LoanAccount;
import com.ledger.models.AccountBalance;
import com.ledger.models.Payment;
import com.ledger.service.LedgerService;
import com.ledger.util.LedgerUtil;

import java.util.Map;

public class PaymentServiceImpl implements LedgerService {
    @Override
    public void processData(String[] data, Map<Integer, LoanAccount> loanAccounts) {
        Payment payment = getPayment(data);
        int key = LedgerUtil.hashcode(payment.getBankName() , payment.getBorrowerName());
        LoanAccount loanAccount = loanAccounts.get(key);
        updateEMI(loanAccount , payment);
        updateLoanAccount(loanAccount , payment);
    }

    private Payment getPayment(String[] data) {
       return Payment.builder()
               .bankName(data[1])
               .borrowerName(data[2])
               .lumpSumAmount(LedgerUtil.getIntValue(data[3]))
               .emiNo(LedgerUtil.getIntValue(data[4]))
               .build();
    }

    private void updateLoanAccount(LoanAccount loanAccount , Payment payment) {
        AccountBalance accountBalance = loanAccount.getStatement().get(payment.getEmiNo());
        int amountPaidSoFar =  accountBalance.getAmountPaid();

        int remainingAmount = loanAccount.getRemainingAmount() - amountPaidSoFar;
        remainingAmount = remainingAmount - payment.getLumpSumAmount();
        loanAccount.setRemainingAmount(remainingAmount);

        int remainingEMI = (int) Math.ceil((double)remainingAmount / loanAccount.getMonthlyPayment());
        loanAccount.setNoOfEMIRemaining(remainingEMI);
        loanAccount.setLastEMIPaid(payment.getEmiNo());

        accountBalance.setAmountPaid(amountPaidSoFar + payment.getLumpSumAmount());
        accountBalance.setRemainingEMI(remainingEMI);
    }

    private void updateEMI(LoanAccount loanAccount , Payment payment) {
        Map<Integer,AccountBalance> statement = loanAccount.getStatement();
        for(int i = loanAccount.getLastEMIPaid(); i < payment.getEmiNo(); i++) {
            int amountPaid = (statement.get(i) == null ? 0 : (statement.get(i).getAmountPaid()) +
                    loanAccount.getMonthlyPayment());
            int remainingEMI = loanAccount.getNoOfEMIRemaining() - (i+1);

            AccountBalance balance = AccountBalance.builder()
                    .amountPaid(amountPaid)
                    .remainingEMI(remainingEMI)
                    .build();
            statement.put((i+1) , balance);
        }
    }
 }
