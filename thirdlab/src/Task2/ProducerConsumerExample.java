package Task2;

import java.util.Scanner;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Drop drop = new Drop();
        (new Thread(new Producer(drop, size))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
