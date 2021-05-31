package Model.Ships;

public class FlyableShip extends Ship implements Flyable{

    public FlyableShip(double speed, double turningSpeed, double shootingRate, String className, String name, double[] shapeX, double[] shapeY) {
        super(speed, turningSpeed, shootingRate, className, name, shapeX, shapeY);
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
