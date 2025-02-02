import javax.swing.*;
import java.awt.*;

public class HealthBar extends JPanel {
    private int health = 100;
    private final int maxHealth = 100;

    public HealthBar() {
        setPreferredSize(new Dimension(200, 30)); // Size of health bar
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth)); // Keep within bounds
        repaint(); // Redraw the health bar
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = (int) ((getWidth() - 4) * ((double) health / maxHealth));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.fillRect(2, 2, barWidth, getHeight() - 4);

        g.setColor(Color.WHITE);
        g.drawRect(1, 1, getWidth() - 2, getHeight() - 2); // Border

        g.setColor(Color.WHITE);
        g.drawString(health + " / " + maxHealth, getWidth() / 2 - 20, getHeight() / 2 + 5);
    }
}
