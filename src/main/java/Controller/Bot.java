package Controller;

import Model.Ships.Ship;

public abstract class Bot {
    protected boolean moveForward;
    protected boolean turnRight;
    protected boolean turnLeft;
    protected boolean shoot;

    public Ship ship;

    public Bot(Ship ship){
        this.ship = ship;
    }

    public abstract void update(Ship[] opponents);

    public boolean moveForward(){
        return moveForward;
    }

    public boolean turnRight(){
        return turnRight;
    }

    public boolean turnLeft(){
        return turnLeft;
    }

    public boolean shoot(){
        return shoot;
    }

    protected double distanceToTarget(Ship target){
        return Math.sqrt((ship.getPositionX() - target.getPositionX()) * (ship.getPositionX() - target.getPositionX())
                + (ship.getPositionY() - target.getPositionY()) * (ship.getPositionY() - target.getPositionY()));
    }

    protected double relativeAngle(double targetPosX, double targetPosY){
        double relativeAngle = angleToTarget(targetPosX, targetPosY) - ship.getAngle();
        boolean negative = relativeAngle < 0;

        relativeAngle = Math.abs(relativeAngle);
        if (relativeAngle > 180) {
            relativeAngle = (180 - (relativeAngle - 180));
            negative = !negative;
        }

        if (negative) relativeAngle *= -1;

        return relativeAngle;
    }

    private double angleToTarget(double targetPosX, double targetPosY){
        //Math.atan2(compare.y, compare.x)
        return 0;
    }
}
