# ledger application

LedgerFactory Class is used to pick the implementation class based on the input command type.
LedgerService has 3 implementations:
    LoanServiceImpl - Creates Loan account and initialize EMI amount, no of EMIs, creates a key based on bank and borrower name.
    PaymentServiceImpl - Handled lump sum payment, updates EMI paid so far, updates remaining EMI to be paid after lump sum payment and 
                    updates remaining amount to be paid
    BalanceServiceImpl - Prints number of EMI's remaining and amount paid so for

Models:
AccountBalance - No of EMI remaining and amount paid
Balance - Balance command Input
Loan - Loan command Input
LoanAccount - Stores complete infomration about Loan
Payment - Payment command input.

LedgerUtil - Utility class used across application.

Assumptions:
Bank and borrower name is unique, since this will be used to generate hashcode which is used as unique key across application