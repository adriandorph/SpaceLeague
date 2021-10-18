package Model;

import Exceptions.NotImplementedException;
import Model.Ships.Ship;
import java.util.List;

public class GameSettings {
    public List<Ship> ships;
    public List<Integer> indexOfPlayers;
    public int time;
    public boolean localMultiplayer;
    public boolean hasBots;
    public BotDifficulty botDifficulty;
    public GameSettings(){
        throw new NotImplementedException();
    }
}
