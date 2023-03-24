package TextSymbolsOutput;

import java.util.List;

public class SynchronizedPrinter {
    public static final int totalSymbols = 5000;
    public static final int symbolsInLine = 100;
    private static int symbolsCounter = 0;
    private boolean finished = false;
    private char[] chars;
    private int currentIndex = 0;

    public SynchronizedPrinter(char[] chars) {
        this.chars = chars;
    }

    public synchronized void waitAndPrint(char symbol) {
        while (symbol != chars[currentIndex]) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        System.out.print(symbol);
        currentIndex++;
        symbolsCounter++;
        if (currentIndex >= chars.length) {
            currentIndex = 0;
        }
        if (symbolsCounter % symbolsInLine == 0) {
            System.out.print('\n');
        }
        if (symbolsCounter == totalSymbols) {
            finished = true;
        }
        notifyAll();
    }

    public boolean isFinished() {
        return finished;
    }
}
