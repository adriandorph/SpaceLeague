package Model;

import Model.Ships.Ship;
import Model.Ships.ShipBuilder;
import Model.Ships.ShipFactory;

import java.util.LinkedList;
import java.util.List;

public class GameSettings {
    public List<ShipBuilder> shipBuilders;
    public List<Integer> indexOfPlayers;
    public int time;
    public boolean localMultiplayer;
    public boolean hasBots;
    public BotDifficulty botDifficulty;
    public GameSettings(){
        //throw new NotImplementedException();
    }

    public List<Ship> getShips() throws Exception {
        List<Ship> ships = new LinkedList<>();
        for(ShipBuilder shipBuilder: shipBuilders){
            ships.add(shipBuilder.buildShip());
        }
        return ships;
    }
}
