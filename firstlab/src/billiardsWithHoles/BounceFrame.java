package billiardsWithHoles;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private Canvas canvas;
    private JLabel counterLabel;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

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

        JPanel textPanel = new JPanel();
        counterLabel = new JLabel();
        textPanel.add(counterLabel);
        textPanel.setBackground(Color.lightGray);
        content.add(textPanel, "North");
        canvas.add(new Hole(canvas, "left-top", 0, 0, counterLabel));
        canvas.add(new Hole(canvas, "middle-top", 0, WIDTH / 2 - 40, counterLabel));
        canvas.add(new Hole(canvas, "right-top", 0, WIDTH - 60, counterLabel));
        canvas.add(new Hole(canvas, "middle-left", (HEIGHT - 120) / 2, 0, counterLabel));
        canvas.add(new Hole(canvas, "middle-right", (HEIGHT - 120) / 2, WIDTH - 60, counterLabel));
        canvas.add(new Hole(canvas, "bottom-left", HEIGHT - 140, 0, counterLabel));
        canvas.add(new Hole(canvas, "bottom-middle", HEIGHT - 140, WIDTH / 2 - 40, counterLabel));
        canvas.add(new Hole(canvas, "bottom-right", HEIGHT - 140, WIDTH - 60, counterLabel));
    }
}