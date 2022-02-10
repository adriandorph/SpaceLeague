package View;

import Controller.Controller;
import Model.Ships.Drawable;
import Model.Team;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class GameCanvas extends Canvas {
    private final GraphicsContext gc;

    public GameCanvas(double width, double height, List<Drawable> Objects, List<Team> teams){
        super(width, height);
        gc = getGraphicsContext2D();
        update(Objects, teams);
    }

    public void update(List<Drawable> objects, List<Team> teams){
            gc.save();
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0, getWidth(), getHeight());

            for (Drawable object: objects){
                object.draw(gc);
            }
            for (Team team: teams){
                team.drawScore(gc);
            }
            gc.restore();
    }

    public void gameOver(){
        gc.save();
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 70 * Controller.factor));
        gc.fillText("GAME OVER", 1280.0 / 2 * Controller.factor, (720.0 / 2 - 25) * Controller.factor);
        gc.setFont(new Font("Roboto", 20 * Controller.factor));
        gc.fillText("Press R to restart or ESCAPE to return to menu", 1280.0 / 2 * Controller.factor, (720.0 / 2 + 70) * Controller.factor);
        gc.restore();
    }
}
