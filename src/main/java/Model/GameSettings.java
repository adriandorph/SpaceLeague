package Model;

import Model.Ships.Ship;
import Model.Ships.ShipBuilder;
import Model.Ships.Team;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class GameSettings {
    public List<ShipBuilder> shipBuilders;
    public List<Integer> indexOfPlayers;
    public int time;
    public boolean localMultiplayer;
    public boolean hasBots;
    public BotDifficulty botDifficulty;
    public TeamSetting teamSetting;
    public GameSettings(List<ShipBuilder> shipBuilders, TeamSetting teamSetting){
        this.shipBuilders = shipBuilders;
        this.teamSetting = teamSetting;
    }

    public List<Ship> getShips() throws Exception {
        List<Ship> ships = new LinkedList<>();
        for(ShipBuilder shipBuilder: shipBuilders){
            ships.add(shipBuilder.buildShip());
        }
        return ships;
    }

    public List<Team> getTeams() throws Exception {
        List<Team> teams = new LinkedList<>();
        switch (teamSetting){
            case INDIVIDUAL:
                for(ShipBuilder shipBuilder: shipBuilders){
                    Team team = new Team(new LinkedList<>(List.of(shipBuilder.buildShip())), shipBuilder.color);
                    teams.add(team);
                }
                break;
            case UNFAIR:
                List<Ship> shipUnfairTeam1 = new LinkedList<>(List.of(shipBuilders.get(0).buildShip()));
                Color unfairColor1 = shipBuilders.get(0).color;
                List<Ship> shipUnfairTeam2 = new LinkedList<>(List.of(shipBuilders.get(1).buildShip(), shipBuilders.get(2).buildShip()));
                Color unfairColor2 = shipBuilders.get(1).color;
                Team unfairTeam1 = new Team(shipUnfairTeam1, unfairColor1);
                Team unfairTeam2 = new Team(shipUnfairTeam2, unfairColor2);
                teams.add(unfairTeam1);
                teams.add(unfairTeam2);
                break;
            case COOP:
                List<Ship> shipTeam1 = new LinkedList<>(List.of(shipBuilders.get(0).buildShip(), shipBuilders.get(1).buildShip()));
                Color color1 = shipBuilders.get(0).color;
                List<Ship> shipTeam2 = new LinkedList<>(List.of(shipBuilders.get(2).buildShip(), shipBuilders.get(3).buildShip()));
                Color color2 = shipBuilders.get(1).color;
                Team team1 = new Team(shipTeam1, color1);
                Team team2 = new Team(shipTeam2, color2);
                teams.add(team1);
                teams.add(team2);
                break;
            default:
                throw new Exception("No TeamSetting has been set...");
        }
        return teams;
    }
}
