package Controller;

import Model.Ships.Ship;

public class MediumBot extends Bot {
    public MediumBot(Ship ship){
        super(ship);
        //MoveForward
        boostDistance = 75;
        boostDirectionPrecision = 75;
        boostSpeed = 2;
        //Turning
        rotationPrecision = 20;
        rotationSpeed = 125;
        //Shoot
        shotPrecision = 50;
    }
}
