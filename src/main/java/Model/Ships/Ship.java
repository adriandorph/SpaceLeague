package Model.Ships;

public class Ship implements Flyable{
    private final double speed;
    private final double turningSpeed;
    private final double shootingRate;
    private final int classRank;
    private final String className;
    private final String name;
    private double angel;
    private final double[] shapeX;
    private final double[] shapeY;
    private double positionX;
    private double positionY;

    public Ship(double speed, double turningSpeed, double shootingRate, String className, String name, double[] shapeX, double[] shapeY){
        this.speed = speed;
        this.turningSpeed = turningSpeed;
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

    public void shoot(){

    }

    public void move(){

    }

    public void turn(){

    }

    public void updatePosition(){

    }

    public double getSpeed(){
        return speed;
    }
    public double getTurningSpeed(){
        return turningSpeed;
    }
    public double getShootingRate(){
        return shootingRate;
    }
}
