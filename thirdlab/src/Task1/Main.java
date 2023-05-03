package Task1;

import java.util.Scanner;

/**
 author Cay Horstmann
 */
public class Main {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(b, i, INITIAL_BALANCE, option);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }
}