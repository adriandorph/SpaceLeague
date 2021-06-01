package View;

import Model.Ships.Drawable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;

    public GameCanvas(double width, double height, List<Drawable> objects){
        super(width, height);
        gc = getGraphicsContext2D();
        update(objects);
    }

    public void update(List<Drawable> objects){
        gc.save();
        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(0,0, getWidth(), getHeight());
        gc.setFill(Color.rgb(255,255,255)); // flyttes ud i draw metoderne
        for (Drawable object: objects){
            object.draw(gc);
        }
        gc.restore();
    }
}
