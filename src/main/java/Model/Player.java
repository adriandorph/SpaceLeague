package Model;

import Model.Ships.ShipFactory;
import Model.Ships.ShipVariant;
import Model.Ships.ShowShip;
import javafx.scene.paint.Color;

public class Player {
    public String name;
    public ShowShip showShip;

    public Player(String name, ShipVariant shipVariant, Color color){
        this.name = name;
        this.showShip = ShipFactory.BuildShowShip(shipVariant, color);
    }

    public Player(String name, Color color){
        this.name = name;
        this.showShip = ShipFactory.BuildShowShip(ShipVariant.DEFAULT, color);
    }

    public void setShowShip(ShowShip showShip){
        this.showShip = showShip;
    }
}
