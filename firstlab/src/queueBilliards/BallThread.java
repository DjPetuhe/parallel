package queueBilliards;

public class BallThread extends Thread {
    private Ball b;
    private boolean isWaiting = false;
    private BallThread toWait;

    public BallThread(Ball ball) {
        b = ball;
    }
    public BallThread(Ball ball, BallThread toWait) {
        b = ball;
        this.isWaiting = true;
        this.toWait = toWait;
    }

    @Override
    public void run() {
        try {
            if (isWaiting) toWait.join();
            for (int i = 0; i < 500; i++) {
                b.move();
                Thread.sleep(5);
            }
        } catch(InterruptedException ex) {
        }
    }
}
