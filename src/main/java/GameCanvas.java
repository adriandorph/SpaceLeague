import Model.Ships.Ship;
import Model.Ships.StartPosition;
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
        gc.save();
        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(0,0, getWidth(), getHeight());
        gc.setFill(Color.rgb(255,255,255));
        Ship ship1 = new Ship(1,1,1,"S-class","", new double[]{-10.0, 10.0, 10.0, -10.0}, new double[]{10.0, 10.0, -10.0, -10.0});
        ship1.startPosition(StartPosition.PLAYER1);
        double[]x = ship1.getDynamicShapeX();
        double[]y = ship1.getDynamicShapeY();
        System.out.println(x.length);
        for (int i = 0; i<x.length; i++){
            x[i] *= View.factor;
            System.out.println(x[i]);
            y[i] *= View.factor;
            System.out.println(y[i]);
            System.out.println();
        }
        gc.fillPolygon(x,y,4);
        gc.restore();
    }
}
