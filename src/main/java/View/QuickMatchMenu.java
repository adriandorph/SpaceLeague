package View;

import Controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;

public class QuickMatchMenu extends MenuTemplate {
    public QuickMatchMenu(){
        ToggleGroup toggleGroup = new ToggleGroup();

        ToggleButton deathMatchButton = new ToggleButton("Death Match");
        ToggleButton captureButton = new ToggleButton("Capture the flag");
        ToggleButton spaceBallButton = new ToggleButton("Space Ball");
        ToggleButton goldRushButton = new ToggleButton("Gold Rush");

        //Button functionality

        for (ToggleButton button: Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)){
            button.setToggleGroup(toggleGroup);
            styleMenuButton(button);
            menuButtonsVBox.getChildren().add(button);
        }

        deathMatchButton.setSelected(true);

        Button backButton = backButton();

        backButton.setOnAction(e -> Controller.playMenu());
        menuButtonsVBox.getChildren().add(backButton);
    }
}
