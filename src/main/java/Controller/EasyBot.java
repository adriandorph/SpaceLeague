package Controller;

import Model.Ships.Ship;

public class EasyBot extends Bot {
    public EasyBot(Ship ship) {
        super(ship);
        //MoveForward
        boostDistance = 150;
        boostDirectionPrecision = 50;
        boostSpeed = 1;

        //Turning
        rotationPrecision = 20;
        rotationSpeed = 100;
        //Shoot
        shotPrecision = 50;
    }
}
