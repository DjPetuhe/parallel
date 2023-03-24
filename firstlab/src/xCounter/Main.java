package xCounter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 4; i++) {
            counter.resetNumber();
            runTwoThreads(counter, i);
            if (i == 0) System.out.println("Async result: " + counter.getNumber());
            else System.out.println("Sync #"+ i +" result: " + counter.getNumber());
        }
    }

    private static void runTwoThreads(Counter counter, int type) {
        try {
            Thread incThread = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    if (type == 0) counter.asyncIncrement();
                    if (type == 1) counter.syncIncrementFirst();
                    if (type == 2) counter.syncIncrementSecond();
                    if (type == 3) counter.syncIncrementThird();
                }
            });
            Thread decThread = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    if (type == 0) counter.asyncDecrement();
                    if (type == 1) counter.syncDecrementFirst();
                    if (type == 2) counter.syncDecrementSecond();
                    if (type == 3) counter.syncDecrementThird();
                }
            });
            incThread.start();
            decThread.start();
            incThread.join();
            decThread.join();
        } catch (InterruptedException ex) {
        }
    }
}
