package View;

import Controller.Controller;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;

public abstract class MenuTemplate extends StackPane {
    protected static final double fontSize = 17*(Controller.windowHeight/720);
    protected static final String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
    protected static final Font font = new Font(fontSize);


    protected final Canvas background;
    protected final GraphicsContext gc;
    protected final Pane logoPane;
    protected final ImageView spaceLeagueLogo;
    protected final VBox menuButtonsVBox;

    public MenuTemplate() {
        //Logo
        spaceLeagueLogo = new ImageView(new Image(Objects.requireNonNull(Controller.class.getClassLoader().getResourceAsStream("icons/spaceLeagueLogo.png"))));
        double dimension = spaceLeagueLogo.getImage().getHeight() / spaceLeagueLogo.getImage().getWidth();
        double imageWidth = 0.2 * Controller.windowWidth;
        spaceLeagueLogo.setFitWidth(imageWidth);
        spaceLeagueLogo.setFitHeight(imageWidth * dimension);
        logoPane = new Pane(spaceLeagueLogo);
        logoPane.setTranslateX(Controller.windowWidth * 0.05);
        logoPane.setTranslateY(Controller.windowHeight * 0.075);


        //Background
        background = new Canvas(Controller.windowWidth, Controller.windowHeight);
        gc = background.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, Controller.windowWidth, Controller.windowHeight);

        //Menu buttons VBox
        menuButtonsVBox = new VBox();
        menuButtonsVBox.setTranslateX(Controller.windowWidth * 0.05);
        menuButtonsVBox.setTranslateY(Controller.windowHeight * 0.55);
        menuButtonsVBox.setSpacing(Controller.windowHeight * 0.015);

        getChildren().add(background);
        getChildren().add(logoPane);
        getChildren().add(menuButtonsVBox);
    }

    public static void styleMenuButton(Button button){
        button.setPrefWidth(Controller.windowWidth * 0.2);
        button.setPrefHeight(Controller.windowHeight * 0.05);
        button.setFont(font);
        button.setStyle(fontSizeStyle);
    }

    public static void styleMenuButton(ToggleButton button){
        button.setPrefWidth(Controller.windowWidth * 0.2);
        button.setPrefHeight(Controller.windowHeight * 0.05);
        button.setFont(font);
        button.setStyle(fontSizeStyle);
    }

    public static Button backButton(){
        Button backButton = new Button();
        Image backArrowImage = new Image(Objects.requireNonNull(Controller.class.getClassLoader().getResourceAsStream("icons/backArrow.png")));
        ImageView backArrowImageView = new ImageView(backArrowImage);
        backArrowImageView.setFitHeight(0.05 * Controller.windowHeight);
        backArrowImageView.setFitWidth(0.05 * Controller.windowHeight);
        backButton.setGraphic(backArrowImageView);
        backButton.setPrefWidth(Controller.windowWidth * 0.075);
        backButton.setPrefHeight(Controller.windowHeight * 0.05);
        return backButton;
    }
}
