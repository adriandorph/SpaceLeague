package Model.Ships;

import java.util.HashMap;

public class ShipFactory {
    /*
    private List<Flyable> Sclass;
    private List<Flyable> Aclass;
    private List<Flyable> Bclass;
    private List<Flyable> Cclass;
    private List<Flyable> Dclass;
    private List<Flyable> AeroAclass;
    private List<Flyable> AeroBclass;
    private List<Flyable> TurboCclass;
     */
    private static HashMap<String, Integer> classRanks = new HashMap<>();

    public ShipFactory(){
        initializeRanks();
    }

    public static Ship BoxShip(StartPosition startPosition, boolean flyable){
        double[] shapeX = {-10.0, 10.0, 10.0, -10.0};
        double[] shapeY = {20.0, 20.0, -20.0, -20.0};
        double gunPositionX = 0.0;
        double gunPositionY = 20.0;
        if (flyable) return new FlyableShip(100,500,1,"S-class", "The Box", shapeX, shapeY, gunPositionX, gunPositionY, startPosition);
        else return new Ship(100,500,1,"S-class", "The Box", shapeX, shapeY, gunPositionX, gunPositionY, startPosition);
    }

    private void initializeRanks(){
        classRanks = new HashMap<>();
        classRanks.put("S-class",0);
        classRanks.put("A-class",1);
        classRanks.put("B-class",2);
        classRanks.put("C-class",3);
        classRanks.put("D-class",4);
        classRanks.put("Aero A-class",5);
        classRanks.put("Aero B-class",6);
        classRanks.put("Aero C-class",7);

    }

    public static int getRank(String className){
        return classRanks.get(className);
    }
}
