package View;

import Controller.Controller;
import javafx.scene.control.Button;

import java.util.Arrays;

public class PlayMenu extends MenuTemplate {

    public PlayMenu(){
        Button careerButton = new Button("Career Mode");
        Button quickMatchButton = new Button("Quick Match");
        Button multiplayerButton = new Button("Multiplayer");
        Button backButton = backButton();

        //Button functionality
        careerButton.setOnAction(e -> System.out.println("Career Mode"));

        quickMatchButton.setOnAction(e -> Controller.quickMatchMenu());

        multiplayerButton.setOnAction(e -> System.out.println("Multiplayer"));

        backButton.setOnAction(e -> Controller.mainMenu());

        for (Button button: Arrays.asList(careerButton, quickMatchButton, multiplayerButton)){
            styleMenuButton(button);
            menuButtonsVBox.getChildren().add(button);
        }

        menuButtonsVBox.getChildren().add(backButton);

    }
}
