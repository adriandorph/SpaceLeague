package Controller;

import Model.Ships.Ship;

public class EasyBot extends Bot {

    public EasyBot(Ship ship) {
        super(ship);
    }

    @Override
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
        if(target == null) System.out.println("target is null");

        updateMoveForward(target);
        updateTurning(target);
        updateShoot(target);

        ship.setMoveForward(moveForward);
        ship.setTurnRight(turnRight);
        ship.setTurnLeft(turnLeft);
        ship.setShoot(shoot);
    }

    private void updateMoveForward(Ship target){
        //If speed is low or goind the wrong direction and target is not too close and ship is pointing somewhat in the right direction
        moveForward = (ship.getSpeed() < ship.getAcceleration() || goingOppositeDirection(target)) &&
                distanceToTarget(target) > 100 && Math.abs(relativeAngle(target.getPositionX(), target.getPositionY())) < 50;
    }

    private void updateTurning(Ship target){
        //If relative angle is to the left or right turn left or right
        double relativeAngle = relativeAngle(target.getPositionX(), target.getPositionY());
        if (relativeAngle > 20 && ship.getVelR() < 100) {
            turnRight = true;
            turnLeft = false;
        } else if (relativeAngle < -20 && ship.getVelR() > -100){
            turnLeft = true;
            turnRight = false;
        } else {
            turnLeft = false;
            turnRight = false;
        }
    }

    private void updateShoot(Ship target){
        //If ship is pointing at target
        double relativeAngle = relativeAngle(target.getPositionX(), target.getPositionY());
        shoot = relativeAngle < 50 && relativeAngle > -50;
    }

    private boolean goingOppositeDirection(Ship target){
        double angleToTarget = angleToTarget(target.getPositionX(), target.getPositionY());
        double direction = vectorAngle(ship.getVelX(), ship.getVelY());
        double deltaAngle = deltaAngle(direction, angleToTarget);
        return Math.abs(deltaAngle) > 90;
    }
}
