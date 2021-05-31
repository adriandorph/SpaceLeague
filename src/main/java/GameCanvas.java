import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;
    public GameCanvas(int width, int height){
        super(width, height);
        gc = getGraphicsContext2D();
        update();
    }

    public void update(){
        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(0,0, getWidth(), getHeight());
    }
}
