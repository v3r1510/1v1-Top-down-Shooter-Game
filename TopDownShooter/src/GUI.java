import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.green);
        g.drawString("yes", 20, 30);
    }

}
