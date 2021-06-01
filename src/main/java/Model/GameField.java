package Model;

import Exceptions.UnfairException;
import Model.Ships.*;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private List<Drawable> allObjects;
    private List<Shot> shots;
    private FlyableShip player1;
    private Ship player2;
    boolean host;

    public GameField(boolean host, FlyableShip player1, Ship player2) throws UnfairException {
        if (!player1.getClassName().equals(player2.getClassName())) throw new UnfairException();
        this.player1 = player1;
        this.player2 = player2;
        shots = new ArrayList<>();
    }

    public List<Drawable> getAllObjects() {
        allObjects = new ArrayList<>();
        allObjects.addAll(shots);
        allObjects.add(player1);
        allObjects.add(player2);
        return allObjects;
    }
}
