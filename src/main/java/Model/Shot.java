package Model;

import Controller.Controller;
import Model.Ships.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot implements Drawable {
    private static final double[] shapeX= new double[]{-1.0,1.0,1.0,-1.0};
    private static final double[] shapeY= new double[]{2.5, 2.5, -2.5, -2.5};
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;
    private double positionX;
    private double positionY;
    private double angle;
    private double velX;
    private double velY;

    public Shot(Color color, double gunPositionX, double gunPositionY, double angle){
        positionX = gunPositionX;
        positionY = gunPositionY;
        this.angle = angle;

        final double speed = 10;

        velX = speed * Math.cos(Math.toRadians(angle));//Skal lige testes
        velY = speed * Math.sin(Math.toRadians(angle));

        dynamicShapeX = new double[shapeX.length];
        dynamicShapeY = new double[shapeY.length];

        for (int i = 0; i<shapeX.length; i++){
            double shapePosX = shapeX[i] * Math.cos(Math.toRadians(angle)) - shapeY[i] * Math.sin(Math.toRadians(angle));
            double shapePosY = shapeY[i] * Math.cos(Math.toRadians(angle)) + shapeX[i] * Math.sin(Math.toRadians(angle));

            dynamicShapeX[i] = (shapePosX+positionX) * Controller.factor;
            dynamicShapeY[i] = (shapePosY+positionY) * Controller.factor;
        }
    }



    @Override
    public void draw(GraphicsContext gc) {
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }
}
