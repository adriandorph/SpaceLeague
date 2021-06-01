package Controller;

import Exceptions.UnfairException;
import Model.*;
import Model.Ships.FlyableShip;
import Model.Ships.Ship;
import Model.Ships.ShipFactory;
import Model.Ships.StartPosition;
import View.GameCanvas;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Controller extends javafx.application.Application {
    public Model model;
    public static ShipFactory shipFactory = new ShipFactory();
    public static double factor = 1.0;   // 1.0 = 720p bruges til skalering.
    private static Stage primaryStage;
    public static void main() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws UnfairException {
        model = new Model();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        Controller.primaryStage = primaryStage;
        setFullScreen();
        startGame(true, (FlyableShip) ShipFactory.BoxShip(StartPosition.PLAYER1, true), ShipFactory.BoxShip(StartPosition.PLAYER2, false));
    }

    public void startGame(boolean host, FlyableShip player1, Ship player2) throws UnfairException {
        GameField gameField = new GameField(host, player1, player2);
        GameCanvas gameCanvas = new GameCanvas(primaryStage.getWidth(), primaryStage.getHeight(), gameField.getAllObjects());
        Game game = new Game(gameCanvas, gameField);
        StackPane background = new StackPane();
        Scene scene = new Scene(background);
        background.getChildren().add(gameCanvas);
        primaryStage.setScene(scene);
        primaryStage.show();
        game.start();
    }

    public static void setSize(double height){//Skal være i canvas
        primaryStage.setFullScreen(false);
        factor = height / 720.0;
        primaryStage.setHeight(height);
        primaryStage.setWidth(height * 16/9.0);
        System.out.println(height +" "+ height*16.0/9.0);
    }

    public static void setFullScreen(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setSize(bounds.getHeight()+40);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
    }
}
