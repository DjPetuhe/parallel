package priorityBilliards;

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

        JButton buttonFirst = new JButton("red x1 blue x10");
        buttonFirst.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                createBallThread(new Ball(canvas, Color.BLUE, 0, 0, 2, 2), 1);
            }
            createBallThread(new Ball(canvas, Color.RED, 0, 0, 2, 2), 10);
        });

        JButton buttonSecond = new JButton("red x1 blue x25");
        buttonSecond.addActionListener(e -> {
            for (int i = 0; i < 25; i++) {
                createBallThread(new Ball(canvas, Color.BLUE, 0, 0, 2, 2), 1);
            }
            createBallThread(new Ball(canvas, Color.RED, 0, 0, 2, 2), 10);
        });

        JButton buttonThird = new JButton("red x1 blue x50");
        buttonThird.addActionListener(e -> {
            for (int i = 0; i < 50; i++) {
                createBallThread(new Ball(canvas, Color.BLUE, 0, 0, 2, 2), 1);
            }
            createBallThread(new Ball(canvas, Color.RED, 0, 0, 2, 2), 10);
        });

        JButton buttonFourth = new JButton("red x1 blue x100");
        buttonFourth.addActionListener(e -> {
            for (int i = 0; i < 100; i++) {
                createBallThread(new Ball(canvas, Color.BLUE, 0, 0, 2, 2), 1);
            }
            createBallThread(new Ball(canvas, Color.RED, 0, 0, 2, 2), 10);
        });

        JButton buttonFifth = new JButton("red x1 blue x500");
        buttonFifth.addActionListener(e -> {
            for (int i = 0; i < 500; i++) {
                createBallThread(new Ball(canvas, Color.BLUE, 0, 0, 2, 2), 1);
            }
            createBallThread(new Ball(canvas, Color.RED, 0, 0, 2, 2), 10);
        });

        JButton buttonStop = new JButton("Stop");
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonFirst);
        buttonPanel.add(buttonSecond);
        buttonPanel.add(buttonThird);
        buttonPanel.add(buttonFourth);
        buttonPanel.add(buttonFifth);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createBallThread(Ball b, int priority) {
        canvas.add(b);
        BallThread thread = new BallThread(b);
        thread.setPriority(priority);
        thread.start();
    }
}