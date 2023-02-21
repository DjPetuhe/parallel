package billiardsWithHoles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Hole {
    protected Canvas canvas;
    protected Color col = Color.black;
    public static final int radius = 20;
    protected int x = 0;
    protected int y = 0;
    private int hits = 0;
    private String name;
    public Hole(Canvas c, String name, int y, int x) {
        this.canvas = c;
        this.name = name;
        this.y = y;
        this.x = x;
    }

    public boolean checkHit(double y, double x) {
        if (Math.hypot((y + Ball.radius) - (this.y + radius), (x + Ball.radius) - (this.x + radius)) <= radius) {
            hits++;
            System.out.println("========================");
            for (Hole hole : canvas.holes)  {
                hole.printInfo();
            }
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(col);
        g2.fill(new Ellipse2D.Double((double)this.x, (double)this.y, radius * 2, radius * 2));
    }

    public void printInfo() {
        System.out.println(String.format("%-15s= %s" , name, hits ));
    }
}
