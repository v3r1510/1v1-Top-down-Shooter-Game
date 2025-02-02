import java.awt.*;
import java.awt.event.KeyEvent;

public class SemiAuto extends GameObject{
    private Handler handler;
    private Handler2 handler2;
    private int currentDirection1;
    private int currentDirection2;

    public SemiAuto(int x, int y, ID id, Handler handler, Handler2 handler2, int currentDirection1, int currentDirection2) {
        super(x, y, id);
        this.handler = handler;
        this.handler2 = handler2;
        this.currentDirection1 = currentDirection1;
        this.currentDirection2 = currentDirection2;
    }

    public void tick() {


        if (currentDirection1 == KeyEvent.VK_W) {
            y += velY - 10;
        }
        if (currentDirection1 == KeyEvent.VK_S) {
            y -= velY - 10;
        }
        if (currentDirection1 == KeyEvent.VK_A) {
            x -= velX + 10;
        }
        if (currentDirection1 == KeyEvent.VK_D || currentDirection1 == -1) {
            x += velX + 10;
        }

        if (currentDirection2 == KeyEvent.VK_UP) {
            y += velY - 10;
        }
        if (currentDirection2 == KeyEvent.VK_DOWN) {
            y -= velY - 10;
        }
        if (currentDirection2 == KeyEvent.VK_LEFT) {
            x -= velX + 10;
        }
        if (currentDirection2 == KeyEvent.VK_RIGHT || currentDirection2 == -1) {
            x += velX + 10;
        }
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject instanceof Soldier) {
                Soldier soldier = (Soldier) tempObject;
                if (getBounds().intersects(soldier.getBounds())) {
                    soldier.takeDamage(10); // Reduce health
                    handler.removeObject(this); // Remove bullet
                    return;
                }
            } else if (tempObject instanceof Enemy) {
                Enemy enemy = (Enemy) tempObject;
                if (getBounds().intersects(enemy.getBounds())) {
                    enemy.takeDamage(10); // Reduce health
                    handler.removeObject(this); // Remove bullet
                    return;
                }
            }
        }

        for (int i = 0; i < handler2.object.size(); i++) {
            GameObject tempObject = handler2.object.get(i);
            if (tempObject instanceof Soldier) {
                Soldier soldier = (Soldier) tempObject;
                if (getBounds().intersects(soldier.getBounds())) {
                    soldier.takeDamage(10); // Reduce health
                    handler2.removeObject(this); // Remove bullet
                    return;
                }
            } else if (tempObject instanceof Enemy) {
                Enemy enemy = (Enemy) tempObject;
                if (getBounds().intersects(enemy.getBounds())) {
                    enemy.takeDamage(10); // Reduce health
                    handler2.removeObject(this); // Remove bullet
                    return;
                }
            }
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }

        for (int i = 0; i < handler2.object.size(); i++) {
            GameObject tempObject = handler2.object.get(i);
            if (tempObject.getID() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler2.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x,y,8,8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }
}
