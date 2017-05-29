import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Tablero extends JPanel {

    public Tablero() {
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Pelota
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.WHITE);
        g2D.fillOval(Game.x,Game.y,30,30);

        //Raquetas
        g2D.fillRect(10,Game.r1,15,80);
        g2D.fillRect(894-25,Game.r2,15,80);

        //Score
        Line2D.Double linea = new Line2D.Double(getBounds().getCenterX(), 0,
                getBounds().getCenterX(), getBounds().getMaxY());
        g2D.draw(linea);
        Font score = new Font("Arial", Font.BOLD, 30);
        g2D.setFont(score);
        g2D.drawString(Integer.toString(Game.scoreJ1), (float) getBounds().getCenterX() - 50, 30);
        g2D.drawString(Integer.toString(Game.scoreJ2), (float) getBounds().getCenterX() + 25, 30);
    }
}
