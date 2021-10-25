package View;

import Model.Player;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TeamSelectionBox extends HBox {
    private VBox colorPick;
    private ColorPicker colorPicker;
    private List<PlayerShipStatBox> playerShipStatBoxes;
    private List<PlayerShipStatBox> players;

    public TeamSelectionBox(List<Player> players){
        //For player in players add change ship button
        setPlayers(players);

        colorPicker = new ColorPicker();
        colorPicker.setOnAction(e ->{
            changeColor(colorPicker.getValue());
        });

        this.getChildren().addAll(playerShipStatBoxes);
    }

    public TeamSelectionBox(List<Player> players, Color color){
        new TeamSelectionBox(players);
        colorPicker.setValue(color);
    }

    public void setPlayers(List<Player> players){
        playerShipStatBoxes = new ArrayList<>();
        for(Player player: players){
            playerShipStatBoxes.add(new PlayerShipStatBox(player));
        }
    }

    public void changeColor(Color color){
        for(PlayerShipStatBox p: playerShipStatBoxes){
            //p.setColor(color);
        }

    }


}
