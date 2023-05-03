package Task2;

public class Drop {
    private int num;
    private boolean empty = true;
    public static final int TERMINATOR = -1;

    public synchronized int take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) { }
        }
        empty = true;
        notifyAll();
        return num;
    }

    public synchronized void put(int num) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException ignored) { }
        }
        empty = false;
        this.num = num;
        notifyAll();
    }
}
