package Model;

import Controller.Controller;
import Model.Ships.Drawable;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameClock implements Drawable {
    public double time;

    public GameClock(int time){
        this.time = time;
    }

    public void updateClock(double timePassed){
        time -= timePassed;
    }

    private String displayString(){

        int displaySeconds = (int)time %  60;
        int displayMinutes = (int)time / 60;
        if (displaySeconds < 0) displaySeconds = 0;

        StringBuilder displayString = new StringBuilder();
        displayString.append(displayMinutes).append(":");
        if (displaySeconds < 10)displayString.append(0);
        displayString.append(displaySeconds);
        return displayString.toString();
    }

    @Override
    public void draw(GraphicsContext gc) {
            gc.setFill(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(new Font("Roboto", 30 * Controller.factor));
            gc.fillText(displayString(), 1280.0 / 2 * Controller.factor, 80 * Controller.factor);
    }
}
