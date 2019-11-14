package ro.teamnet.accountImpl;

import ro.teamnet.IAccount;

public class CheckingAccount implements IAccount{
    private String identifier;
    private double amountInAccount;
    private static final double INTEREST = 0.0;

    public CheckingAccount(String identifier, double amountInAccount) {
        this.amountInAccount = amountInAccount;
        this.identifier = identifier;
    }

    public CheckingAccount(CheckingAccount checkingAccount){
        this.amountInAccount = checkingAccount.amountInAccount;
        this.identifier = checkingAccount.identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static double getINTEREST() {
        return INTEREST;
    }

    public double getAmountInAccount() {
        return amountInAccount;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CheckingAccount) {
            CheckingAccount checkingAccount = new CheckingAccount((CheckingAccount) obj);
            if (this.identifier.equalsIgnoreCase(checkingAccount.identifier))
                return true;
        }
        return false;
    }

    public AccountActionMessages depositInChecking(double depositValue) {
        amountInAccount += depositValue;
        return AccountActionMessages.DEPOSIT_SUCCESSFUL;
    }

    @Override
    public void deposit(double depositValue){
        if(depositInChecking(depositValue) == AccountActionMessages.DEPOSIT_SUCCESSFUL)
            System.out.println("Deposit was successful. New amount in account is: " + amountInAccount);
    }

    public AccountActionMessages withdrawFromChecking(double withdrawValue){
        if (amountInAccount - withdrawValue < 0){
            return IAccount.AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
        }
        else {
            amountInAccount -= withdrawValue;
            return IAccount.AccountActionMessages.WITHDRAW_SUCCESSFUL;
        }
    }

    @Override
    public void withdraw(double withdrawValue){
        AccountActionMessages message = withdrawFromChecking(withdrawValue);
        if(message == AccountActionMessages.WITHDRAW_SUCCESSFUL){
            System.out.println("Withdraw succesful. New amount in account is: " + amountInAccount);
        }
        else if(message == AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS){
            System.out.println("The desired amount is too large");
        }
        else
            System.out.println("Invalid operation");
    }
    public double getInterestAfterNYears(int years){
        return getAmountInAccount();
    }


    public String showAccount() {
        return "Checking Account - " + identifier + "; Amount in account: " + amountInAccount;
    }
}
