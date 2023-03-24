package TextSymbolsOutput;

public class SyncSymbolPrinterThread extends Thread {
    private char symbol;
    private SynchronizedPrinter printer;

    public SyncSymbolPrinterThread(char symbol, SynchronizedPrinter printer) {
        this.symbol = symbol;
        this.printer = printer;
    }

    @Override
    public void run() {
        while (!printer.isFinished()) {
            printer.waitAndPrint(symbol);
        }
    }
}
