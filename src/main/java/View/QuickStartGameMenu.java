package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuickStartGameMenu extends VBox {//TODO: find a better name
    private BorderPane individualAndTeamToggleBorderPane;
    private ToggleGroup individualAndTeamToggleGroup;
    private ToggleButton individualToggle;
    private ToggleButton teamToggle;

    protected static final double fontSize = 50*(Controller.windowHeight/720);
    protected static final String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
    protected static final Font font = new Font(fontSize);

    public QuickStartGameMenu(){
        individualAndTeamToggleBorderPane = new BorderPane();
        individualAndTeamToggleGroup = new ToggleGroup();

        individualToggle = new ToggleButton("Individual");
        teamToggle = new ToggleButton("Teams");

        individualToggle.setToggleGroup(individualAndTeamToggleGroup);
        individualToggle.setSelected(true);

        individualToggle.setOnAction(e -> {
            if (!individualToggle.isSelected()) individualToggle.setSelected(true);
        });

        teamToggle.setOnAction(e -> {
            if (!teamToggle.isSelected()) teamToggle.setSelected(true);
        });


        teamToggle.setToggleGroup(individualAndTeamToggleGroup);

        individualToggle.setFont(font);
        individualToggle.setStyle(fontSizeStyle);
        individualToggle.setPrefWidth(Controller.windowWidth * 0.28);
        individualToggle.setPrefHeight(Controller.windowHeight * 0.2);

        teamToggle.setFont(font);
        teamToggle.setStyle(fontSizeStyle);
        teamToggle.setPrefWidth(Controller.windowWidth * 0.28);
        teamToggle.setPrefHeight(Controller.windowHeight * 0.2);

        individualAndTeamToggleBorderPane.setLeft(individualToggle);
        individualAndTeamToggleBorderPane.setRight(teamToggle);

        //Position
        setTranslateX(Controller.windowWidth * 0.125);
        setTranslateY(Controller.windowHeight * 0.05);
        setMaxWidth(Controller.windowWidth * 0.6);
        setSpacing(Controller.windowHeight * 0.05);

        Button startGameButton = new Button("Start Game");
        MenuTemplate.styleMenuButton(startGameButton);
        BorderPane startBorderPane = new BorderPane();
        startBorderPane.setCenter(startGameButton);

        startGameButton.setOnAction(e -> {
            try {
                Controller.startGame(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        getChildren().add(individualAndTeamToggleBorderPane);
        getChildren().add(startBorderPane);

    }

}
