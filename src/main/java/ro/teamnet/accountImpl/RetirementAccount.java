package ro.teamnet.accountImpl;

import ro.teamnet.IAccount;

public class RetirementAccount implements IAccount {
    private String identifier;
    private double amountInAccount;
    private int personAge;
    private static final int AGE_OF_WITHDRAWAL = 65;
    private static final double INTEREST = 0.02;

    public RetirementAccount(String identifier, double amountInAccount, int personAge) {
        this.amountInAccount = amountInAccount;
        this.identifier = identifier;
        this.personAge = personAge;
    }

    public RetirementAccount(RetirementAccount retirementAccount){
        this.amountInAccount = retirementAccount.amountInAccount;
        this.identifier = retirementAccount.identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RetirementAccount) {
            RetirementAccount retirementAccount = new RetirementAccount((RetirementAccount) obj);
            if (this.identifier.equalsIgnoreCase(retirementAccount.identifier))
                return true;
        }
        return false;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getPersonAge() {
        return personAge;
    }

    public static double getINTEREST() {
        return INTEREST;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public static int getAgeOfWithdrawal() {
        return AGE_OF_WITHDRAWAL;
    }

    public double getAmountInAccount() {
        return amountInAccount;
    }

    public AccountActionMessages depositInRetirement(double depositValue) {
        amountInAccount += depositValue;
        return AccountActionMessages.DEPOSIT_SUCCESSFUL;
    }

    @Override
    public void deposit(double depositValue){
        if(depositInRetirement(depositValue) == AccountActionMessages.DEPOSIT_SUCCESSFUL)
            System.out.println("Deposit was successful. New amount in account is: " + amountInAccount);
    }

    @Override
    public void withdraw(double withdrawValue){
        AccountActionMessages message = withdrawFromRetirement(withdrawValue);
        if(message == AccountActionMessages.WITHDRAW_SUCCESSFUL){
            System.out.println("Withdraw succesful. New amount in account is: " + amountInAccount);
        }
        else if(message == AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS){
            System.out.println("The desired amount is too large");
        }
        else if(message == AccountActionMessages.NOT_OF_AGE){
            System.out.println("You can not withdraw until you have 65 years");
        }
        else
            System.out.println("Invalid operation");

    }

    public AccountActionMessages withdrawFromRetirement(double withdrawValue){
        if(personAge >= AGE_OF_WITHDRAWAL) {
            if (amountInAccount - withdrawValue < 0){
                return AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
            }
            else {
                amountInAccount -= withdrawValue;
                return AccountActionMessages.WITHDRAW_SUCCESSFUL;
            }
        }
        else return AccountActionMessages.NOT_OF_AGE;
    }


    public double getInterestAfterNYears(int years){
        return getAmountInAccount()*Math.pow((1+ INTEREST), years);
    }

    public String showAccount() {
        return "Retirement Account - " + identifier + "; Amount in account: " + amountInAccount +
                "; with an interest rate of " + INTEREST*100 + "%";
    }
}
