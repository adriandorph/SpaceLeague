package Controller;

import Exceptions.NotImplementedException;
import Model.Ships.Ship;

public class MediumBot extends Bot {
    public MediumBot(Ship ship){
        super(ship);
        throw new NotImplementedException();
    }

    @Override
    public void update(Ship[] opponents) {
        throw new NotImplementedException();
    }
}
