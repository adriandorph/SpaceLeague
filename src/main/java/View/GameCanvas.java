package View;

import Controller.Controller;
import Model.Ships.Ship;
import Model.Ships.StartPosition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;
    public GameCanvas(int width, int height){
        super(width, height);
        gc = getGraphicsContext2D();
        update();
    }

    public void update(){
        gc.save();
        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(0,0, getWidth(), getHeight());
        gc.setFill(Color.rgb(255,255,255));
        Ship ship1 = new Ship(1,1,1,"S-class","", new double[]{-10.0, 10.0, 10.0, -10.0}, new double[]{20.0, 20.0, -20.0, -20.0});
        ship1.startPosition(StartPosition.PLAYER1);
        ship1.draw(gc);
        gc.restore();
    }
}
