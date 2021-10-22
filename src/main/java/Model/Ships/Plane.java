package Model.Ships;

import javafx.scene.paint.Color;

public class Plane extends Ship{
    private final double forwardFriction;
    private final double sideFriction;
    public Plane(double speed, double turningAcceleration, double shootingRate, double shootingPower, String className, String name, double[]shapeX, double[]shapeY, double[] flameX, double[] flameY, double gunPosX, double gunPosY, int startPosition, int numOfShips, Color color, double forwardFriction, double sideFriction) throws Exception {
        super(speed, turningAcceleration, shootingRate, shootingPower, className, name, shapeX, shapeY, flameX, flameY, gunPosX, gunPosY, startPosition, numOfShips, color);
        this.forwardFriction = forwardFriction;
        this.sideFriction = sideFriction;
    }

    public double getForwardFriction(){
        return forwardFriction;
    }

    public double getSideFriction(){
        return sideFriction;
    }
}
