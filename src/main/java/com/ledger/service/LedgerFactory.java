package com.ledger.service;

import com.ledger.constants.Constants;
import com.ledger.service.impl.BalanceServiceImpl;
import com.ledger.service.impl.LoanServiceImpl;
import com.ledger.service.impl.PaymentServiceImpl;

public class LedgerFactory {

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
