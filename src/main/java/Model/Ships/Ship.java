package Model.Ships;
import Controller.Controller;
import Model.Shot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Ship implements Comparable<Ship>, Drawable{
    protected final double speed;
    private final double turningAcceleration;
    private final double shootingRate;
    private double timeSinceLastShoot; // i sekunder
    private boolean canShoot;
    private final int classRank;
    private final String className;
    private final String name;
    private final double[] shapeX;
    private final double[] shapeY;
    private double[] dynamicShapeX;
    private double[] dynamicShapeY;
    protected Color color;
    private List<Shot> shots;

    protected double velX;
    protected double velY;
    protected double velR;
    protected double angle;
    private double positionX;
    private double positionY;
    private final double gunPosX;
    private final double gunPosY;
    protected double dynamicGunPosX;
    protected double dynamicGunPosY;
    private boolean moveForward;
    private boolean turnLeft;
    private boolean turnRight;
    private boolean shoot;

    public Ship(double speed, double turningAcceleration, double shootingRate, String className, String name, double[] shapeX, double[] shapeY, double gunPosX, double gunPosY, StartPosition startPosition){
        this.speed = speed;
        this.turningAcceleration = turningAcceleration;
        this.shootingRate = shootingRate;
        this.classRank = ShipFactory.getRank(className);
        this.className = className;
        this.name = name;
        this.shapeX = shapeX;
        this.shapeY = shapeY;
        this.gunPosX = gunPosX;
        this.gunPosY = gunPosY;
        timeSinceLastShoot = 0; //Cooldown fra start
        canShoot = false;
        shots = new ArrayList<>();
        velR = 0;
        velX = 0;
        velY = 0;

        moveForward = false;
        turnLeft = false;
        turnRight = false;
        shoot = false;

        startPosition(startPosition);
    }

    public void startPosition(StartPosition startPosition){
        if (startPosition == StartPosition.PLAYER1){
            positionX = 200;
            positionY = 200;
            angle = 120;
            color = Color.RED;
        } else if (startPosition == StartPosition.PLAYER2){
            positionX = 1000;
            positionY = 200;
            angle = 0;
            color = Color.BLUE;
        } else {
            positionX = 0;
            positionY = 0;
        }
        update(0);
    }

    public void update(double time){
        //Update position, rotation
        updateVelR(time, turnLeft, turnRight);
        updateAngle(time);
        if (moveForward) updateVelocity(time);
        updatePosition(time);
        detroyShots();

        timeSinceLastShoot += time;
        if (timeSinceLastShoot < shootingRate) canShoot = false;
        else canShoot = true;
        if (canShoot && shoot) shoot();

        for(int i = 0; i<shots.size(); i++){
            Shot shot = shots.get(i);
            shot.update(time);
            shots.set(i, shot);
        }


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
        dynamicGunPosX = gunPosX * Math.cos(Math.toRadians(angle)) - gunPosY * Math.sin(Math.toRadians(angle)) + positionX;
        dynamicGunPosY = gunPosY * Math.cos(Math.toRadians(angle)) + gunPosX * Math.sin(Math.toRadians(angle)) + positionY;
    }

    public void updateAngle(double time){
        angle += velR * time;
        if (angle < 0){
            angle = 360 -angle;
        } else angle %= 360;
    }
    public void updateVelR(double time, boolean left, boolean right){
        //TODO: this{
            if (right) velR += turningAcceleration * time;
            if (left) velR -= turningAcceleration * time;
    }

    public void updateVelocity(double time){
        velX += time * speed * Math.cos(Math.toRadians(angle+90));
        velY += time * speed * Math.sin(Math.toRadians(angle+90));
    }

    public void updatePosition(double time){
        //Boundary detection
        if (positionY <= 0.0){
            velY *= -0.2;
            positionY = 0.1;
        } else if (positionY >= 720.0) {
            velY *= -0.2;
            positionY = 719.9;
        }
        if (positionX <= 0.0){
            velX *= -0.2;
            positionX = 0.1;
        } else if(positionX >= 720 * 16.0/9.0) {
            velX *= -0.2;
            positionX = 1279.9;
        }

        //position update by speed
        positionX += velX * time;
        positionY += velY * time;
    }

    public void detroyShots(){
        List<Shot> removeObjects = new ArrayList<>();
        for (Shot shot : shots) {
            if (!shot.isInGameField()) removeObjects.add(shot);
        }
        shots.removeAll(removeObjects);
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
    public List<Shot> getShots(){
        return shots;
    }

    @Override
    public int compareTo(Ship that) {
        if (this.classRank < that.classRank) return -1;
        if (this.classRank > that.classRank) return 1;
        return this.name.compareTo(that.name);
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
    }

    public void shoot() {
        shots.add(new Shot(color, dynamicGunPosX, dynamicGunPosY, angle, velX, velY));
        timeSinceLastShoot = 0;
    }

    public void setMoveForward(boolean moveForward) {
        this.moveForward = moveForward;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
}
