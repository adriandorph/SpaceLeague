package Model.Ships;

import Controller.Controller;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShowShip {
    //Draw
    private final double[] shapeX;
    private final double[] shapeY;
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;
    public Color color;

    //Stats
    public double acceleration;
    public double turning;
    public double shootingRate;
    public double shootingPower;

    public ShowShip(double[] shapeX, double[] shapeY, Color color, double acceleration, double turning, double shootingRate, double shootingPower){
        this.shapeX = shapeX;
        this.shapeY = shapeY;
        this.color = color;
        dynamicShapeX = null;
        dynamicShapeY = null;

        this.acceleration = ShipFactory.getStat(acceleration, Stat.Acceleration);
        this.turning = ShipFactory.getStat(turning, Stat.Turning);
        this.shootingRate = ShipFactory.getStat(shootingRate, Stat.ShootingRate);
        this.shootingPower = ShipFactory.getStat(shootingPower, Stat.ShootingPower);
    }

    public void drawShip(GraphicsContext gc, double sizeFactor, double angle){
        sizeFactor *= Controller.factor;
        Ship.polygonAngle(shapeX, shapeY, dynamicShapeX, dynamicShapeY, angle, 0, 0, sizeFactor);
        double positionX = ((-min(dynamicShapeX) + max(dynamicShapeX)) * sizeFactor) / 2;
        double positionY = ((-min(dynamicShapeY) + max(dynamicShapeY)) * sizeFactor) / 2;
        //Call Ship.polygonAngle again to get a position where all parts of the ship can be seen and is not outside the Canvas
        Ship.polygonAngle(shapeX, shapeY, dynamicShapeX, dynamicShapeY, angle, positionX, positionY, sizeFactor);

        //draw
        gc.setFill(color);
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }

    public Canvas drawStat(double width, double height, Stat stat){
        double statWidth = switch (stat){
            case Acceleration -> acceleration * width;
            case Turning -> turning * width;
            case ShootingRate -> shootingRate * width;
            case ShootingPower -> shootingPower * width;
        };

        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.gray(0.4));
        gc.fillPolygon(new double[]{0.0, width, width, 0.0}, new double[]{0.0, 0.0, height, height}, 4);
        gc.fillPolygon(new double[]{0.0, statWidth, statWidth, 0.0},new double[]{0.0, 0.0, height, height}, 4);

        return canvas;
    }

    private double min(double[] shape){
        double min = Double.POSITIVE_INFINITY;
        for(double point: shape){
            if(point < min) min = point;
        }
        return min;
    }

    private double max(double[] shape){
        double max = Double.NEGATIVE_INFINITY;
        for(double point: shape){
            if(point > max) max = point;
        }
        return max;
    }
}
