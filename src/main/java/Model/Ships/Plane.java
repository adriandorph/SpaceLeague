package Model.Ships;

public class Plane extends Ship{
    private final double forwardFriction;
    private final double sideFriction;
    public Plane(double speed, double turningAcceleration, double shootingRate, String className, String name, double[]shapeX, double[]shapeY, double gunPosX, double gunPosY, StartPosition startPosition, double forwardFriction, double sideFriction) {
        super(speed, turningAcceleration, shootingRate, className, name, shapeX, shapeY, gunPosX, gunPosY, startPosition);
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
