package Model;

import Controller.Controller;
import Model.Ships.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot implements Drawable {
    private static final double[] shapeX= new double[]{-1.0,1.0,1.0,-1.0};
    private static final double[] shapeY= new double[]{5.0, 5.0, 0.0, 0.0};
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;
    private double positionX;
    private double positionY;
    private final double angle;
    private final double velX;
    private final double velY;
    private final Color color;
    private static final double speed = 500.0;

    public Shot(Color color, double gunPositionX, double gunPositionY, double angle, double shipVelX, double shipVelY){
        this.color = color;
        positionX = gunPositionX;
        positionY = gunPositionY;
        this.angle = angle;
        velX = speed * Math.cos(Math.toRadians(angle+90)) + shipVelX;//Det virker, ved ikke hvorfor +90;
        velY = speed * Math.sin(Math.toRadians(angle+90)) + shipVelY;
    }

    public void update(double time){
        positionX += time * velX;
        positionY += time * velY;

        dynamicShapeX = new double[shapeX.length];
        dynamicShapeY = new double[shapeY.length];

        for (int i = 0; i<shapeX.length; i++){
            double shapePosX = shapeX[i] * Math.cos(Math.toRadians(angle)) - shapeY[i] * Math.sin(Math.toRadians(angle));
            double shapePosY = shapeY[i] * Math.cos(Math.toRadians(angle)) + shapeX[i] * Math.sin(Math.toRadians(angle));

            dynamicShapeX[i] = (shapePosX+positionX) * Controller.factor;
            dynamicShapeY[i] = (shapePosY+positionY) * Controller.factor;
        }

    }

    public boolean isInGameField(){
        return !(positionY <= 0.0) && !(positionY >= 720.0) && !(positionX <= 0.0) && !(positionX >= 720 * 16.0 / 9.0);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }
}
