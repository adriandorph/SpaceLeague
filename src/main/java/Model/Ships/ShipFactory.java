package Model.Ships;

import javafx.scene.paint.Color;

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
    private static boolean isInitialized;

    public static Ship BoxShip(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        if (!isInitialized) {initializeRanks(); isInitialized = true;}
        double[] shapeX = {-7.5, 7.5, 10.0, -10.0};
        double[] shapeY = {20.0, 20.0, -20.0, -20.0};
        double[] flameX = {-3, 3, 0};
        double[] flameY = {-22, -22, -28};
        double gunPositionX = 0.0;
        double gunPositionY = 20.0;
        return new Ship(200,400,0.3,"S-class", "The Box", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static Ship MarkIShip(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        if (!isInitialized) {initializeRanks(); isInitialized = true;}
        double[] shapeX = {0.0, 4.2, 4.8, 12.0, 12.0, 13.2, 14.4, 14.4, 14.4, 12.0, 12.0, 2.4, 3.6, -3.6, -2.4, -12.0, -12.0, -14.4, -14.4, -14.4, -13.2, -12.0, -12.0, -4.8, -4.2};
        double[] shapeY = {24.0, 13.2, 0.0, -3.6, -1.2, 2.4, -1.2, -3.6, -12.0, -12.0, -9.6, -12.0, -15.0, -15.0, -12.0, -9.6, -12.0, -12.0, -3.6, -1.2, 2.4, -1.2, -3.6, 0.0, 13.2};
        double[] flameX = {-2.5, 2.5, 0};
        double[] flameY = {-17, -17, -30};
        double gunPositionX = 0.0;
        double gunPositionY = 24.0;
        return new Ship(200,400,0.3,"S-class", "The Box", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static void initializeRanks(){
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
