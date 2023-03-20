package billiardsWithHoles;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList();
    public ArrayList<Hole> holes = new ArrayList<>();

    public Canvas() {
    }

    public void add(Ball b) {
        this.balls.add(b);
    }
    public void add(Hole h) {
        this.holes.add(h);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(int i = 0; i < this.balls.size(); ++i) {
            Ball b = (Ball)this.balls.get(i);
            if (b.hit)
            {
                this.balls.remove(i);
                i--;
            }
            else b.draw(g2);
        }
        for(int i = 0; i < this.holes.size(); ++i) {
            Hole h = (Hole)this.holes.get(i);
            h.draw(g2);
        }
    }
}
