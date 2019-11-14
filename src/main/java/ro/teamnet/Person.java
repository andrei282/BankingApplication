package ro.teamnet;

import ro.teamnet.accountImpl.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private String identifier;
    private List<IAccount> accounts;

    public Person(String name, int age, String identifier) {
        this.name = name;
        this.age = age;
        this.identifier = identifier;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<IAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(IAccount account){
        boolean flag = true;
        for(IAccount iterAccount: accounts) {
            if (iterAccount.equals(account)) {
                System.out.println(iterAccount.showAccount() + " has already been aded!");
                flag = false;
            }
        }
        if (flag) {
            accounts.add(account);
            System.out.println(account.getClass().getSimpleName() + " added");
        }
    }

    public double getInterestAfterNYears(IAccount account, int years){
        return account.getInterestAfterNYears(years);
    }

    public void showAccounts() {
        System.out.println(name + ", age " + age + "; identifier number: " + identifier + " has the following accounts:");
        for(IAccount account: accounts){
            System.out.println("---------> " + account.showAccount());
        }
    }
}
