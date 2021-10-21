package Controller;

import Model.Ships.Ship;

public class HardBot extends Bot {
    public HardBot(Ship ship){
        super(ship);
        //MoveForward
        boostDistance = 50;
        boostDirectionPrecision = 40;
        boostSpeed = Integer.MAX_VALUE;
        //Turning
        rotationPrecision = 20;
        rotationSpeed = 150;
        //Shoot
        shotPrecision = 50;
    }
}
