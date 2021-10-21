package Model.Ships;

import Controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class Team implements Comparable<Team>{
    public Color color;
    public List<Ship> ships;
    public int score;
    private double scoreX;

    public Team(List<Ship> ships, Color color, int numberOfTeams){
        this.color = color;
        this.ships = ships;
        for (Ship ship : ships) {
            ship.setColor(color);
        }
        score = 0;

        short scorePadding = 35;

        switch (numberOfTeams){
            case 2:
                switch (ships.get(0).getStartPosition()) {
                    case 0 -> scoreX = 1280.0 / 2 - scorePadding;
                    case 1, 2 -> scoreX = 1280.0 / 2 + scorePadding;
                }
            break;
            case 3:
                switch (ships.get(0).getStartPosition()) {
                    case 0 -> scoreX = 1280.0 / 2 - 2 * scorePadding;
                    case 1 -> scoreX = 1280.0 / 2;
                    case 2 -> scoreX = 1280.0 / 2 + 2 * scorePadding;
                }
            break;
            case 4:
                switch (ships.get(0).getStartPosition()) {
                    case 0 -> scoreX = 1280.0 / 2 - 3 * scorePadding;
                    case 1 -> scoreX = 1280.0 / 2 - scorePadding;
                    case 2 -> scoreX = 1280.0 / 2 + scorePadding;
                    case 3 -> scoreX = 1280.0 / 2 + 3 * scorePadding;
                }
            break;
            default :
                throw new IllegalArgumentException("Illegal number of teams");
        }

    }

    public void updateScore(){
        int scoreSum = 0;
        for(Ship ship: ships){
            scoreSum += ship.score;
        }
        score = scoreSum;
    }

    @Override
    public int compareTo(Team that) {
        if(this.score > that.score) return 1;
        else if (this.score < that.score) return -1;
        return 0;
    }

    public void drawScore(GraphicsContext gc){
        gc.setFill(color);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 30 * Controller.factor));
        gc.fillText(Integer.toString(score), scoreX * Controller.factor, 40 * Controller.factor);
    }
}
