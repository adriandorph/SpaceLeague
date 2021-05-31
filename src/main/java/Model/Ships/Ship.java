package Model.Ships;
import Controller.Controller;
import javafx.scene.canvas.GraphicsContext;

public class Ship implements Comparable<Ship>{
    private final double speed;
    private final double turningAcceleration;
    private final double shootingRate;
    private final int classRank;
    private final String className;
    private final String name;
    private final double[] shapeX;
    private final double[] shapeY;
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;

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
        velR = 0;
        velX = 0;
        velY = 0;
    }

    public void startPosition(StartPosition startPosition){
        if (startPosition == StartPosition.PLAYER1){
            positionX = 200;
            positionY = 200;
            angle = 10;
        } else if (startPosition == StartPosition.PLAYER2){
            positionX = 400;
            positionY = 200;
            angle = 0;
        } else {
            positionX = 0;
            positionY = 0;
        }

        updatePosition();
    }

    public void updatePosition(){

        //Shape angle conversion
        dynamicShapeX = new double[shapeX.length];
        dynamicShapeY = new double[shapeY.length];
        for (int i = 0; i<shapeX.length; i++){
            //Rotation https://www.youtube.com/watch?v=OYuoPTRVzxY
            double shapePosX = shapeX[i] * Math.cos(Math.toRadians(angle)) - shapeY[i] * Math.sin(Math.toRadians(angle));
            double shapePosY = shapeY[i] * Math.cos(Math.toRadians(angle)) + shapeX[i] * Math.sin(Math.toRadians(angle));

            dynamicShapeX[i] = (shapePosX+positionX) * Controller.factor;
            dynamicShapeY[i] = (shapePosY+positionY) * Controller.factor;
        }
    }

    public void updateAngle(){
        //TODO: this
        angle %= 360;
    }
    public void updateVelR(){
        //TODO: this
        velR = 0;
    }
    public void updateVelX(){
        //TODO: this
        velX = 0;
    }
    public void updateVelY(){
        //TODO: this
        velY = 0;
    }

    public double[] getDynamicShapeX(){
        return dynamicShapeX;
    }

    public double[] getDynamicShapeY(){
        return dynamicShapeY;
    }

    public double getSpeed(){
        return speed;
    }
    public double getTurningAcceleration(){
        return turningAcceleration;
    }
    public double getShootingRate(){
        return shootingRate;
    }
    public String getName(){
        return name;
    }
    public String getClassName() {
        return className;
    }

    @Override
    public int compareTo(Ship that) {
        if (this.classRank < that.classRank) return -1;
        if (this.classRank > that.classRank) return 1;
        return this.name.compareTo(that.name);
    }

    public void draw(GraphicsContext gc){
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }
}
