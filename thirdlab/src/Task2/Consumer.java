package Task2;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private static int RANDOM_BOUND = 10;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        int num = drop.take();
        while (num != Drop.TERMINATOR) {
            System.out.format("Number: %s%n", num);
            //try {
                //Thread.sleep(random.nextInt(RANDOM_BOUND));
            //} catch (InterruptedException ignored) { }
            num = drop.take();
        }
    }
}