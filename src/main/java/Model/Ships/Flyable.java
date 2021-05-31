package Model.Ships;

public interface Flyable {
    void shoot();
    void move();
    void turn();
    double getSpeed();
    double getTurningSpeed();
    double getShootingRate();
}
