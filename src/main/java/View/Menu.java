package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu {

    public static void initializeMainMenu(Scene scene, StackPane stackPane, double width, double height, Controller controller){

        scene.getStylesheets().add("styling/menuButtons.css");

        Canvas background = new Canvas(width, height);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);
        VBox buttonVBox = new VBox();

        Button playButton = new Button("Play");
        Button hangerButton = new Button("Hanger");
        Button profileButton = new Button("Profile");
        Button friendsButton = new Button("Friends");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit Game");

        //Button functionality
        playButton.setOnAction(e ->{
            System.out.println("Play");
            try {
                controller.startGame(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        hangerButton.setOnAction(e ->{
            System.out.println("Hanger");
        });

        profileButton.setOnAction(e ->{
            System.out.println("Profile");
        });

        friendsButton.setOnAction(e ->{
            System.out.println("Friends");
        });

        settingsButton.setOnAction(e ->{
            System.out.println("Settings");
        });

        exitButton.setOnAction(e ->{
            System.out.println("Exit");
            Platform.exit();
            System.exit(0);
        });


        //Button width
        playButton.setPrefWidth(width * 0.2);
        hangerButton.setPrefWidth(width * 0.2);
        profileButton.setPrefWidth(width * 0.2);
        friendsButton.setPrefWidth(width * 0.2);
        settingsButton.setPrefWidth(width * 0.2);
        exitButton.setPrefWidth(width * 0.2);


        //Button height
        playButton.setPrefHeight(height * 0.065);
        hangerButton.setPrefHeight(height * 0.065);
        profileButton.setPrefHeight(height * 0.065);
        friendsButton.setPrefHeight(height * 0.065);
        settingsButton.setPrefHeight(height * 0.065);
        exitButton.setPrefHeight(height * 0.065);

        //Button position
        buttonVBox.setTranslateY(height * 0.45);
        buttonVBox.setTranslateX(width * 0.05);
        buttonVBox.setSpacing(height * 0.02);

        //Button appearance
        Font font = new Font("Calibri", 24 * (height/720));
        playButton.setFont(font);
        hangerButton.setFont(font);
        profileButton.setFont(font);
        friendsButton.setFont(font);
        settingsButton.setFont(font);
        exitButton.setFont(font);

        buttonVBox.getChildren().add(playButton);
        buttonVBox.getChildren().add(hangerButton);
        buttonVBox.getChildren().add(profileButton);
        buttonVBox.getChildren().add(friendsButton);
        buttonVBox.getChildren().add(settingsButton);
        buttonVBox.getChildren().add(exitButton);

        stackPane.getChildren().add(background);
        stackPane.getChildren().add(buttonVBox);
    }
}
