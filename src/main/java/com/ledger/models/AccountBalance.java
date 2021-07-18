package com.ledger.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountBalance {
    public int amountPaid;
    public int remainingEMI;
}
