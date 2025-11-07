import java.util.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String name, String accNo, double balance)
            throws InvalidNameException, InvalidAmountException {
        Account acc = new Account(name, accNo, balance);
        accounts.put(accNo, acc);
        return acc;
    }

    public Account getAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null)
            throw new AccountNotFoundException("Account not found");
        return acc;
    }

    public void depositToAccount(String accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        getAccount(accNo).deposit(amount);
    }

    public void withdrawFromAccount(String accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        getAccount(accNo).withdraw(amount);
    }

    public void transferBetweenAccounts(String fromAcc, String toAcc, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        Account source = getAccount(fromAcc);
        Account target = getAccount(toAcc);
        source.transfer(target, amount);
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }
}
