package TextSymbolsOutput;

public class AsyncSymbolPrinterThread extends Thread {
    private char symbol;
    private static int counter = 0;
    private static final int maxSymbols = 5000;
    public static final int symbolsInLine = 100;

    public AsyncSymbolPrinterThread(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        while (counter < maxSymbols) {
            System.out.print(symbol);
            if (counter % symbolsInLine == 0) {
                System.out.print('\n');
            }
            counter++;
        }
    }
}
