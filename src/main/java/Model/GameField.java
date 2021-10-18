package Model;

import Controller.Controller;
import Controller.*;
import Exceptions.UnfairException;
import Model.Ships.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameField {
    private List<Ship> ships;
    private List<Bot> bots;
    private GameClock clock;
    private List<Integer> indexOfPlayers;
    boolean localMultiplayer;
    boolean hasBots;

    //Currently used
    public GameField(GameSettings gameSettings) throws UnfairException {
        //Check for fairness of ships
        String className = ships.get(0).getClassName();
        for (Ship ship: ships){
            if (!ship.getClassName().equals(className)) throw new UnfairException();
        }

        this.indexOfPlayers = gameSettings.indexOfPlayers;
        this.localMultiplayer = gameSettings.localMultiplayer;
        this.ships = gameSettings.ships;
        bots = new ArrayList<>();
        for (int i = 0; i< ships.size(); i++){
            if (!indexOfPlayers.contains(i)){
                switch (gameSettings.botDifficulty) {
                    case EASY:
                        EasyBot easyBot = new EasyBot(ships.get(i));
                        bots.add(easyBot);
                        break;
                    case MEDIUM:
                        MediumBot mediumBot = new MediumBot(ships.get(i));
                        bots.add(mediumBot);
                        break;
                    case HARD:
                        HardBot hardBot = new HardBot(ships.get(i));
                        bots.add(hardBot);
                        break;
                    default:
                        throw new RuntimeException("BotDifficulty not set properly.");
                }
                EasyBot easyBot = new EasyBot(ships.get(i));
                bots.add(easyBot);
            }
        }
        this.hasBots = gameSettings.hasBots;
        clock = new GameClock(gameSettings.time);

    }

    public GameField(boolean host, List<Ship> ships, int time) throws UnfairException {
        //Check for fairness of ships
        String className = ships.get(0).getClassName();
        for (Ship ship: ships){
            if (!ship.getClassName().equals(className)) throw new UnfairException();
        }
        this.indexOfPlayers = new ArrayList<>(List.of(0));
        this.ships = ships;
        localMultiplayer = false;
        clock = new GameClock(time);
    }

    public void update(double time){//Lav om så til any
        if (localMultiplayer) localMultiplayerInput(ships.get(indexOfPlayers.get(1))); //player 2
        else playerInputs(ships.get(indexOfPlayers.get(0)));//player 1
        if (hasBots) botInputs();
        for (Ship ship: ships){
            ship.update(time);
        }
        shotCollision(ships);
        clock.updateClock(time);
    }

    private void playerInputs(Ship ship){
        ship.setMoveForward(Controller.moveForward());
        ship.setTurnLeft(Controller.turnLeft());
        ship.setTurnRight(Controller.turnRight());
        ship.setShoot(Controller.shoot());
    }

    private void localMultiplayerInput(Ship ship){
        playerInputs(ships.get(indexOfPlayers.get(0)));

        ship.setMoveForward(Controller.moveForward2());
        ship.setTurnLeft(Controller.turnLeft2());
        ship.setTurnRight(Controller.turnRight2());
        ship.setShoot(Controller.shoot2());
    }

    private void botInputs(){
        for (int i = 0; i<bots.size(); i++){
            Ship[] opponents = new Ship[ships.size()-1];
            int index = 0;
            for (Ship ship: ships){
                if (ship != bots.get(i).ship){
                    opponents[index] = ship;
                    index++;
                }
            }
            bots.get(i).update(opponents);
        }
    }

    private void shotCollision(List<Ship> ships){
        for (Ship ship: ships) {
            List<Shot> hitShots = new LinkedList<>();
            for (Ship otherShip: ships){
                if (otherShip != ship){
                    for (Shot shot : ship.getShots()) {
                        if (CollisionDetection.isColliding(shot, otherShip)) {
                            hitShots.add(shot);
                            ship.score++;
                        }
                    }
                }
            }
            ship.destroyShots(hitShots);
        }
    }

    public List<Ship> getShips(){
        return ships;
    }
    public List<Drawable> getAllObjects() {
        List<Drawable> allObjects = new LinkedList<>();
        for (Ship ship: ships){
            allObjects.addAll(ship.getShots());
            allObjects.add(ship);
        }
        allObjects.add(clock);
        return allObjects;
    }

    public double getGameTime(){
        return clock.time;
    }
}
