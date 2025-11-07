import java.util.Objects;

public class Account {
    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String holderName, String accountNumber, double initialBalance)
            throws InvalidNameException, InvalidAmountException {
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new InvalidNameException("Holder name cannot be empty");
        }
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative");
        }
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public synchronized void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be > 0");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }

    public synchronized void transfer(Account to, double amount)
            throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be > 0");
        }
        synchronized (to) {
            if (this.balance < amount) {
                throw new InsufficientBalanceException("Insufficient balance for transfer");
            }
            this.balance -= amount;
            to.balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    @Override
    public String toString() {
        return String.format("Account[%s] - %s - Balance: %.2f", accountNumber, holderName, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
