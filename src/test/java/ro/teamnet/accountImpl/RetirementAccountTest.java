package ro.teamnet.accountImpl;

import org.junit.Test;
import org.junit.Before;
import ro.teamnet.IAccount;

import static org.junit.Assert.*;

public class RetirementAccountTest {
    private RetirementAccount subjectUnderTest;

    @Before
    public void setUp(){
        subjectUnderTest = new RetirementAccount("RO88QWER", 890, 25);
    }

    @Test
    public void setPersonAge() {
        int expected = 30;
        subjectUnderTest.setPersonAge(30);
        int actual = subjectUnderTest.getPersonAge();

        assertEquals(expected, actual);
    }

    @Test
    public void getAgeOfWithdrawal() {
        double expected = 0.02;
        double actual = RetirementAccount.getAgeOfWithdrawal();

        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getAmountInAccount() {
        double expected = 890;
        double actual = subjectUnderTest.getAmountInAccount();

        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void depositInRetirement() {
        IAccount.AccountActionMessages expected = IAccount.AccountActionMessages.DEPOSIT_SUCCESSFUL;
        IAccount.AccountActionMessages actual = subjectUnderTest.depositInRetirement(10);
        double expected2 = 900;
        double actual2 = subjectUnderTest.getAmountInAccount();
        assertEquals(expected, actual);
        assertEquals(expected2, actual2, 0.0);
    }

    @Test
    public void withdrawFromRetirement() {
        IAccount.AccountActionMessages expectedWithdrawSuccess = IAccount.AccountActionMessages.WITHDRAW_SUCCESSFUL;
        IAccount.AccountActionMessages expectedWithdrawInsufficientFunds = IAccount.AccountActionMessages.WITHDRAW_INSUFFICIENT_FUNDS;
        IAccount.AccountActionMessages expectedWithdraNotOfAge = IAccount.AccountActionMessages.NOT_OF_AGE;
        IAccount.AccountActionMessages actualWithdrawSuccess = subjectUnderTest.withdrawFromRetirement(10);
        IAccount.AccountActionMessages actualWithdrawInsufficientFund = subjectUnderTest.withdrawFromRetirement(900);
        IAccount.AccountActionMessages actualWithdrawtNotOfAge = subjectUnderTest.withdrawFromRetirement(1500);

        assertEquals(expectedWithdrawSuccess, actualWithdrawSuccess);
        assertEquals(expectedWithdraNotOfAge, actualWithdrawtNotOfAge);
        assertEquals(expectedWithdrawInsufficientFunds, actualWithdrawInsufficientFund);
    }

    @Test
    public void getInterestAfterNYears() {
        double expected = 1965.15;
        double actual = subjectUnderTest.getInterestAfterNYears(40);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void showAccount() {
        String expected = "Checking Account - " + subjectUnderTest.getIdentifier() + "; Amount in account: " +
                subjectUnderTest.getAmountInAccount() + "; with an interest rate of " +
                SavingsAccount.getINTEREST()*100 + "%";
        assertEquals(expected, subjectUnderTest.showAccount());
    }
}