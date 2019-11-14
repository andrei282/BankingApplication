package ro.teamnet.accountImpl;

import org.junit.Test;
import org.junit.Before;
import ro.teamnet.IAccount;

import static org.junit.Assert.*;

public class SavingsAccountTest {
    private SavingsAccount subjectUnderTest;

    @Before
    public void setUp(){
        subjectUnderTest = new SavingsAccount("RO88QWER", 890);
    }

    @Test
    public void getAmountInAccount() {
        double expected = 890;
        double actual = subjectUnderTest.getAmountInAccount();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void depositInSavings() {
        IAccount.AccountActionMessages expected = IAccount.AccountActionMessages.DEPOSIT_SUCCESSFUL;
        IAccount.AccountActionMessages actual = subjectUnderTest.depositInSavings(10);
        double expected2 = 900;
        double actual2 = subjectUnderTest.getAmountInAccount();
        assertEquals(expected, actual);
        assertEquals(expected2, actual2, 0.0);
    }

    @Test
    public void withdrawFromSavings() {
        IAccount.AccountActionMessages expectedWithdrawSuccess = IAccount.AccountActionMessages.WITHDRAW_SUCCESSFUL;
        IAccount.AccountActionMessages expectedWithdrawInsufficientFunds = IAccount.AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
        IAccount.AccountActionMessages expectedWithdrawAmountTooLarge = IAccount.AccountActionMessages.WITHDRAW_AMOUNT_TOO_LARGE;
        IAccount.AccountActionMessages actualWithdrawSuccess = subjectUnderTest.withdrawFromSavings(10);
        IAccount.AccountActionMessages actualWithdrawInsufficientFund = subjectUnderTest.withdrawFromSavings(900);
        IAccount.AccountActionMessages actualWithdrawAmountTooLarge = subjectUnderTest.withdrawFromSavings(1500);

        assertEquals(expectedWithdrawSuccess, actualWithdrawSuccess);
        assertEquals(expectedWithdrawAmountTooLarge, actualWithdrawAmountTooLarge);
        assertEquals(expectedWithdrawInsufficientFunds, actualWithdrawInsufficientFund);
    }

    @Test
    public void getInterestAfterNYears() {
        double expected = 983.11;
        double actual = subjectUnderTest.getInterestAfterNYears(10);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void showAccount() {
        String expected = "Savings Account - " + subjectUnderTest.getIdentifier() + "; Amount in account: " +
                subjectUnderTest.getAmountInAccount() + "; with an interest rate of " +
                SavingsAccount.getINTEREST()*100 + "%";
        assertEquals(expected, subjectUnderTest.showAccount());
    }
}