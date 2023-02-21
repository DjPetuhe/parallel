package billiardsWithHoles;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        this.b = ball;
    }

    public void run() {
        try {
            while (true){
                this.b.move();
                if (this.b.isOnHole()) {
                    b.canvas.repaint();
                    break;
                }
                Thread.sleep(5L);
            }
        } catch (InterruptedException var2) {
        }

    }
}
