import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Soldier extends GameObject {
    Handler handler;
    private HealthBar healthBar;
    private int health = 100;  // Track health internally

    public Soldier(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.healthBar = new HealthBar();
    }
    public void tick(){
       x+=velX;
       y+=velY;

       collision();
       //movement
       if(handler.isUp()) velY= -5;
       else if(!handler.isDown()) velY= 0;
       if(handler.isDown()) velY= 5;
       else if(!handler.isUp()) velY= 0;
       if(handler.isRight()) velX= 5;
       else if(!handler.isLeft()) velX= 0;
       if(handler.isLeft()) velX= -5;
       else if(!handler.isRight()) velX= 0;

    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Block) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            if(tempObject.getID() == ID.SemiAuto) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
        }
    }
    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
        healthBar.setHealth(health);
        if (health <= 0) {
            System.out.println("Soldier has died!"); // Placeholder for death logic
            handler.removeObject(this); // Remove the soldier from the game
        }
    }




    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,32,48);
        // Draw Health Bar above soldier
        g.setColor(Color.BLACK);
        g.fillRect(x, y - 10, 32, 6);

        g.setColor(Color.GREEN);
        int healthWidth = (int) ((32.0 * health) / 100);
        g.fillRect(x, y - 10, healthWidth, 6);

        g.setColor(Color.WHITE);
        g.drawRect(x, y - 10, 32, 6);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
