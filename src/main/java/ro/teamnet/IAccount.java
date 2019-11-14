package ro.teamnet;

public interface IAccount {
    enum AccountActionMessages{
        WITHDRAW_SUCCESSFUL,
        WITHDRAW_INSUFFICIENT_FUNDS,
        WITHDRAW_AMOUNT_TOO_LARGE,
        DEPOSIT_SUCCESSFUL,
        NOT_OF_AGE
    }

    void deposit(double depositValue);
    void withdraw(double withdrawValue);
    double getInterestAfterNYears(int years);
    String showAccount();
}
