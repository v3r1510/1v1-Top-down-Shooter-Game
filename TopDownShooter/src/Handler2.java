import java.awt.*;
import java.util.LinkedList;

public class Handler2 {
    LinkedList<GameObject> object=new LinkedList<GameObject>();
    private boolean up=false,down=false,left=false,right=false;

    public void tick(){
        for(int i=0;i<object.size();i++){
            GameObject tempObject2=object.get(i);
            tempObject2.tick();
        }
    }

    public void render(Graphics g){
        for(int i=0;i<object.size();i++){
            GameObject tempObject2=object.get(i);
            tempObject2.render(g);
        }
    }
    public void addObject(GameObject tempObject2){
        object.add(tempObject2);
    }
    public void removeObject(GameObject tempObject2){
        object.remove(tempObject2);
    }
    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean isDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }








}