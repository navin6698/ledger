package com.ledger.service.impl;

import com.ledger.constants.Constants;
import com.ledger.models.LoanAccount;
import com.ledger.models.Loan;
import com.ledger.service.LedgerService;
import com.ledger.util.LedgerUtil;

import java.util.Map;

public class LoanServiceImpl implements LedgerService {

    /*
        @param data[] : LOAN IDIDI Dale 5000 1 6
        @param loanAccounts : holds loan information
        @returns void
     */

    @Override
    public void processData(String[] data, Map<Integer, LoanAccount> loanAccounts) {
        Loan loan = getLoan(data);
        LoanAccount loanAccount = createLoanAccount(loan);
        int key = LedgerUtil.hashcode(loan.getBankName() , loan.getBorrowerName());
        loanAccounts.put(key,loanAccount);
    }

    /*
        Creates loan object
        @param data[] : LOAN IDIDI Dale 5000 1 6
        @returns Loan
     */
    private Loan getLoan(String[] data) {
        return Loan.builder()
                .bankName(data[1])
                .borrowerName(data[2])
                .principal(LedgerUtil.getIntValue(data[3]))
                .numOfYears(LedgerUtil.getIntValue(data[4]))
                .interest(LedgerUtil.getIntValue(data[5]))
                .build();
    }

    private LoanAccount createLoanAccount(Loan loan) {
        return LoanAccount.builder()
                .loan(loan)
                .remainingAmount(LedgerUtil.totalAmount(loan))
                .totalAmount(LedgerUtil.totalAmount(loan))
                .noOfEMI(LedgerUtil.noOfEMI(loan.getNumOfYears()))
                .noOfEMIRemaining(LedgerUtil.noOfEMI(loan.getNumOfYears()))
                .monthlyPayment(LedgerUtil.getMonthlyPayment(loan))
                .lastEMIPaid(Constants.ZERO)
                .statement(LedgerUtil.getStatement(loan))
                .build();
    }
}
