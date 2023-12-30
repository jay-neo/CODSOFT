import java.util.Scanner;

class BankAccount {
    private long CardNumber;
    private int PIN;
    private double Balance;
    public BankAccount(long _CardNumber, int _PIN, double _Balance) {
        this.CardNumber = _CardNumber;
        this.PIN = _PIN;
        this.Balance = _Balance;
    }
    public long getCardNumber() {
        return this.CardNumber;
    }
    public int getPIN() {
        return this.PIN;
    }
    public double getBalance() {
        return this.Balance;
    }
    public void deposit(double amount) {
        this.Balance += amount;
        System.out.println("Deposited: $" + amount);
    }
    public boolean withdraw(double amount) {
        if (this.Balance >= amount) {
            this.Balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        }
    }
}

class VirtualDatabase {
    private BankAccount[] Accounts;
    VirtualDatabase() {
        Accounts = new BankAccount[3];
        Accounts[0] = new BankAccount(1000123L, 2021, 100);
        Accounts[1] = new BankAccount(1000456L, 2022, 200);
        Accounts[2] = new BankAccount(1000789L, 2023, 500);
    }
    public BankAccount fetch(long cardNumber) {
        for (BankAccount Acc : Accounts) {
            if(Acc.getCardNumber() == cardNumber) {
                System.out.println("Your account found successfully!!");
                return Acc;
            }
        }
        System.out.println("Your account not fount!!");
        return null;
    }
}

class ATM {
    private BankAccount account;

    public ATM(long number) {
        VirtualDatabase db = new VirtualDatabase();
        if ((account = db.fetch(number)) == null) {
            System.exit(0);
        }
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalied input!!");
                return;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    this.checkBalance();
                    break;
                case 2:
                    this.deposit();
                    break;
                case 3:
                    this.withdraw();
                    break;
                case 4:
                    System.out.println("Exiting the ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 4);
        sc.close();
    }

    private void checkBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your ATM PIN: ");
        if (!scanner.hasNextInt()) {
            System.out.println("You entered invalid PIN");
            return;
        }
        int PIN = scanner.nextInt();
        scanner.nextLine();
        if (PIN == account.getPIN()) {
            double balance = account.getBalance();
            System.out.println("Current balance: $" + balance);
        } else {
            System.out.println("You entered wrong PIN");
        }
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the deposit amount: $");
        double amount = 0;
        if (!scanner.hasNextDouble() || (amount = scanner.nextDouble()) < 0) {
            System.out.println("You entered invalied input");
            return;
        }
        scanner.nextLine();
        account.deposit(amount);
    }

    private void withdraw() {
        double amount = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: $");
        if (!scanner.hasNextDouble() || (amount = scanner.nextDouble()) < 0) {
            System.out.println("You entered invalied input");
            return;
        }
        scanner.nextLine();

        System.out.print("Enter your ATM PIN: ");
        if (!scanner.hasNextInt()) {
            System.out.println("You entered invalid PIN");
            return;
        }
        int PIN = scanner.nextInt();
        scanner.nextLine();

        if (PIN == account.getPIN()) {
            if (account.withdraw(amount)) {
                System.out.println("Please take your cash.");
            }
        } else {
            System.out.println("Your entered wrong PIN");
        }
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your card number: ");
        if (!scanner.hasNextLong()) {
            System.out.println("You entered invalid input!!");
            scanner.close();
            return;
        }
        long cardNumber = scanner.nextLong();
        scanner.nextLine();
        ATM atm = new ATM(cardNumber);
        atm.run();
        System.out.println();
        scanner.close();
    }
}
