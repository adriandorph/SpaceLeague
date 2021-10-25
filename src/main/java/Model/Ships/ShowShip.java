package Model.Ships;

import Controller.Controller;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShowShip {
    public final String name;
    public final String className;


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

    public ShowShip(String className, String name, double[] shapeX, double[] shapeY, Color color, double acceleration, double turning, double shootingRate, double shootingPower){
        this.name = name;
        this.className = className;

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

    public Canvas drawShip(double sizeFactor, double angle){
        sizeFactor *= Controller.factor;
        dynamicShapeX = Ship.dynamicShapeX(shapeX, shapeY, angle,0, sizeFactor);
        dynamicShapeY = Ship.dynamicShapeY(shapeX, shapeY, angle, 0, sizeFactor);

        double positionX = -min(dynamicShapeX) / sizeFactor;
        double positionY = -min(dynamicShapeY) / sizeFactor;


        //Call Ship.dynamicShape again to get a position where all parts of the ship can be seen and is not outside the Canvas
        dynamicShapeX = Ship.dynamicShapeX(shapeX, shapeY, angle,positionX, sizeFactor);
        dynamicShapeY = Ship.dynamicShapeY(shapeX, shapeY, angle, positionY, sizeFactor);

        Canvas canvas = new Canvas(max(dynamicShapeX),max(dynamicShapeY));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //draw
        gc.setFill(color);
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
        return canvas;
    }

    public Canvas drawStat(double width, double height, Stat stat){
        double statWidth = switch (stat){
            case Acceleration -> acceleration * width;
            case Turning -> turning * width;
            case ShootingRate -> shootingRate * width;
            case ShootingPower -> shootingPower * width;
        };

        Canvas canvas = new Canvas(width,height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.gray(0.4));
        gc.fillPolygon(new double[]{0.0, width, width, 0.0}, new double[]{0.0, 0.0, height, height}, 4);
        gc.setFill(Color.WHITE);
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
