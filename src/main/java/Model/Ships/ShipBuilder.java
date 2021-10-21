package Model.Ships;

import javafx.scene.paint.Color;

public class ShipBuilder {
    int startPosition;
    int numberOfShips;
    public Color color;
    boolean noob;
    ShipVariant shipVariant;

    public ShipBuilder(int startPosition, int numberOfShips, Color color, boolean noob, ShipVariant shipVariant){
        this.startPosition = startPosition;
        this.numberOfShips = numberOfShips;
        this.color = color;
        this.noob = noob;
        this.shipVariant = shipVariant;
    }

    public Ship buildShip() throws Exception {
        Ship ship;
        switch (shipVariant){
            case BoxShip:
                ship = ShipFactory.BoxShip(startPosition, numberOfShips, color, noob);
                break;
            case MarkIShip:
                ship = ShipFactory.MarkIShip(startPosition, numberOfShips, color, noob);
                break;
            case MarkIIShip:
                ship = ShipFactory.MarkIIShip(startPosition, numberOfShips, color, noob);
                break;
            case AlexI:
                ship = ShipFactory.AlexI(startPosition, numberOfShips, color, noob);
                break;
            default:
                throw new Exception("Ship variant doesn't exist!");
        }
        return ship;
    }

}
