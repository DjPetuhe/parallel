package Task2;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private final int arrSize;
    private static int RANDOM_BOUND = 10;

    public Producer(Drop drop, int arrSize) {
        this.drop = drop;
        this.arrSize = arrSize;
    }

    @Override
    public void run() {
        int[] numArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            numArr[i] = i + 1;
        }
        Random random = new Random();
        for (int j : numArr) {
            drop.put(j);
            //try {
            //    Thread.sleep(random.nextInt(RANDOM_BOUND));
            //} catch (InterruptedException ignored) { }
        }
        drop.put(Drop.TERMINATOR);
    }
}
