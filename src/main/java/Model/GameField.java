package Model;

import Controller.Controller;
import Exceptions.UnfairException;
import Model.Ships.*;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private List<Drawable> allObjects;
    private FlyableShip player1;
    private Ship player2;
    boolean host;

    public GameField(boolean host, FlyableShip player1, Ship player2) throws UnfairException {
        if (!player1.getClassName().equals(player2.getClassName())) throw new UnfairException();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void update(double time){
        player1.setMoveForward(Controller.moveForward());
        player1.setTurnLeft(Controller.turnLeft());
        player1.setTurnRight(Controller.turnRight());
        player1.setShoot(Controller.shoot());
        player1.update(time);

        player2.update(time);
    }

    public List<Drawable> getAllObjects() {
        allObjects = new ArrayList<>();
        allObjects.addAll(player1.getShots());
        allObjects.addAll(player2.getShots());
        allObjects.add(player1);
        allObjects.add(player2);
        return allObjects;
    }
}
