import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Handler2 handler2;

    private BufferedImage level = null;

    public Game() {
        new Window(993, 1016, "Game", this);
        start();

        handler=new Handler();
        handler2=new Handler2();

        this.addKeyListener(new KeyInput(handler, handler2));

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/level.png");

        loadLevel(level);
    }



    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        handler.tick();
        handler2.tick();

    }
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///

        g.setColor(Color.red);
        g.fillRect(0, 0, 960, 960);
        handler.render(g);
        handler2.render(g);


        ///
        g.dispose();
        bs.show();
    }

    //level load

    private void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;

                if (r == 255 && g == 0 && b == 0) {
                    handler.addObject(new Block(x * 15, y * 15, ID.Block));
                    handler2.addObject(new Block(x * 15, y * 15, ID.Block));
                }

                if (b == 255 && r == 0 && g == 0) {
                    handler2.addObject(new Enemy(x * 15, y * 15, ID.Player2, handler2));
                }

                if (r == 255 && b == 255 && g == 0) {
                    handler.addObject(new Soldier(x * 15, y * 15, ID.Player, handler));
                }
            }
        }




    }


    public static void main(String[] args) {
        new Game();
    }
}