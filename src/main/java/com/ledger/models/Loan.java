package com.ledger.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Loan {
    public String bankName;
    public String borrowerName;
    public int principal;
    public int numOfYears;
    public int interest;
}
