package Model.Ships;

import Controller.Controller;
import Model.Collidable;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot implements Drawable, Collidable {
    private static final double[] shapeX= new double[]{-1.0,1.0,1.0,-1.0};
    private static final double[] shapeY= new double[]{5.0, 5.0, 0.0, 0.0};
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;
    private double boundingRadius;
    private double positionX;
    private double positionY;
    private final double angle;
    private final double velX;
    private final double velY;
    private final Color color;

    public Shot(double speed, Color color, double gunPositionX, double gunPositionY, double angle, double shipVelX, double shipVelY){
        this.color = color;
        positionX = gunPositionX;
        positionY = gunPositionY;
        this.angle = angle;
        velX = speed * Math.cos(Math.toRadians(angle+90)) + shipVelX;
        velY = speed * Math.sin(Math.toRadians(angle+90)) + shipVelY;

        boundingRadius = 0.0;
        for (int i = 0; i<shapeX.length; i++){
            double auxBoundingRadius = Math.sqrt(shapeX[i]*shapeX[i]+shapeY[i]*shapeY[i]);
            if (auxBoundingRadius > boundingRadius) boundingRadius = auxBoundingRadius;
        }
    }

    public void update(double time){
        positionX += time * velX;
        positionY += time * velY;

        dynamicShapeX = new double[shapeX.length];
        dynamicShapeY = new double[shapeY.length];

        Ship.polygonAngle(shapeX, shapeY, dynamicShapeX, dynamicShapeY, angle, positionX, positionY, Controller.factor);
    }


    public boolean isInGameField(){
        return !(positionY <= 0.0) && !(positionY >= 720.0) && !(positionX <= 0.0) && !(positionX >= 720 * 16.0 / 9.0);
    }

    @Override
    public void draw(GraphicsContext gc) {
            gc.setFill(color);
            gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }

    //Collidable
    @Override
    public double[] getDynamicShapeX(){
        return dynamicShapeX;
    }
    @Override
    public double[] getDynamicShapeY(){
        return dynamicShapeY;
    }
    @Override
    public double getPositionX() {
        return positionX;
    }
    @Override
    public double getPositionY() {
        return positionY;
    }
    @Override
    public double getBoundingRadius(){return boundingRadius;}
}
