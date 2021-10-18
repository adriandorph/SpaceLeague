package Controller;

import Exceptions.NotImplementedException;
import Model.Ships.Ship;

public class HardBot extends Bot {
    public HardBot(Ship ship){
        super(ship);
        throw new NotImplementedException();
    }

    @Override
    public void update(Ship[] opponents) {
        throw new NotImplementedException();
    }
}
