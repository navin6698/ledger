package com.ledger.service;

import com.ledger.constants.Constants;
import com.ledger.service.impl.BalanceServiceImpl;
import com.ledger.service.impl.LoanServiceImpl;
import com.ledger.service.impl.PaymentServiceImpl;

public class LedgerFactory {

    /*
       @param commandType : LOAN, PAYMENT, BALANCE
       @Return LedgerService : Its an Interface which has implementations for each type of command,
               returns null if command type is incorrect
    */
    public static LedgerService getLedgerService(String commandType) {
        switch (commandType) {
            case Constants.LOAN:
                return new LoanServiceImpl();
            case Constants.PAYMENT:
                return new PaymentServiceImpl();
            case Constants.BALANCE:
                return new BalanceServiceImpl();
            default:
                return null;
        }
    }
}
