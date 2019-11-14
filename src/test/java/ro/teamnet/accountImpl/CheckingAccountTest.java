package ro.teamnet.accountImpl;

import org.junit.Before;
import org.junit.Test;
import ro.teamnet.IAccount;

import static org.junit.Assert.*;

public class CheckingAccountTest {
    private CheckingAccount subjectUnderTest;

    @Before
    public void setUp(){
        subjectUnderTest = new CheckingAccount("RO88QWER", 890);
    }

    @Test
    public void getAmountInAccount() {
        double expected = 890;
        double actual = subjectUnderTest.getAmountInAccount();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void depositInChecking() {
        IAccount.AccountActionMessages expected = IAccount.AccountActionMessages.DEPOSIT_SUCCESSFUL;
        IAccount.AccountActionMessages actual = subjectUnderTest.depositInChecking(10);
        double expected2 = 900;
        double actual2 = subjectUnderTest.getAmountInAccount();
        assertEquals(expected, actual);
        assertEquals(expected2, actual2, 0.0);
    }

    @Test
    public void withdrawFromChecking() {
        IAccount.AccountActionMessages expectedWithdrawSuccess = IAccount.AccountActionMessages.WITHDRAW_SUCCESSFUL;
        IAccount.AccountActionMessages expectedWithdrawInsufficientFunds = IAccount.AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
        IAccount.AccountActionMessages actualWithdrawSuccess = subjectUnderTest.withdrawFromChecking(10);
        IAccount.AccountActionMessages actualWithdrawInsufficientFund = subjectUnderTest.withdrawFromChecking(900);

        assertEquals(expectedWithdrawSuccess, actualWithdrawSuccess);
        assertEquals(expectedWithdrawInsufficientFunds, actualWithdrawInsufficientFund);
    }

    @Test
    public void getInterestAfterNYears() {
        double expected = 890;
        double actual = subjectUnderTest.getInterestAfterNYears(10);

        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void showAccount() {
        String expected = "Checking Account - " + subjectUnderTest.getIdentifier() + "; Amount in account: " +
                subjectUnderTest.getAmountInAccount() + "; with an interest rate of " +
                SavingsAccount.getINTEREST() + "%";
        assertEquals(expected, subjectUnderTest.showAccount());
    }
}