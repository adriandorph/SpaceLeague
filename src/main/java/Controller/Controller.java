package Controller;

import Model.*;
import Model.Ships.Ship;
import Model.Ships.ShipFactory;
import View.GameCanvas;
import View.View;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


public class Controller extends javafx.application.Application {

    private static View view;
    public static double factor = 1.0;   // 1.0 = 720p bruges til skalering.
    public static Stage primaryStage;
    private static Game game = null;
    private static boolean fullScreen;
    public static double windowHeight;
    public static double windowWidth;

    private static boolean moveForward;
    private static boolean turnRight;
    private static boolean turnLeft;
    private static boolean shoot;

    private static boolean moveForward2;
    private static boolean turnRight2;
    private static boolean turnLeft2;
    private static boolean shoot2;

    private static GameSettings gameSettings;

    public static void main() {
        fullScreen = false;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        Controller.primaryStage = primaryStage;
        setFullScreen();
        //setSize(720);

        view = new View();
        view.setFill(Color.BLACK);
        view.getStylesheets().add("styling/menuButtons.css");
        Controller.primaryStage.setScene(view);
        sizingAfterNewScene();
        setGameKeyInput(view);
        primaryStage.show();
    }

    public static void mainMenu(){
        //view.setFill(Color.BLACK);
        view.mainMenu();
        primaryStage.show();
    }

    public static void playMenu(){
        view.playMenu();
        primaryStage.show();
    }

    public static void quickMatchMenu(){
        view.quickMatchMenu();
        primaryStage.show();
    }

    public static void startGame(GameSettings gameSettings) throws Exception {

        List<Ship> ships = new LinkedList<>();
        ships.add(ShipFactory.MarkIShip(0, 2, Color.RED, true));
        ships.add(ShipFactory.MarkIShip(ships.size(), 2, Color.LIME, false));
        //ships.add(ShipFactory.AlexI(ships.size(), 4, Color.AQUA, false));
        //ships.add(ShipFactory.BoxShip(ships.size(), 4, Color.YELLOW, false));

        GameField gameField = new GameField(gameSettings);

        Controller.gameSettings = gameSettings;

        GameCanvas gameCanvas = new GameCanvas(primaryStage.getWidth(), primaryStage.getHeight(), gameField.getAllObjects(), gameField.getShips());
        game = new Game(gameCanvas, gameField);
        StackPane gamePane = new StackPane();
        view.setCursor(Cursor.NONE);
        gamePane.getChildren().add(gameCanvas);
        view.viewGame(gamePane);

        primaryStage.show();

        game.start();
    }

    private static void sizingAfterNewScene(){
        if (fullScreen){
            primaryStage.setFullScreen(true);
        } else {
            primaryStage.sizeToScene();
            setSize(windowHeight);
        }
    }

    public static void setSize(double height){
        if (!fullScreen && (height < 720 || height > 2160)) throw new RuntimeException("The window size has to be within 720p - 2160p");
        primaryStage.setFullScreen(false);
        factor = height / 720.0;
        windowWidth = height * 16/9.0;
        windowHeight = height;

        primaryStage.setWidth(windowWidth);
        primaryStage.setHeight(height);
    }

    public static void setFullScreen(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setSize(bounds.getHeight()+40);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
        fullScreen = true;
    }

    public void setGameKeyInput(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.W) moveForward = true;
            if (key == KeyCode.A) turnLeft = true;
            if (key == KeyCode.D) turnRight = true;
            if (key == KeyCode.SPACE) shoot = true;

            if (key == KeyCode.UP) moveForward2 = true;
            if (key == KeyCode.LEFT) turnLeft2 = true;
            if (key == KeyCode.RIGHT) turnRight2 = true;
            if (key == KeyCode.SHIFT) shoot2 = true;

            if (key == KeyCode.R) {//Restart game
                try {
                    game = null;
                    startGame(Controller.gameSettings);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (key == KeyCode.ESCAPE && game != null) {
                game = null;
                view.setCursor(Cursor.DEFAULT);
                playMenu();
            }

        });
        scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.W) moveForward = false;
            if (key == KeyCode.A) turnLeft = false;
            if (key == KeyCode.D) turnRight = false;
            if (key == KeyCode.SPACE) shoot = false;

            if (key == KeyCode.UP) moveForward2 = false;
            if (key == KeyCode.LEFT) turnLeft2 = false;
            if (key == KeyCode.RIGHT) turnRight2 = false;
            if (key == KeyCode.SHIFT) shoot2 = false;
        });
    }

    public static boolean moveForward() {
        return moveForward;
    }

    public static boolean turnRight() {
        return turnRight;
    }

    public static boolean turnLeft() {
        return turnLeft;
    }

    public static boolean shoot() {
        return shoot;
    }

    public static boolean moveForward2() {
        return moveForward2;
    }

    public static boolean turnRight2() {
        return turnRight2;
    }

    public static boolean turnLeft2() {
        return turnLeft2;
    }

    public static boolean shoot2() {
        return shoot2;
    }
}
