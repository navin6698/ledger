package com.ledger.service.impl;

import com.ledger.models.LoanAccount;
import com.ledger.models.AccountBalance;
import com.ledger.models.Balance;
import com.ledger.service.LedgerService;
import com.ledger.util.LedgerUtil;

import java.util.Map;

public class BalanceServiceImpl implements LedgerService {

    @Override
    public void processData(String[] data, Map<Integer, LoanAccount> loanAccounts) {
        Balance balance = getBalance(data);
        int key = LedgerUtil.hashcode(balance.getBankName() , balance.getBorrowerName());
        LoanAccount loanAccount = loanAccounts.get(key);
        updateEMI(loanAccount , balance.getEmiNo());
        printBalance(loanAccount , balance);
    }

    private Balance getBalance(String[] data) {
        return Balance.builder()
                .bankName(data[1])
                .borrowerName(data[2])
                .emiNo(LedgerUtil.getIntValue(data[3]))
                .build();
    }

    private void printBalance(LoanAccount loanAccount , Balance balance) {
            AccountBalance accountBalance = loanAccount.getStatement().get(balance.getEmiNo());
            System.out.println(balance.getBankName() + " " + balance.getBorrowerName() + " " +
                    accountBalance.getAmountPaid() + " " + accountBalance.getRemainingEMI());
    }

    private void updateEMI(LoanAccount loanAccount, int emiNo) {
        Map<Integer,AccountBalance> statement = loanAccount.getStatement();
        for(int i = loanAccount.getLastEMIPaid(); i < emiNo; i++) {
            int amountPaid = (statement.get(i) == null ? 0 : (statement.get(i).getAmountPaid()) +
                    loanAccount.getMonthlyPayment());
            int remainingEMI = statement.get(i) == null ? loanAccount.getNoOfEMIRemaining() : (statement.get(i).getRemainingEMI() - 1);

            AccountBalance balance = AccountBalance.builder()
                    .amountPaid(amountPaid)
                    .remainingEMI(remainingEMI)
                    .build();
            statement.put((i+1) , balance);
        }
    }
}

