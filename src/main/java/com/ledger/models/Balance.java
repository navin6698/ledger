package com.ledger.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Balance {

    public String bankName;
    public String borrowerName;
    public int emiNo;
}
