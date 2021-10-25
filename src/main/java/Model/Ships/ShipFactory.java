package Model.Ships;

import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ShipFactory {

    private static HashMap<String, Integer> classRanks = new HashMap<>();
    private static boolean isInitialized;

    public static Ship BuildShip(int startPosition, int numberOfShips, Color color, ShipVariant shipVariant) throws Exception {
        return switch (shipVariant){
            case Box -> Box(startPosition, numberOfShips, color);
            case MarkI, DEFAULT -> MarkI(startPosition, numberOfShips, color);
            case MarkII -> MarkII(startPosition, numberOfShips, color);
            case AlexI -> AlexI(startPosition, numberOfShips, color);
        };
    }

    public static ShowShip BuildShowShip(ShipVariant shipVariant, Color color){
        return switch (shipVariant){
            case Box -> Box(color);
            case MarkI, DEFAULT -> MarkI(color);
            case MarkII -> MarkII(color);
            case AlexI -> AlexI(color);
        };
    }

    //Box
    //Shape
    private static final double[] BoxShapeX = {-7.5, 7.5, 10.0, -10.0};
    private static final double[] BoxShapeY = {20.0, 20.0, -20.0, -20.0};
    private static final double[] BoxFlameX = {-3, 3, 0};
    private static final double[] BoxFlameY = {-22, -22, -28};
    private static final double BoxGunPositionX = 0.0;
    private static final double BoxGunPositionY = 20.0;
    //Stats
    private static final double BoxAcceleration = 100;
    private static final double BoxTurning = 200;
    private static final double BoxShootingRate = 0.2;
    private static final double BoxShootingPower = 400;

    private static Ship Box(int startPosition, int numberOfShips, Color color) throws Exception {
        initializeRanks();
        return new Ship(BoxAcceleration,BoxTurning,BoxShootingRate, BoxShootingPower,"S-class", "The Box", BoxShapeX, BoxShapeY, BoxFlameX, BoxFlameY, BoxGunPositionX, BoxGunPositionY, startPosition, numberOfShips, color);
    }

    private static ShowShip Box(Color color){
        return new ShowShip("S-class", "The Box", BoxShapeX, BoxShapeY, color, BoxAcceleration, BoxTurning, BoxShootingRate, BoxShootingPower);
    }

    //MarkI
    //Shape
    private static final double[] MarkIShapeX = {0.0, 4.2, 4.8, 12.0, 12.0, 13.2, 14.4, 14.4, 14.4, 12.0, 12.0, 2.4, 3.6, -3.6, -2.4, -12.0, -12.0, -14.4, -14.4, -14.4, -13.2, -12.0, -12.0, -4.8, -4.2};
    private static final double[] MarkIShapeY = {24.0, 13.2, 0.0, -3.6, -1.2, 2.4, -1.2, -3.6, -12.0, -12.0, -9.6, -12.0, -15.0, -15.0, -12.0, -9.6, -12.0, -12.0, -3.6, -1.2, 2.4, -1.2, -3.6, 0.0, 13.2};
    private static final double[] MarkIFlameX = {-2.5, 2.5, 0};
    private static final double[] MarkIFlameY = {-17, -17, -30};
    private static final double MarkIGunPositionX = 0.0;
    private static final double MarkIGunPositionY = 24.0;

    //Stats
    private static final double MarkIAcceleration = 200;
    private static final double MarkITurning = 400;
    private static final double MarkIShootingRate = 0.3;
    private static final double MarkIShootingPower = 500;

    private static Ship MarkI(int startPosition, int numberOfShips, Color color) throws Exception {
        initializeRanks();
        return new Ship(MarkIAcceleration,MarkITurning,MarkIShootingRate, MarkIShootingPower,"S-class", "Mark I", MarkIShapeX, MarkIShapeY, MarkIFlameX, MarkIFlameY, MarkIGunPositionX, MarkIGunPositionY, startPosition, numberOfShips, color);
    }

    private static ShowShip MarkI(Color color){
        return new ShowShip("S-class", "Mark I", MarkIShapeX, MarkIShapeY, color, MarkIAcceleration, MarkITurning, MarkIShootingRate, MarkIShootingPower);
    }

    //MarkII
    //Shape
    private static final double[] MarkIIShapeX = {0.0,   4.0, 5.0,  5.3, 4.7, 5.5,   8.5, 14.0, 16.9, 13.2,  4.2,   3.2,   2.4,   2.0,   -2.0, -2.4, -3.2, -4.2, -13.2, -16.9, -14.0, -8.5, -5.5, -4.7, -5.3, -5.0, -4.0};
    private static final double[] MarkIIShapeY = {21.5, 14.0, 13.5, 8.9, 7.8, -2.7, -3.5, 10.0, 8.24, -10.0, -13.3, -14.6, -14.6, -15.5, -15.5, -14.6, -14.6, -13.3, -10.0, 8.24, 10.0, -3.5, -2.7, 7.8, 8.9, 13.5, 14.0};
    private static final double[] MarkIIFlameX = {-2.5, 2.5, 0};
    private static final double[] MarkIIFlameY = {-17, -17, -30};
    private static final double MarkIIGunPositionX = 0.0;
    private static final double MarkIIGunPositionY = 21.5;

    //Stats
    private static final double MarkIIAcceleration = 70;
    private static final double MarkIITurning = 100;
    private static final double MarkIIShootingRate = 0.1;
    private static final double MarkIIShootingPower = 300;


    private static Ship MarkII(int startPosition, int numberOfShips, Color color) throws Exception {
        initializeRanks();
        return new Ship(MarkIIAcceleration,MarkIITurning,MarkIIShootingRate, MarkIIShootingPower,"S-class", "Mark II", MarkIIShapeX, MarkIIShapeY, MarkIIFlameX, MarkIIFlameY, MarkIIGunPositionX, MarkIIGunPositionY, startPosition, numberOfShips, color);
    }

    private static ShowShip MarkII(Color color){
        return new ShowShip("S-class", "Mark II", MarkIIShapeX, MarkIIShapeY, color, MarkIIAcceleration, MarkIITurning, MarkIIShootingRate, MarkIIShootingPower);
    }

    //AlexI
    //Shape
    private static final double[] AlexIShapeX = {0.0, 3.25, 5.0, 14.0, 14.0, 15.0, 16.0, 16.0, 21.0, 16.0, 16.0, 14.0, 14.0, 5.0, 2.5, 0.0, -2.5, -5.0, -14.0, -14.0, -16.0, -16.0, -21.0, -16.0, -16.0, -15.0, -14.0, -14.0, -5.0, -3.25};
    private static final double[] AlexIShapeY = {25, 20, 10, 4.36, 6.0, 8.0, 6.0, 3.11, 0, -3.12, -6.0, -6.0, -4.37, -10.0, -10.0, -14.0, -10.0, -10.0, -4.37, -6.0, -6.0, -3.12, 0, 3.11, 6.0, 8.0, 6.0, 4.36, 10, 20};
    private static final double[] AlexIFlameX = {-3, 3, 0};
    private static final double[] AlexIFlameY = {-15, -15, -28};
    private static final double AlexIGunPositionX = 0.0;
    private static final double AlexIGunPositionY = 20.0;

    //Stats
    private static final double AlexIAcceleration = 200;
    private static final double AlexITurning = 400;
    private static final double AlexIShootingRate = 0.3;
    private static final double AlexIShootingPower = 500;

    private static Ship AlexI(int startPosition, int numberOfShips, Color color) throws Exception {
        initializeRanks();
        return new Ship(AlexIAcceleration,AlexITurning,AlexIShootingRate, AlexIShootingPower,"S-class", "Alex I", AlexIShapeX, AlexIShapeY, AlexIFlameX, AlexIFlameY, AlexIGunPositionX, AlexIGunPositionY, startPosition, numberOfShips, color);
    }

    private static ShowShip AlexI(Color color){
        return new ShowShip("S-class", "Alex I", AlexIShapeX, AlexIShapeY, color, AlexIAcceleration, AlexITurning, AlexIShootingRate, AlexIShootingPower);
    }

    private static final List<Double> acceleration = new LinkedList<>(List.of(
            BoxAcceleration,
            MarkIAcceleration,
            MarkIIAcceleration,
            AlexIAcceleration
    ));

    private static final List<Double> turning = new LinkedList<>(List.of(
            BoxTurning,
            MarkITurning,
            MarkIITurning,
            AlexITurning
    ));

    private static final List<Double> shootingRate = new LinkedList<>(List.of(
            BoxShootingRate,
            MarkIShootingRate,
            MarkIIShootingRate,
            AlexIShootingRate
    ));

    private static final List<Double> shootingPower = new LinkedList<>(List.of(
            BoxShootingPower,
            MarkIShootingPower,
            MarkIIShootingPower,
            AlexIShootingPower
    ));

    public static double getStat(double compare, Stat stat){
        return switch (stat){
            case Acceleration -> getCompareStat(acceleration.stream().max(Double::compare).get(), acceleration.stream().min(Double::compare).get(), compare);
            case Turning -> getCompareStat(turning.stream().max(Double::compare).get(), turning.stream().min(Double::compare).get(), compare);
            case ShootingRate -> getCompareStat(shootingRate.stream().max(Double::compare).get(), shootingRate.stream().min(Double::compare).get(), compare);
            case ShootingPower -> getCompareStat(shootingPower.stream().max(Double::compare).get(), shootingPower.stream().min(Double::compare).get(), compare);
        };
    }

    private static double getCompareStat(double max, double min, double compare){
        return (compare-min)/(max-min);
    }


    private static void initializeRanks(){
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


    public static void main(String[] args) {
        System.out.println(xMirror("0.0, 3.25, 5.0, 14.0, 14.0, 15.0, 16.0, 16.0, 21.0, 16.0, 16.0, 14.0, 14.0, 5.0, 2.5, 0.0", true));
        System.out.println(xMirror("25, 20, 10, 4.36, 6.0, 8.0, 6.0, 3.11, 0, -3.12, -6.0, -6.0, -4.37, -10.0, -10.0, -14.0", false));
    }
}
