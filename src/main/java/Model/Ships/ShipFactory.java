package Model.Ships;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class ShipFactory {

    private static HashMap<String, Integer> classRanks = new HashMap<>();
    private static boolean isInitialized;


    public static Ship BoxShip(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        initializeRanks();
        double[] shapeX = {-7.5, 7.5, 10.0, -10.0};
        double[] shapeY = {20.0, 20.0, -20.0, -20.0};
        double[] flameX = {-3, 3, 0};
        double[] flameY = {-22, -22, -28};
        double gunPositionX = 0.0;
        double gunPositionY = 20.0;
        return new Ship(200,400,0.3,"S-class", "The Box", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static Ship MarkIShip(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        initializeRanks();
        double[] shapeX = {0.0, 4.2, 4.8, 12.0, 12.0, 13.2, 14.4, 14.4, 14.4, 12.0, 12.0, 2.4, 3.6, -3.6, -2.4, -12.0, -12.0, -14.4, -14.4, -14.4, -13.2, -12.0, -12.0, -4.8, -4.2};
        double[] shapeY = {24.0, 13.2, 0.0, -3.6, -1.2, 2.4, -1.2, -3.6, -12.0, -12.0, -9.6, -12.0, -15.0, -15.0, -12.0, -9.6, -12.0, -12.0, -3.6, -1.2, 2.4, -1.2, -3.6, 0.0, 13.2};
        double[] flameX = {-2.5, 2.5, 0};
        double[] flameY = {-17, -17, -30};
        double gunPositionX = 0.0;
        double gunPositionY = 24.0;
        return new Ship(200,400,0.3,"S-class", "Mark I", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static Ship MarkIIShip(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        initializeRanks();
        double[] shapeX = {0.0,   4.0, 5.0,  5.3, 4.7, 5.5,   8.5, 14.0, 16.9, 13.2,  4.2,   3.2,   2.4,   2.0,   -2.0, -2.4, -3.2, -4.2, -13.2, -16.9, -14.0, -8.5, -5.5, -4.7, -5.3, -5.0, -4.0};
        double[] shapeY = {21.5, 14.0, 13.5, 8.9, 7.8, -2.7, -3.5, 10.0, 8.24, -10.0, -13.3, -14.6, -14.6, -15.5, -15.5, -14.6, -14.6, -13.3, -10.0, 8.24, 10.0, -3.5, -2.7, 7.8, 8.9, 13.5, 14.0};
        double[] flameX = {-2.5, 2.5, 0};
        double[] flameY = {-17, -17, -30};
        double gunPositionX = 0.0;
        double gunPositionY = 21.5;
        return new Ship(200,400,0.3,"S-class", "Mark II", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static Ship AlexI(int startPosition, int numberOfShips, Color color, boolean noob) throws Exception {
        initializeRanks();
        double[] shapeX = {0.0, 3.25, 5.0, 14.0, 14.0, 15.0, 16.0, 16.0, 21.0, 16.0, 16.0, 14.0, 14.0, 5.0, 2.5, 0.0, -2.5, -5.0, -14.0, -14.0, -16.0, -16.0, -21.0, -16.0, -16.0, -15.0, -14.0, -14.0, -5.0, -3.25};
        double[] shapeY = {25, 20, 10, 4.36, 6.0, 8.0, 6.0, 3.11, 0, -3.12, -6.0, -6.0, -4.37, -10.0, -10.0, -14.0, -10.0, -10.0, -4.37, -6.0, -6.0, -3.12, 0, 3.11, 6.0, 8.0, 6.0, 4.36, 10, 20};
        double[] flameX = {-3, 3, 0};
        double[] flameY = {-15, -15, -28};
        double gunPositionX = 0.0;
        double gunPositionY = 20.0;
        return new Ship(200,400,0.3,"S-class", "The Box", shapeX, shapeY, flameX, flameY, gunPositionX, gunPositionY, startPosition, numberOfShips, color, noob);
    }

    public static void initializeRanks(){
        if (!isInitialized) {
            classRanks = new HashMap<>();
            classRanks.put("S-class", 0);
            classRanks.put("A-class", 1);
            classRanks.put("B-class", 2);
            classRanks.put("C-class", 3);
            classRanks.put("D-class", 4);
            classRanks.put("Aero A-class", 5);
            classRanks.put("Aero B-class", 6);
            classRanks.put("Aero C-class", 7);
            isInitialized = true;
        }
    }

    public static int getRank(String className){
        if (!isInitialized) initializeRanks();
        return classRanks.get(className);
    }

    //Test og Spejling af skibe.
    private static String xMirror(String xCoords, boolean isX){
        StringBuilder stringBuilder = new StringBuilder(xCoords);
        String[] xCoordsArr = xCoords.split(" ");
        for (int i = xCoordsArr.length -1; i>=0; i--){
            if (!(isX && (i == 0 || i == xCoordsArr.length-1) && ShipFactory.parseDouble(xCoordsArr[i]) == 0)) {
                stringBuilder.append(' ');
                if (isX) stringBuilder.append('-');
                stringBuilder.append(xCoordsArr[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static Double parseDouble(String str){
        StringBuilder newStr = new StringBuilder();
        for (Character c: str.toCharArray()){
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.'){
                newStr.append(c);
            }
        }
        return Double.parseDouble(newStr.toString());
    }


    public static void main(String[] args) throws Exception {
        System.out.println(xMirror("0.0, 3.25, 5.0, 14.0, 14.0, 15.0, 16.0, 16.0, 21.0, 16.0, 16.0, 14.0, 14.0, 5.0, 2.5, 0.0", true));
        System.out.println(xMirror("25, 20, 10, 4.36, 6.0, 8.0, 6.0, 3.11, 0, -3.12, -6.0, -6.0, -4.37, -10.0, -10.0, -14.0", false));
    }
}
