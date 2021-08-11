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

import java.util.Arrays;

public class Menu {

    public static void mainMenuBackground(Scene scene, StackPane stackPane, double width, double height){
        scene.getStylesheets().add("styling/menuButtons.css");



        Canvas background = new Canvas(width, height);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);
        stackPane.getChildren().add(background);
    }

    public static void initializeMainMenu(Scene scene, StackPane stackPane, double width, double height, Controller controller){
        mainMenuBackground(scene, stackPane, width, height);
        VBox buttonVBox = new VBox();
        //Button position
        buttonVBox.setTranslateY(height * 0.45);
        buttonVBox.setTranslateX(width * 0.05);
        buttonVBox.setSpacing(height * 0.02);

        mainMenuButtons(buttonVBox, width, height, controller);

        stackPane.getChildren().add(buttonVBox);
    }

    private static void mainMenu(VBox buttonVBox, double width, double height, Controller controller){
        buttonVBox.getChildren().clear();
        mainMenuButtons(buttonVBox, width, height, controller);
        Controller.primaryStage.show();
    }

    private static void mainMenuButtons(VBox buttonVBox, double width, double height, Controller controller){
        double fontSize = 24*(height/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font("Calibri", fontSize);

        Button playButton = new Button("Play");
        Button hangarButton = new Button("Hangar");
        Button profileButton = new Button("Profile");
        Button friendsButton = new Button("Friends");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit Game");

        //Button functionality
        playButton.setOnAction(e ->{
            playMenu(buttonVBox, width, height, controller);
            /*
            try {
                controller.startGame(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
             */
        });

        hangarButton.setOnAction(e ->{
            System.out.println("Hangar");
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
        for (Button button : Arrays.asList(playButton, hangarButton, profileButton, friendsButton, settingsButton, exitButton)) {
            button.setPrefWidth(width * 0.2);
        }


        //Button height
        for (Button button : Arrays.asList(playButton, hangarButton, profileButton, friendsButton, settingsButton, exitButton)) {
            button.setPrefHeight(height * 0.065);
        }

        //Button appearance
        for (Button button : Arrays.asList(playButton, hangarButton, profileButton, friendsButton, settingsButton, exitButton)) {
            button.setFont(font);
            button.setStyle(fontSizeStyle);
        }

        //Add buttons to VBox
        for (Button button : Arrays.asList(playButton, hangarButton, profileButton, friendsButton, settingsButton, exitButton)) {
            buttonVBox.getChildren().add(button);
        }
    }


    private static void playMenu(VBox buttonVBox, double width, double height, Controller controller){
        buttonVBox.getChildren().clear();
        playMenuButtons(buttonVBox, width, height, controller);
        Controller.primaryStage.show();
    }

    public static void playMenu(Scene scene, StackPane stackPane, double width, double height, Controller controller){
        mainMenuBackground(scene, stackPane, width, height);
        VBox buttonVBox = new VBox();
        buttonVBox.setTranslateY(height * 0.45);
        buttonVBox.setTranslateX(width * 0.05);
        buttonVBox.setSpacing(height * 0.02);

        playMenuButtons(buttonVBox, width, height, controller);
        stackPane.getChildren().add(buttonVBox);
    }

    public static void playMenuButtons(VBox buttonVBox, double width, double height, Controller controller){
        double fontSize = 24*(height/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font("Calibri", fontSize);


        Button careerButton = new Button("Career Mode");
        Button quickMatchButton = new Button("Quick Match");
        Button multiplayerButton = new Button("Multiplayer");
        Button mainMenuButton = new Button("Main Menu");

        //Button functionality
        careerButton.setOnAction(e ->{
            System.out.println("Career Mode");
        });

        quickMatchButton.setOnAction(e ->{
            System.out.println("Quick Match");
            try {
                controller.startGame(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        multiplayerButton.setOnAction(e ->{
            System.out.println("Multiplayer");
        });

        mainMenuButton.setOnAction(e ->{
            System.out.println("Main Menu");
            mainMenu(buttonVBox, width, height, controller);
        });


        //Button width
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton, mainMenuButton)) {
            button.setPrefWidth(width * 0.2);
        }


        //Button height
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton, mainMenuButton)) {
            button.setPrefHeight(height * 0.065);
        }

        //Button appearance
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton, mainMenuButton)) {
            button.setFont(font);
            button.setStyle(fontSizeStyle);
        }

        //Add button to VBox
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton, mainMenuButton)) {
            buttonVBox.getChildren().add(button);
        }
    }


}
