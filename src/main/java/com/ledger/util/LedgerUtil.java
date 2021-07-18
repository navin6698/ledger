package com.ledger.util;

import com.ledger.models.AccountBalance;
import com.ledger.models.Loan;

import java.util.HashMap;
import java.util.Map;

public class LedgerUtil {

    public static int hashcode(String bankName, String borrowerName) {
        return (bankName + borrowerName).hashCode();
    }

    public static int getIntValue(String value) {
        return Integer.parseInt(value);
    }

    public static int totalAmount(Loan loan) {
        return (int) loan.getPrincipal()  + interestAmount(loan);
    }

    public static int interestAmount(Loan loan) {
        return (int) Math.ceil((loan.getPrincipal() * loan.getNumOfYears() *((float)loan.getInterest() / 100)));
    }

    public static int noOfEMI(int numOfYears) {
        return numOfYears * 12;
    }

    public static int getMonthlyPayment(Loan loan) {
        return (int) Math.ceil((double)totalAmount(loan) / noOfEMI(loan.getNumOfYears()));
    }

    public static Map<Integer, AccountBalance> getStatement(Loan loan) {
        AccountBalance accountBalance = AccountBalance.builder()
                .amountPaid(0)
                .remainingEMI(noOfEMI(loan.getNumOfYears()))
                .build();
        Map<Integer,AccountBalance> statement = new HashMap<>();
        statement.put(0,accountBalance);
        return statement;
    }
}
