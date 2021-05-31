package Model.Ships;

public class FlyablePlane extends Plane implements Flyable{

    public FlyablePlane(double speed, double turningSpeed, double shootingRate, String className, String name, double[] shapeX, double[] shapeY, double forwardFriction, double sideFriction) {
        super(speed, turningSpeed, shootingRate, className, name, shapeX, shapeY, forwardFriction, sideFriction);
    }

    @Override
    public void shoot() {

    }

    @Override
    public void move() {

    }

    @Override
    public void turn() {

    }
}
