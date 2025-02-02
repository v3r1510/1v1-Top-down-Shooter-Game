import java.awt.*;
public class Enemy extends GameObject {
    Handler2 handler2;
    private HealthBar healthBar;
    private int health = 100;  // Track health internally
    public Enemy(int x, int y, ID id, Handler2 handler2) {
        super(x, y, id);
        this.handler2  = handler2;
        this.healthBar = new HealthBar();
    }
    public void tick(){
        x+=velX;
        y+=velY;

        collision();
        //movement
        if(handler2.isUp()) velY= -5;
        else if(!handler2.isDown()) velY= 0;
        if(handler2.isDown()) velY= 5;
        else if(!handler2.isUp()) velY= 0;
        if(handler2.isRight()) velX= 5;
        else if(!handler2.isLeft()) velX= 0;
        if(handler2.isLeft()) velX= -5;
        else if(!handler2.isRight()) velX= 0;

    }

    private void collision() {
        for (int i = 0; i < handler2.object.size(); i++) {
            GameObject tempObject2 = handler2.object.get(i);
            if(tempObject2.getID() == ID.Block) {
                if(getBounds().intersects(tempObject2.getBounds())) {
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
            System.out.println("Enemy has died!"); // Placeholder for death logic
            handler2.removeObject(this); // Remove the soldier from the game
        }
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
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
