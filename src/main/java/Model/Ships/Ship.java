package Model.Ships;

public class Ship{
    private final double speed;
    private final double turningAcceleration;
    private final double shootingRate;
    private final int classRank;
    private final String className;
    private final String name;
    private final double[] shapeX;
    private final double[] shapeY;

    private double velX;
    private double velY;
    private double velR;
    private double angle;
    private double positionX;
    private double positionY;

    public Ship(double speed, double turningAcceleration, double shootingRate, String className, String name, double[] shapeX, double[] shapeY){
        this.speed = speed;
        this.turningAcceleration = turningAcceleration;
        this.shootingRate = shootingRate;
        this.classRank = ShipFactory.getRank(className);
        this.className = className;
        this.name = name;
        this.shapeX = shapeX;
        this.shapeY = shapeY;
    }

    public void startPosition(StartPosition startPosition){
        if (startPosition == StartPosition.PLAYER1){

        } else if (startPosition == StartPosition.PLAYER2){

        } else {
            //Ignored
        }
    }

    public void updatePosition(){

    }

    public void updateAngle(){

    }

    public double[] getShapePositionX(){
        double[] shapePosition = new double[shapeX.length];
        for (int i = 0; i<shapeX.length; i++){
            shapePosition[i] = shapeX[i]+positionX;
        }
        return shapePosition;
    }
    public double[] getShapePositionY(){
        double[] shapePosition = new double[shapeY.length];
        for (int i = 0; i<shapeY.length; i++){
            shapePosition[i] = shapeY[i]+positionY;
        }
        return shapePosition;
    }






    //Måske ubrugeligt
    public double getSpeed(){
        return speed;
    }
    public double getTurningAcceleration(){
        return turningAcceleration;
    }
    public double getShootingRate(){
        return shootingRate;
    }
}
