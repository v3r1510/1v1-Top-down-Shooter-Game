import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    int previousKey = -1;
    int previousKey2 = -1;
    Handler handler;
    Handler2 handler2;
    public KeyInput(Handler handler, Handler2 handler2) {
        this.handler = handler;
        this.handler2 = handler2;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();;
        for(int i=0;i<handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);


            if (tempObject.getID() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    handler.setUp(true);
                    previousKey = KeyEvent.VK_W;
                }
                if (key == KeyEvent.VK_S) {
                    handler.setDown(true);
                    previousKey = KeyEvent.VK_S;
                }
                if (key == KeyEvent.VK_A) {
                    handler.setLeft(true);
                    previousKey = KeyEvent.VK_A;
                }

                if (key == KeyEvent.VK_D) {
                    handler.setRight(true);
                    previousKey = KeyEvent.VK_D;
                }

                if (key == KeyEvent.VK_SPACE){
                }
            }

        }
        for (int i = 0; i < handler2.object.size(); i++) {
            GameObject tempObject2= handler2.object.get(i);

            if (tempObject2.getID() == ID.Player2) {
                if (key == KeyEvent.VK_UP) {
                    handler2.setUp(true);
                    previousKey2 = KeyEvent.VK_UP;
                }
                if (key == KeyEvent.VK_DOWN) {
                    handler2.setDown(true);
                    previousKey2 = KeyEvent.VK_DOWN;
                }
                if (key == KeyEvent.VK_LEFT){
                    handler2.setLeft(true);
                    previousKey2 = KeyEvent.VK_LEFT;
                }
                if (key == KeyEvent.VK_RIGHT){
                    handler2.setRight(true);
                    previousKey2 = KeyEvent.VK_RIGHT;
                }
                if (key == KeyEvent.VK_ENTER){

                }

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i=0;i<handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID()==ID.Player) {
                if(key==KeyEvent.VK_W) handler.setUp(false);
                if(key==KeyEvent.VK_S) handler.setDown(false);
                if(key==KeyEvent.VK_A) handler.setLeft(false);
                if(key==KeyEvent.VK_D) handler.setRight(false);
                if(key==KeyEvent.VK_SPACE){
                    if(previousKey == KeyEvent.VK_W){
                        handler.addObject(new SemiAuto(tempObject.getX() + 16, tempObject.getY() - 32, ID.SemiAuto, handler, handler2, previousKey, 0));
                    } else if(previousKey == KeyEvent.VK_S){
                        handler.addObject(new SemiAuto(tempObject.getX() + 16, tempObject.getY() + 50, ID.SemiAuto, handler, handler2, previousKey, 0));
                    } else if(previousKey == KeyEvent.VK_A){
                        handler.addObject(new SemiAuto(tempObject.getX(), tempObject.getY() + 24, ID.SemiAuto, handler, handler2, previousKey, 0));
                    } else if(previousKey == KeyEvent.VK_D){
                        handler.addObject(new SemiAuto(tempObject.getX() + 30, tempObject.getY() + 24, ID.SemiAuto, handler, handler2, previousKey, 0));
                    }
                }

            }

        }

        for (int i = 0; i < handler2.object.size(); i++) {
            GameObject tempObject2= handler2.object.get(i);
            if (tempObject2.getID() == ID.Player2) {
                if (key == KeyEvent.VK_UP) handler2.setUp(false);
                if (key == KeyEvent.VK_DOWN) handler2.setDown(false);
                if (key == KeyEvent.VK_LEFT) handler2.setLeft(false);
                if (key == KeyEvent.VK_RIGHT) handler2.setRight(false);
                if (key == KeyEvent.VK_ENTER) {
                    if (previousKey2 == KeyEvent.VK_UP) {
                        handler.addObject(new SemiAuto(tempObject2.getX() + 16, tempObject2.getY() - 32, ID.SemiAuto, handler, handler2, 0, previousKey2));
                    } else if (previousKey2 == KeyEvent.VK_DOWN) {
                        handler.addObject(new SemiAuto(tempObject2.getX() + 16, tempObject2.getY() + 50, ID.SemiAuto, handler, handler2, 0, previousKey2));
                    } else if (previousKey2 == KeyEvent.VK_LEFT) {
                        handler.addObject(new SemiAuto(tempObject2.getX(), tempObject2.getY() + 24, ID.SemiAuto, handler, handler2, 0, previousKey2));
                    } else if (previousKey2 == KeyEvent.VK_RIGHT) {
                        handler.addObject(new SemiAuto(tempObject2.getX() + 30, tempObject2.getY() + 24, ID.SemiAuto, handler, handler2, 0, previousKey2));
                    }

                }

            }
        }
    }
}
