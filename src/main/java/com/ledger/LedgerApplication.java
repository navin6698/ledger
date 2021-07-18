package com.ledger;

import com.ledger.constants.Constants;
import com.ledger.models.LoanAccount;
import com.ledger.service.LedgerFactory;
import com.ledger.service.LedgerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LedgerApplication {

    public static void main(String args[]){

        LedgerService ledgerService;
        Map<Integer, LoanAccount> loanAccount = new HashMap<>();
        String inputFile = args[0];
        try {
            File file = new File(inputFile);
            BufferedReader input = new BufferedReader(new FileReader(file));
            String inputCommand = "";

            while((inputCommand = input.readLine()) != null && inputCommand.length() != 0) {
                String[] inputData = inputCommand.split(Constants.SPACE);
                ledgerService = LedgerFactory.getLedgerService(inputData[0]);

                if(ledgerService != null) {
                    ledgerService.processData(inputData , loanAccount);
                }
                else {
                    System.out.println(Constants.INVALID_COMMAND);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Please pass input file" + e.getMessage());
        }
    }
}
