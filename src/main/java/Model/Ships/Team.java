package Model.Ships;

import javafx.scene.paint.Color;

import java.util.List;

public class Team {
    public Color color;
    public List<Ship> ships;
    public int score;

    public Team(List<Ship> ships, Color color){
        this.color = color;
        this.ships = ships;
        for (Ship ship : ships) {
            ship.setColor(color);
        }
        score = 0;
    }
}
