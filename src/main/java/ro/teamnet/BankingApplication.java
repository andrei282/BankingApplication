package ro.teamnet;

import ro.teamnet.accountImpl.CheckingAccount;
import ro.teamnet.accountImpl.RetirementAccount;
import ro.teamnet.accountImpl.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class BankingApplication {
    public static void main(String[] args) {
        Person p1 = new Person("Andrei", 23, "ANDREI_TACHE");

        p1.addAccount(new SavingsAccount("EUR74OMDJ", 105.6));
        p1.addAccount(new SavingsAccount("RON88IDAX", 0.0));

        p1.addAccount(new SavingsAccount("EUR74OMDJ", 2));

        p1.addAccount(new CheckingAccount("CHK01RARB", 2750));
        p1.addAccount(new CheckingAccount("CHK01RARB", 0.0));

        p1.addAccount(new RetirementAccount("RET66OLDM", 500, p1.getAge()));
        p1.addAccount(new RetirementAccount("RET66OLDM", 0, p1.getAge()));

        System.out.println();
        p1.showAccounts();

        /*List<IAccount> accounts = new ArrayList<>(p1.getAccounts());
        for(int i=0; i<accounts.size(); i++){
            System.out.println(accounts.get(i).showAccount());
        }*/
    }
}
