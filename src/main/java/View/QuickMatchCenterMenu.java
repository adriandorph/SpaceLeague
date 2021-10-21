package View;

import Controller.Controller;
import Model.BotDifficulty;
import Model.GameSettings;
import Model.Ships.Ship;
import Model.Ships.ShipBuilder;
import Model.Ships.ShipFactory;
import Model.Ships.ShipVariant;
import Model.TeamSetting;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuickMatchCenterMenu extends VBox {
    private BorderPane individualAndTeamToggleBorderPane;
    private ToggleGroup individualAndTeamToggleGroup;
    private final ToggleButton individualToggle;
    private final ToggleButton teamToggle;
    private BorderPane numOfPlayersBotDifficultyBorderPane;
    private final BorderPane startBorderPane;
    private ComboBox<Integer> numOfPLayersDropDown;
    private final HBox unfairHBox;
    private final CheckBox unfairCheckBox;
    private final HBox numberOfPlayersHBox;
    private boolean numberOfPlayersIsOption;


    private static final double gameFormatLabelFontSize = 20 * (Controller.windowHeight/720);
    private static final String gameFormatLabelSizeStyle = "-fx-font-size: "+ gameFormatLabelFontSize +"px;";
    private static final Font gameFormatLabelfont = new Font(gameFormatLabelFontSize);

    private static final String dropDownFontSizeStyle = "-fx-font-size: "+ 14 * Controller.windowHeight/720+"px;";


    private static final double individualTeamfontSize = 50*(Controller.windowHeight/720);
    private static final String individualTeamfontSizeStyle = "-fx-font-size: "+ individualTeamfontSize +"px;";
    private static final Font individualTeamfont = new Font(individualTeamfontSize);

    public QuickMatchCenterMenu(boolean localMultiplayer){

        //Individual and team toggles
        individualAndTeamToggleBorderPane = new BorderPane();
        individualAndTeamToggleGroup = new ToggleGroup();

        individualToggle = new ToggleButton("Individual");
        teamToggle = new ToggleButton("Teams");

        individualToggle.setToggleGroup(individualAndTeamToggleGroup);
        individualToggle.setSelected(true);

        individualToggle.setOnAction(e -> {
            if (!individualToggle.isSelected()) individualToggle.setSelected(true);
            updateNumberOfPlayersSettings(numberOfPlayersIsOption);
        });

        teamToggle.setOnAction(e -> {
            if (!teamToggle.isSelected()) teamToggle.setSelected(true);
            updateNumberOfPlayersSettings(numberOfPlayersIsOption);
            numOfPLayersDropDown.setValue(4);
        });


        teamToggle.setToggleGroup(individualAndTeamToggleGroup);

        individualToggle.setFont(individualTeamfont);
        individualToggle.setStyle(individualTeamfontSizeStyle);
        individualToggle.setPrefWidth(Controller.windowWidth * 0.28);
        individualToggle.setPrefHeight(Controller.windowHeight * 0.2);

        teamToggle.setFont(individualTeamfont);
        teamToggle.setStyle(individualTeamfontSizeStyle);
        teamToggle.setPrefWidth(Controller.windowWidth * 0.28);
        teamToggle.setPrefHeight(Controller.windowHeight * 0.2);

        individualAndTeamToggleBorderPane.setLeft(individualToggle);
        individualAndTeamToggleBorderPane.setRight(teamToggle);



        //Number of players and bot difficulty settings
        numOfPlayersBotDifficultyBorderPane = new BorderPane();

        numberOfPlayersHBox = new HBox();
        Label numOfPlayersLabel = new Label("Number of players:");
        numOfPlayersLabel.setFont(gameFormatLabelfont);
        numOfPlayersLabel.setStyle(gameFormatLabelSizeStyle);

        numOfPLayersDropDown = new ComboBox<>();
        ObservableList<Integer> numberOfPlayersList = numOfPLayersDropDown.getItems();
        for (int i = 2; i <= 4; i++){
            numberOfPlayersList.add(i);
        }
        numOfPLayersDropDown.setValue(2);
        numOfPLayersDropDown.setOnAction(e -> updatePlayers(numOfPLayersDropDown.valueProperty().getValue()));
        numOfPLayersDropDown.setStyle(dropDownFontSizeStyle);

        numberOfPlayersHBox.getChildren().add(numOfPlayersLabel);
        numberOfPlayersHBox.getChildren().add(numOfPLayersDropDown);
        numberOfPlayersHBox.setSpacing(Controller.windowWidth * 0.005);//Spacing

        unfairHBox = new HBox();
        Label unfairLabel = new Label("Unfair:");
        unfairLabel.setFont(gameFormatLabelfont);
        unfairLabel.setStyle(gameFormatLabelSizeStyle);
        unfairCheckBox = new CheckBox("");
        unfairCheckBox.setStyle(gameFormatLabelSizeStyle); //The font sets the size of the checkbox, because setPrefSize does not work
        unfairCheckBox.setSelected(false);

        if(!localMultiplayer){
            unfairHBox.getChildren().add(unfairLabel);
            unfairHBox.getChildren().add(unfairCheckBox);
            unfairHBox.setSpacing(Controller.windowWidth * 0.005);//Spacing
        }

        HBox botDifficultyHBox = new HBox();
        Label botDifficultyLabel = new Label("Bot difficulty:");
        botDifficultyLabel.setFont(gameFormatLabelfont);
        botDifficultyLabel.setStyle(gameFormatLabelSizeStyle);

        ComboBox<BotDifficulty> botDifficultyDropDown = new ComboBox<>();
        ObservableList<BotDifficulty> botDifficultyList = botDifficultyDropDown.getItems();
        botDifficultyList.add(BotDifficulty.EASY);
        botDifficultyList.add(BotDifficulty.MEDIUM);
        botDifficultyList.add(BotDifficulty.HARD);
        botDifficultyDropDown.setValue(BotDifficulty.MEDIUM);
        botDifficultyDropDown.setStyle(dropDownFontSizeStyle);

        botDifficultyHBox.getChildren().add(botDifficultyLabel);
        botDifficultyHBox.getChildren().add(botDifficultyDropDown);
        botDifficultyHBox.setSpacing(Controller.windowWidth * 0.005);//Spacing

        numOfPlayersBotDifficultyBorderPane.setRight(botDifficultyHBox);



        updateNumberOfPlayersSettings(true);



        //StartButton
        Button startGameButton = new Button("Start Game");
        MenuTemplate.styleMenuButton(startGameButton);
        startBorderPane = new BorderPane();
        startBorderPane.setCenter(startGameButton);

        startGameButton.setOnAction(e -> {

            List<ShipBuilder> shipBuilders = new LinkedList<>();
            try {
                shipBuilders.add(new ShipBuilder(0, numOfPLayersDropDown.getValue(), Color.RED, true, ShipVariant.MarkIShip));
                shipBuilders.add(new ShipBuilder(shipBuilders.size(), numOfPLayersDropDown.getValue(), Color.LIME, false, ShipVariant.MarkIShip));
                shipBuilders.add(new ShipBuilder(shipBuilders.size(), numOfPLayersDropDown.getValue(), Color.AQUA, false, ShipVariant.AlexI));
                shipBuilders.add(new ShipBuilder(shipBuilders.size(), numOfPLayersDropDown.getValue(), Color.YELLOW, false, ShipVariant.BoxShip));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            GameSettings gameSettings = new GameSettings(shipBuilders, getTeamSetting());
            gameSettings.localMultiplayer = localMultiplayer;
            gameSettings.botDifficulty = botDifficultyDropDown.getValue();
            gameSettings.hasBots = true;

            if(localMultiplayer) gameSettings.indexOfPlayers = new ArrayList<>(List.of(0,1));
            else gameSettings.indexOfPlayers = new ArrayList<>(List.of(0));


            gameSettings.shipBuilders = new LinkedList<>();

            for (int i = 0; i<numOfPLayersDropDown.getValue(); i++){
                gameSettings.shipBuilders.add(shipBuilders.get(i));
            }
            gameSettings.time = 150;
            try {
                Controller.startGame(gameSettings);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });



        //Position
        setTranslateX(Controller.windowWidth * 0.125);
        setTranslateY(Controller.windowHeight * 0.05);
        setMaxWidth(Controller.windowWidth * 0.6);
        setSpacing(Controller.windowHeight * 0.05);

        getChildren().add(individualAndTeamToggleBorderPane);
        getChildren().add(numOfPlayersBotDifficultyBorderPane);
        getChildren().add(startBorderPane);

    }

    public void updateNumberOfPlayersSettings(boolean numberOfPlayersIsOption){
        this.numberOfPlayersIsOption = numberOfPlayersIsOption;
        if (teamToggle.isSelected()) numOfPlayersBotDifficultyBorderPane.setLeft(unfairHBox);
        else if (numberOfPlayersIsOption) numOfPlayersBotDifficultyBorderPane.setLeft(numberOfPlayersHBox);
        else numOfPlayersBotDifficultyBorderPane.setLeft(null);
    }

    public void updatePlayers(int numOfPlayers){

    }

    private TeamSetting getTeamSetting(){
        if(individualAndTeamToggleGroup.getSelectedToggle() == individualToggle){
            return TeamSetting.INDIVIDUAL;
        } //If not individual, team is selected
        else if (unfairCheckBox.isSelected()){
            return TeamSetting.UNFAIR;
        } else {
            return TeamSetting.COOP;
        }
    }

}
