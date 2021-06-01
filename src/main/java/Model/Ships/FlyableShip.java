package Model.Ships;

public class FlyableShip extends Ship implements Flyable{

    public FlyableShip(double speed, double turningAcceleration, double shootingRate, String className, String name, double[] shapeX, double[] shapeY, double gunPosX, double gunPosY, StartPosition startPosition) {
        super(speed, turningAcceleration, shootingRate, className, name, shapeX, shapeY, gunPosX, gunPosY, startPosition);
    }

    @Override
    public void move() {

    }

    @Override
    public void turn() {

    }
}
