package queueBilliards;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonFirst = new JButton("Yellow ball waits blue");
        buttonFirst.addActionListener(e -> {
            Ball yellow = new Ball(canvas, Color.YELLOW, 0, 0, 2, 2);
            canvas.add(yellow);
            BallThread yellowThread = new BallThread(yellow);
            yellowThread.start();
            Ball blue = new Ball(canvas, Color.BLUE, 0, 0, 2, 2);
            canvas.add(blue);
            BallThread blueThread = new BallThread(blue, yellowThread);
            blueThread.start();
        });

        JButton buttonStop = new JButton("Stop");
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonFirst);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createBallThread(Ball b) {
        canvas.add(b);
        BallThread thread = new BallThread(b);
        thread.start();
    }
}