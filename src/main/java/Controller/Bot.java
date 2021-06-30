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

    protected double distanceToTarget(Ship target){
        return Math.sqrt((ship.getPositionX() - target.getPositionX()) * (ship.getPositionX() - target.getPositionX())
                + (ship.getPositionY() - target.getPositionY()) * (ship.getPositionY() - target.getPositionY()));
    }

    protected double relativeAngle(double targetPosX, double targetPosY){
        return deltaAngle(ship.getAngle(), angleToTarget(targetPosX, targetPosY));

    }

    public static double deltaAngle(double from, double to){
        double relativeAngle = to - from;
        boolean negative = relativeAngle < 0;

        relativeAngle = Math.abs(relativeAngle);
        if (relativeAngle > 180) {
            relativeAngle = (180 - (relativeAngle - 180));
            negative = !negative;
        }

        if (negative) relativeAngle *= -1;
        return relativeAngle;
    }

    public double angleToTarget(double targetPosX, double targetPosY){
        double relativeX = targetPosX - ship.getPositionX();
        double relativeY = targetPosY - ship.getPositionY();
        return vectorAngle(relativeX, relativeY);
    }

    public static double vectorAngle(double x, double y){ //0 degrees is south which means +y direction and angle is clockwise
        double vectorAngle = Math.toDegrees(Math.atan(x/y));
        if (x >= 0 && y >= 0){ //First quadrant
            return 360 - vectorAngle;
        } else if ((x >= 0 && y <= 0)||(x <= 0 && y <= 0)){ //Second and third quadrant
            return 180 - vectorAngle;
        } else { //Fourth quadrant
            return -vectorAngle;
        }
    }


}
