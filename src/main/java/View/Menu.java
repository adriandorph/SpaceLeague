package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.Objects;

public class Menu {

    public static void menuBackground(Scene scene, StackPane stackPane, double width, double height){
        scene.getStylesheets().add("styling/menuButtons.css");


        ImageView spaceLeagueLogo = new ImageView(new Image(Objects.requireNonNull(Controller.class.getClassLoader().getResourceAsStream("icons/spaceLeagueLogo.png"))));
        double dimension = spaceLeagueLogo.getImage().getHeight() / spaceLeagueLogo.getImage().getWidth();
        double imageWidth = 0.2 * width;
        spaceLeagueLogo.setFitWidth(imageWidth);
        spaceLeagueLogo.setFitHeight(imageWidth * dimension);
        Pane logoPane = new Pane(spaceLeagueLogo);
        logoPane.setTranslateX(width * 0.05);
        logoPane.setTranslateY(height * 0.075);

        Canvas background = new Canvas(width, height);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);
        stackPane.getChildren().add(background);
        stackPane.getChildren().add(logoPane);
    }

    public static void mainMenu(Scene scene, StackPane stackPane, double width, double height, Controller controller){
        menuBackground(scene, stackPane, width, height);
        VBox buttonVBox = new VBox();
        //Button position
        buttonVBox.setTranslateX(width * 0.05);
        buttonVBox.setTranslateY(height * 0.55);
        buttonVBox.setSpacing(height * 0.015);

        mainMenuButtons(buttonVBox, width, height, controller);

        stackPane.getChildren().add(buttonVBox);
    }

    private static void mainMenu(VBox buttonVBox, double width, double height, Controller controller){
        buttonVBox.getChildren().clear();
        mainMenuButtons(buttonVBox, width, height, controller);
        Controller.primaryStage.show();
    }

    private static void mainMenuButtons(VBox buttonVBox, double width, double height, Controller controller){
        double fontSize = 17*(height/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font(fontSize);

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

        hangarButton.setOnAction(e -> System.out.println("Hangar"));

        profileButton.setOnAction(e ->System.out.println("Profile"));

        friendsButton.setOnAction(e -> System.out.println("Friends"));

        settingsButton.setOnAction(e ->System.out.println("Settings"));

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
            button.setPrefHeight(height * 0.05);
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
        menuBackground(scene, stackPane, width, height);
        VBox buttonVBox = new VBox();
        buttonVBox.setTranslateX(width * 0.05);
        buttonVBox.setTranslateY(height * 0.55);
        buttonVBox.setSpacing(height * 0.02);

        playMenuButtons(buttonVBox, width, height, controller);
        stackPane.getChildren().add(buttonVBox);
    }

    public static void playMenuButtons(VBox buttonVBox, double width, double height, Controller controller){
        double fontSize = 17*(height/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font(fontSize);


        Button careerButton = new Button("Career Mode");
        Button quickMatchButton = new Button("Quick Match");
        Button multiplayerButton = new Button("Multiplayer");

        //Button functionality
        careerButton.setOnAction(e -> System.out.println("Career Mode"));

        quickMatchButton.setOnAction(e ->{
            quickMatchMenu(buttonVBox,width,height,controller);
            /*
            try {
                controller.startGame(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
             */
        });

        multiplayerButton.setOnAction(e -> System.out.println("Multiplayer"));


        //Button width
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton)) {
            button.setPrefWidth(width * 0.2);
        }


        //Button height
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton)) {
            button.setPrefHeight(height * 0.05);
        }

        //Button appearance
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton)) {
            button.setFont(font);
            button.setStyle(fontSizeStyle);
        }

        //Add button to VBox
        for (Button button : Arrays.asList(careerButton, quickMatchButton, multiplayerButton)) {
            buttonVBox.getChildren().add(button);
        }

        //BackButton
        Button backButton = backButton(buttonVBox, width, height);
        backButton.setOnAction(e -> mainMenu(buttonVBox,width,height,controller));
    }

    private static void quickMatchMenu(VBox buttonVBox, double width, double height, Controller controller){
        buttonVBox.getChildren().clear();
        quickMatchMenuButtons(buttonVBox, width, height, controller);
        Controller.primaryStage.show();
    }

    private static void quickMatchMenuButtons(VBox buttonVBox, double width, double height, Controller controller){
        double fontSize = 17*(height/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font(fontSize);
        ToggleGroup toggleGroup = new ToggleGroup();

        ToggleButton deathMatchButton = new ToggleButton("Death Macth");
        ToggleButton captureButton = new ToggleButton("Capture the flag");
        ToggleButton spaceBallButton = new ToggleButton("Space Ball");
        ToggleButton goldRushButton = new ToggleButton("Gold Rush");
        //Button functionality
        for (ToggleButton button: Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)){
            button.setToggleGroup(toggleGroup);
        }

        deathMatchButton.setSelected(true);


        //Button width
        for (ToggleButton button : Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)) {
            button.setPrefWidth(width * 0.2);
        }


        //Button height
        for (ToggleButton button : Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)) {
            button.setPrefHeight(height * 0.05);
        }

        //Button appearance
        for (ToggleButton button : Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)) {
            button.setFont(font);
            button.setStyle(fontSizeStyle);
        }

        //Add buttons to VBox
        for (ToggleButton button : Arrays.asList(deathMatchButton,captureButton,spaceBallButton,goldRushButton)) {
            buttonVBox.getChildren().add(button);
        }

        //BackButton
        Button backButton = backButton(buttonVBox, width, height);
        backButton.setOnAction(e -> playMenu(buttonVBox,width,height,controller));
    }

    private static Button backButton(VBox buttonVBox, double width, double height) {
        Button backButton = new Button();
        Image backArrowImage = new Image(Objects.requireNonNull(Controller.class.getClassLoader().getResourceAsStream("icons/backArrow.png")));
        ImageView backArrowImageView = new ImageView(backArrowImage);
        backArrowImageView.setFitHeight(0.05 * height);
        backArrowImageView.setFitWidth(0.05 * height);
        backButton.setGraphic(backArrowImageView);
        backButton.setPrefWidth(0.075 * width);
        backButton.setPrefHeight(0.05 * height);
        buttonVBox.getChildren().add(backButton);
        return backButton;
    }


}
