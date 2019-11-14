package ro.teamnet.accountImpl;

import ro.teamnet.IAccount;

public class SavingsAccount implements IAccount {
    private String identifier;
    private double amountInAccount;
    private static final double INTEREST = 0.01;

    public SavingsAccount(String identifier, double amountInAccount) {
        this.amountInAccount = amountInAccount;
        this.identifier = identifier;
    }

    public SavingsAccount(SavingsAccount savingsAccount){
        this.amountInAccount = savingsAccount.amountInAccount;
        this.identifier = savingsAccount.identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SavingsAccount) {
            SavingsAccount savingsAccount = new SavingsAccount((SavingsAccount) obj);
            if (this.identifier.equalsIgnoreCase(savingsAccount.identifier))
                return true;
        }
        return false;
    }

    public String getIdentifier() {
        return identifier;
    }

    public double getAmountInAccount() {
        return amountInAccount;
    }

    public static double getINTEREST() {
        return INTEREST;
    }

    public AccountActionMessages depositInSavings(double depositValue) {
        amountInAccount += depositValue;
        return AccountActionMessages.DEPOSIT_SUCCESSFUL;
    }

    @Override
    public void deposit(double depositValue){
        if(depositInSavings(depositValue) == AccountActionMessages.DEPOSIT_SUCCESSFUL)
            System.out.println("Deposit was successful. New amount in account is: " + amountInAccount);
    }

    public AccountActionMessages withdrawFromSavings(double withdrawValue){
        if(withdrawValue <= 1000) {
            if (amountInAccount - withdrawValue < 0){
                return AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
            }
            else {
                amountInAccount -= withdrawValue;
                return AccountActionMessages.WITHDRAW_SUCCESSFUL;
            }
        }
        else return AccountActionMessages.WITHDRAW_AMOUNT_TOO_LARGE;
    }

    @Override
    public void withdraw(double withdrawValue){
        AccountActionMessages message = withdrawFromSavings(withdrawValue);
        if(message == AccountActionMessages.WITHDRAW_SUCCESSFUL){
            System.out.println("Withdraw succesful. New amount in account is: " + amountInAccount);
        }
        else if(message == AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS){
            System.out.println("The desired amount is too large");
        }
        else if(message == AccountActionMessages.WITHDRAW_AMOUNT_TOO_LARGE){
            System.out.println("You can not withdraw more than 1000");
        }
        else
            System.out.println("Invalid operation");

    }

    public double getInterestAfterNYears(int years){
        return getAmountInAccount()*Math.pow((1+ INTEREST), years);
    }

    public String showAccount() {
        return "Savings Account - " + identifier + "; Amount in account: " + amountInAccount +
                "; with an interest rate of " + INTEREST*100 + "%";
    }
}
