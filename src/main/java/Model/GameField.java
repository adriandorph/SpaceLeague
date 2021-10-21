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
    private List<Team> teams;
    private List<Bot> bots;
    private GameClock clock;
    private List<Integer> indexOfPlayers;
    boolean localMultiplayer;
    boolean hasBots;

    //Currently used
    public GameField(GameSettings gameSettings) throws Exception {
        //Check for fairness of ships
        this.ships = new LinkedList<>();
        this.teams = gameSettings.createTeams();
        for(Team team: teams){
            ships.addAll(team.ships);
        }

        String className = ships.get(0).getClassName();
        for (Ship ship: ships){
            if (!ship.getClassName().equals(className)) throw new UnfairException();
        }

        this.indexOfPlayers = gameSettings.indexOfPlayers;
        this.localMultiplayer = gameSettings.localMultiplayer;
        bots = new ArrayList<>();
        for (int i = 0; i< ships.size(); i++){
            if (!indexOfPlayers.contains(i)){
                switch (gameSettings.botDifficulty) {
                    case EASY:
                        EasyBot easyBot = new EasyBot(ships.get(i));
                        bots.add(easyBot);
                        break;
                    case MEDIUM:
                        EasyBot mediumBot = new EasyBot(ships.get(i));
                        bots.add(mediumBot);
                        break;
                    case HARD:
                        EasyBot hardBot = new EasyBot(ships.get(i));
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

    public void update(double time){
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
            Ship[] opponents = opponents(bots.get(i).ship).toArray(new Ship[0]);
            bots.get(i).update(opponents);
        }
    }

    private void shotCollision(List<Ship> ships) {
        for (Team team : teams) {
            List<Ship> otherShips = opponents(team.ships.get(0));
            for (Ship ship : team.ships) {//Ship in one team
                List<Shot> hitShots = new LinkedList<>();

                for (Ship otherShip : otherShips) {//Ships not on the team
                    for (Shot shot : ship.getShots()) {
                        if (CollisionDetection.isColliding(shot, otherShip)) {
                            hitShots.add(shot);
                            ship.score++;
                            team.updateScore();
                        }
                    }
                }
                ship.destroyShots(hitShots);
            }
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

    private List<Ship> opponents(Ship ship){
        List<Ship> opponents = new LinkedList<>();
        for(Team team: teams){
            if(!team.ships.contains(ship)){
                opponents.addAll(team.ships);
            }
        }

        return opponents;
    }

    public List<Team> getTeams(){
        return teams;
    }
}
