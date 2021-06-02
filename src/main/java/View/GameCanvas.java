package View;

import Model.Ships.Drawable;
import Model.Ships.Ship;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;

    public GameCanvas(double width, double height, List<Drawable> Objects, List<Ship> ships){
        super(width, height);
        gc = getGraphicsContext2D();
        update(Objects, ships);
    }

    public void update(List<Drawable> objects, List<Ship> ships){
        gc.save();
        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(0,0, getWidth(), getHeight());
        for (Drawable object: objects){
            object.draw(gc);
        }
        for (Ship ship: ships){
            ship.drawScore(gc);
        }
        gc.restore();
    }
}
