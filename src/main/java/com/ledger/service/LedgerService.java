package com.ledger.service;

import com.ledger.models.LoanAccount;

import java.util.Map;

public interface LedgerService {

    void processData(String[] inputData, Map<Integer, LoanAccount> loanAccount);
}
