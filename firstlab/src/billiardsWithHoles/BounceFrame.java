package billiardsWithHoles;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private Canvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new Canvas();
        Container content = this.getContentPane();
        content.add(this.canvas, "Center");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStartHundred = new JButton("Start x100");
        JButton buttonStop = new JButton("Stop");
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(BounceFrame.this.canvas, HEIGHT / 2 - 40, WIDTH / 2 - 30);
                BounceFrame.this.canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
            }
        });
        buttonStartHundred.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 100; i++)
                {
                    Ball b = new Ball(BounceFrame.this.canvas, HEIGHT / 2 - 40, WIDTH / 2 - 30);
                    BounceFrame.this.canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.start();
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(buttonStartHundred);
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, "South");

        canvas.add(new Hole(canvas, "left-top", 0, 0));
        canvas.add(new Hole(canvas, "middle-top", 0, WIDTH / 2 - 40));
        canvas.add(new Hole(canvas, "right-top", 0, WIDTH - 60));
        canvas.add(new Hole(canvas, "middle-left", (HEIGHT - 120) / 2, 0));
        canvas.add(new Hole(canvas, "middle-right", (HEIGHT - 120) / 2, WIDTH - 60));
        canvas.add(new Hole(canvas, "bottom-left", HEIGHT - 120, 0));
        canvas.add(new Hole(canvas, "bottom-middle", HEIGHT - 120, WIDTH / 2 - 40));
        canvas.add(new Hole(canvas, "bottom-right", HEIGHT - 120, WIDTH - 60));
    }
}