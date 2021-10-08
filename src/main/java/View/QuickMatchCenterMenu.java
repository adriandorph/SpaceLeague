package View;

import Controller.Controller;
import Model.BotDifficulty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuickMatchCenterMenu extends VBox {//TODO: find a better name
    private BorderPane individualAndTeamToggleBorderPane;
    private ToggleGroup individualAndTeamToggleGroup;
    private final ToggleButton individualToggle;
    private final ToggleButton teamToggle;
    private BorderPane numOfPlayersBotDifficultyBorderPane;
    private final BorderPane startBorderPane;
    private ComboBox<Integer> numOfPLayersDropDown;
    private final HBox unfairHBox;
    private final HBox numberOfPlayersHBox;
    private boolean numberOfPlayersIsOption;

    private static final double gameFormatLabelFontSize = 20 * (Controller.windowHeight/720);
    private static final String gameFormatLabelSizeStyle = "-fx-font-size: "+ gameFormatLabelFontSize +"px;";
    private static final Font gameFormatLabelfont = new Font(gameFormatLabelFontSize);

    private static final String dropDownFontSizeStyle = "-fx-font-size: "+ 14 * Controller.windowHeight/720+"px;";


    private static final double individualTeamfontSize = 50*(Controller.windowHeight/720);
    private static final String individualTeamfontSizeStyle = "-fx-font-size: "+ individualTeamfontSize +"px;";
    private static final Font individualTeamfont = new Font(individualTeamfontSize);


    public QuickMatchCenterMenu(){

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
        CheckBox unfairCheckBox = new CheckBox("");
        unfairCheckBox.setStyle(gameFormatLabelSizeStyle); //The font sets the size of the checkbox, because setPrefSize does not work
        unfairCheckBox.setSelected(false);

        unfairHBox.getChildren().add(unfairLabel);
        unfairHBox.getChildren().add(unfairCheckBox);
        unfairHBox.setSpacing(Controller.windowWidth * 0.005);//Spacing


        HBox botDiffucultyHBox = new HBox();
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

        botDiffucultyHBox.getChildren().add(botDifficultyLabel);
        botDiffucultyHBox.getChildren().add(botDifficultyDropDown);
        botDiffucultyHBox.setSpacing(Controller.windowWidth * 0.005);//Spacing

        numOfPlayersBotDifficultyBorderPane.setRight(botDiffucultyHBox);



        updateNumberOfPlayersSettings(true);

        //StartButton
        Button startGameButton = new Button("Start Game");
        MenuTemplate.styleMenuButton(startGameButton);
        startBorderPane = new BorderPane();
        startBorderPane.setCenter(startGameButton);

        startGameButton.setOnAction(e -> {
            try {
                Controller.startGame();
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

}
