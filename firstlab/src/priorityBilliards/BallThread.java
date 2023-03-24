package priorityBilliards;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        b = ball;
    }

    @Override
    public void run() {
        try {
            while (true) {
                b.move();
                Thread.sleep(5);
            }
        } catch(InterruptedException ex) {
        }
    }
}
