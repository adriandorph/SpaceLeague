package Controller;

import Model.Ships.Ship;

public abstract class Bot {
    protected boolean moveForward;
    protected boolean turnRight;
    protected boolean turnLeft;
    protected boolean shoot;

    //MoveForward
    protected int boostDistance;
    protected int boostDirectionPrecision;
    protected int boostSpeed;
    //Turning
    protected int rotationPrecision;
    protected int rotationSpeed;
    //Shoot
    protected int shotPrecision;

    public Ship ship;

    public Bot(Ship ship){
        this.ship = ship;
    }

    //Update

    public void update(Ship[] opponents) {
        //Chooses the closest opponent
        Ship target = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Ship ship: opponents){
            double distanceToTarget = distanceToTarget(ship);
            if (distanceToTarget < minDistance){
                target = ship;
                minDistance = distanceToTarget;
            }
        }

        assert target != null;
        updateMoveForward(target);
        updateTurning(target);
        updateShoot(target);

        ship.setMoveForward(moveForward);
        ship.setTurnRight(turnRight);
        ship.setTurnLeft(turnLeft);
        ship.setShoot(shoot);
    }

    protected void updateMoveForward(Ship target){
        //If speed is low or going the wrong direction and target is not too close and ship is pointing somewhat in the right direction
        moveForward = (ship.getSpeed() < ship.getAcceleration() * boostSpeed || goingOppositeDirection(target)) &&
                distanceToTarget(target) > boostDistance && Math.abs(relativeAngle(target.getPositionX(), target.getPositionY())) < boostDirectionPrecision;
    }

    protected void updateTurning(Ship target){
        //If relative angle is to the left or right turn left or right
        double relativeAngle = relativeAngle(target.getPositionX(), target.getPositionY());
        if (relativeAngle > rotationPrecision && ship.getVelR() < rotationSpeed) {
            turnRight = true;
            turnLeft = false;
        } else if (relativeAngle < -rotationPrecision && ship.getVelR() > -rotationSpeed){
            turnLeft = true;
            turnRight = false;
        } else {
            turnLeft = false;
            turnRight = false;
        }
    }

    protected void updateShoot(Ship target){
        //If ship is pointing at target
        double relativeAngle = relativeAngle(target.getPositionX(), target.getPositionY());
        shoot = relativeAngle < shotPrecision && relativeAngle > -shotPrecision;
    }



    //Queries

    protected boolean goingOppositeDirection(Ship target){
        double angleToTarget = angleToTarget(target.getPositionX(), target.getPositionY());
        double direction = vectorAngle(ship.getVelX(), ship.getVelY());
        double deltaAngle = deltaAngle(direction, angleToTarget);
        return Math.abs(deltaAngle) > 90;
    }

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
