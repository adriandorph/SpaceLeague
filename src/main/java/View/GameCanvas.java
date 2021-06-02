package View;

import Controller.Controller;
import Model.Ships.Drawable;
import Model.Ships.Ship;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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

    public void gameOver(){
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 70 * Controller.factor));
        gc.fillText("GAME OVER", 1280.0 / 2 * Controller.factor, (720.0 / 2 - 25) * Controller.factor);
        gc.setFont(new Font("Roboto", 20 * Controller.factor));
        gc.fillText("Press R to restart or BACKSPACE to quit", 1280.0 / 2 * Controller.factor, (720.0 / 2 + 70) * Controller.factor);
    }
}
