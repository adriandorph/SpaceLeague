package Model.Ships;

import javafx.scene.paint.Color;

public class ShipBuilder {
    int startPosition;
    int numberOfShips;
    public Color color;
    ShipVariant shipVariant;

    public ShipBuilder(int startPosition, int numberOfShips, Color color, boolean noob, ShipVariant shipVariant){
        this.startPosition = startPosition;
        this.numberOfShips = numberOfShips;
        this.color = color;
        this.shipVariant = shipVariant;
    }

    public Ship buildShip() throws Exception {
        Ship ship;
        switch (shipVariant){
            case BoxShip:
                ship = ShipFactory.BoxShip(startPosition, numberOfShips, color);
                break;
            case MarkIShip:
                ship = ShipFactory.MarkIShip(startPosition, numberOfShips, color);
                break;
            case MarkIIShip:
                ship = ShipFactory.MarkIIShip(startPosition, numberOfShips, color);
                break;
            case AlexI:
                ship = ShipFactory.AlexI(startPosition, numberOfShips, color);
                break;
            default:
                throw new Exception("Ship variant doesn't exist!");
        }
        return ship;
    }

}
