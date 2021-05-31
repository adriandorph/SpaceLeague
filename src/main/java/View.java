import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class View extends Application {
    public static Model model;
    public static double factor = 1.0;   // 1.0 = 720p bruges til skalering.
    private static GameCanvas gameCanvas;
    private static Stage primaryStage;
    public static void main(Model model_) {
        model = model_;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane background = new StackPane();
        Scene scene = new Scene(background);
        View.primaryStage = primaryStage;
        gameCanvas = new GameCanvas(500,500);
        background.getChildren().add(gameCanvas);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        setFullScreen();
    }

    public static void setSize(double height){//Skal være i canvas
        primaryStage.setFullScreen(false);
        factor = height / 720.0;
        primaryStage.setHeight(height);
        primaryStage.setWidth(height * 16/9.0);
        gameCanvas.setHeight(height);
        gameCanvas.setWidth(height * 16/9.0);
        System.out.println(height +" "+ height*16.0/9.0);
        gameCanvas.update();
    }

    public static void setFullScreen(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setSize(bounds.getHeight()+40);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
    }
}
