package Model.Package;

import Model.GameMode;
import Model.TeamSetting;

public class Career {
    private GameMode gameMode;
    private TeamSetting teamSetting;
    private int numberOfPlayers;
    private int wins;
    private int losses;

    public Career(GameMode gameMode, TeamSetting teamSetting, int numberOfPlayers){
        this.gameMode = gameMode;
        this.teamSetting = teamSetting;
        this.numberOfPlayers = numberOfPlayers;
    }
}
