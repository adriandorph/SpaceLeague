package Model.Ships;

import javafx.scene.paint.Color;

public class ShipBuilder {
    int startPosition;
    int numberOfShips;
    public Color color;
    public ShipVariant shipVariant;

    public ShipBuilder(int startPosition, int numberOfShips, Color color, ShipVariant shipVariant){
        this.startPosition = startPosition;
        this.numberOfShips = numberOfShips;
        this.color = color;
        this.shipVariant = shipVariant;
    }

    public Ship buildShip() throws Exception {
        return ShipFactory.BuildShip(startPosition, numberOfShips, color, shipVariant);
    }
}
