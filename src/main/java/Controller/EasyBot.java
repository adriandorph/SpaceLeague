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
            if (distanceToTarget(ship) < minDistance){
                target = ship;
            }
        }
        assert target != null;

        updateMoveForward(target);
        updateTurning(target);
        updateShoot(target);
    }

    private void updateMoveForward(Ship target){
        //If speed is low or goind the wrong direction and target is not too close and ship is pointing somewhat in the right direction
        if ((ship.getSpeed() < ship.getAcceleration() || false) && //TODO: add: or going opposite/wrong direction
            distanceToTarget(target) > 100 && relativeAngle(target.getPositionX(), target.getPositionY()) < 20){
            moveForward = true;
        } else moveForward = false;
    }

    private void updateTurning(Ship target){
        //If relative angle is to the left or right turn left or right
        double relativeAngle = relativeAngle(target.getPositionX(), target.getPositionY());

    }

    private void updateShoot(Ship target){
        //If ship is pointing at target
        if (relativeAngle(target.getPositionX(), target.getPositionY()) < 20 ) shoot = true;
        else shoot = false;
    }
}
