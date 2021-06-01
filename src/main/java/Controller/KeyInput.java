package Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private static boolean moveForward;
    private static boolean turnRight;
    private static boolean turnLeft;
    private static boolean shoot;

    public KeyInput(){
        moveForward = false;
        turnRight = false;
        turnLeft = false;
        shoot = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) moveForward = true;
        if (key == KeyEvent.VK_A) turnLeft = true;
        if (key == KeyEvent.VK_D) turnRight = true;
        if (key == KeyEvent.VK_SHIFT) shoot = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) moveForward = false;
        if (key == KeyEvent.VK_A) turnLeft = false;
        if (key == KeyEvent.VK_D) turnRight = false;
        if (key == KeyEvent.VK_SHIFT) shoot = false;
        System.out.println(e.getKeyCode());
    }

    public static boolean moveForward() {
        return moveForward;
    }

    public static boolean turnRight() {
        return turnRight;
    }

    public static boolean turnLeft() {
        return turnLeft;
    }

    public static boolean shoot() {
        return shoot;
    }
}
