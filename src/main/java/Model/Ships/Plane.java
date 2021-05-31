package Model.Ships;

public class Plane extends Ship{
    private final double forwardFriction;
    private final double sideFriction;
    public Plane(double speed, double turningSpeed, double shootingRate, String className, String name, double[]shapeX, double[]shapeY, double forwardFriction, double sideFriction) {
        super(speed, turningSpeed, shootingRate, className, name, shapeX, shapeY);
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
