import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("=== Welcome to Banking System ===");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show All");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter account holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter initial balance: ");
                        double balance = sc.nextDouble();
                        sc.nextLine();

                        Account acc = bank.createAccount(name, accNo, balance);
                        System.out.println("Account created successfully: " + acc);
                    }
                    case 2 -> {
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter amount to deposit: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        bank.depositToAccount(accNo, amt);
                        System.out.println("Deposit successful!");
                    }
                    case 3 -> {
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter amount to withdraw: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        bank.withdrawFromAccount(accNo, amt);
                        System.out.println("Withdrawal successful!");
                    }
                    case 4 -> {
                        System.out.print("From Account: ");
                        String from = sc.nextLine();
                        System.out.print("To Account: ");
                        String to = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        bank.transferBetweenAccounts(from, to, amt);
                        System.out.println("Transfer successful!");
                    }
                    case 5 -> bank.showAllAccounts();
                    case 6 -> {
                        System.out.println("Thank you for using the Banking System!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
