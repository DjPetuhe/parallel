package TextSymbolsOutput;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        char[] chars = new char[2];
        chars[0] = '|';
        chars[1] = '-';
        try {
            int choose = System.in.read();
            if (choose == (int)'0') {
                AsyncSymbolPrinterThread symbolThreadFirst = new AsyncSymbolPrinterThread(chars[0]);
                AsyncSymbolPrinterThread symbolThreadSecond = new AsyncSymbolPrinterThread(chars[1]);
                symbolThreadFirst.start();
                symbolThreadSecond.start();
            } else {
                SynchronizedPrinter printer = new SynchronizedPrinter(chars);
                SyncSymbolPrinterThread symbolThreadFirst = new SyncSymbolPrinterThread(chars[0], printer);
                SyncSymbolPrinterThread symbolThreadSecond = new SyncSymbolPrinterThread(chars[1], printer);
                symbolThreadFirst.start();
                symbolThreadSecond.start();
            }
        } catch (IOException e) {
        }
    }
}
