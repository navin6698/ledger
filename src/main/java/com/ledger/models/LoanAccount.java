package com.ledger.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Builder
public class LoanAccount {

    public Loan loan;
    public int totalAmount;
    public int remainingAmount;
    public int noOfEMI;
    public int noOfEMIRemaining;
    public int monthlyPayment;
    public int lastEMIPaid;
    public Map<Integer, AccountBalance> statement;

}
