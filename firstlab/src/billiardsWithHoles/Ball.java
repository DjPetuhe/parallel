package billiardsWithHoles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class Ball {
    protected Canvas canvas;
    protected Color col = Color.blue;
    public static final int radius = 10;
    protected double x = 0;
    protected double y = 0;
    private double dx = 0;
    private double dy = 0;
    public boolean hit = false;

    public Ball(Canvas c, int y, int x) {
        this.canvas = c;
        this.y = y + (Math.random() * 40 - 20);
        this.x = x + (Math.random() * 80 - 40);
        while (Math.abs(dx) < 0.1 || Math.abs(dy) < 0.1)
        {
            this.dx = Math.random() * 4 - 2;
            this.dy = Math.random() > 0.5 ? Math.abs(dx) - 2 : 2 - Math.abs(dx);
        }
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
        if (this.x < 0) {
            this.x = 0;
            this.dx = -this.dx;
        }

        if (this.x + 20 >= this.canvas.getWidth()) {
            this.x = this.canvas.getWidth() - radius * 2;
            this.dx = -this.dx;
        }

        if (this.y < 0) {
            this.y = 0;
            this.dy = -this.dy;
        }

        if (this.y + 20 >= this.canvas.getHeight()) {
            this.y = this.canvas.getHeight() - radius * 2;
            this.dy = -this.dy;
        }

        this.canvas.repaint();
    }

    public boolean isOnHole() {
        for (Hole hole: canvas.holes) {
            if (hole.checkHit(y, x)) {
                hit = true;
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(col);
        g2.fill(new Ellipse2D.Double((double)this.x, (double)this.y, radius * 2, radius * 2));
    }
}
