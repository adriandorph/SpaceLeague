package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.Arrays;

public class MainMenu extends MenuTemplate {

    public MainMenu(){
        Button playButton = new Button("Play");
        Button hangarButton = new Button("Hangar");
        Button profileButton = new Button("Profile");
        Button friendsButton = new Button("Friends");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit Game");

        //Button functionality
        playButton.setOnAction(e -> Controller.playMenu());

        hangarButton.setOnAction(e -> System.out.println("Hangar"));

        profileButton.setOnAction(e ->System.out.println("Profile"));

        friendsButton.setOnAction(e -> System.out.println("Friends"));

        settingsButton.setOnAction(e ->System.out.println("Settings"));

        exitButton.setOnAction(e ->{
            Platform.exit();
            System.exit(0);
        });

        for (Button button: Arrays.asList(playButton, hangarButton, profileButton, friendsButton, settingsButton, exitButton)){
            styleMenuButton(button);
            menuButtonsVBox.getChildren().add(button);
        }
    }
}
