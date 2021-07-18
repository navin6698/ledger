package com.ledger.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Payment {
    public String bankName;
    public String borrowerName;
    public int lumpSumAmount;
    public int emiNo;
}
