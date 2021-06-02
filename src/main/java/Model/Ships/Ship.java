package Model.Ships;
import Controller.Controller;
import Model.Collidable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.LinkedList;
import java.util.List;

public class Ship implements Comparable<Ship>, Drawable, Collidable {
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
    private double boundingRadius;
    private final double[] flameX;
    private final double[] flameY;
    private double[] dynamicFlameX;
    private double[] dynamicFlameY;
    protected Color color;
    private List<Shot> shots;
    public int score;
    private double scoreX;

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

    public Ship(double speed, double turningAcceleration, double shootingRate, String className, String name, double[] shapeX, double[] shapeY, double[] flameX, double[] flameY, double gunPosX, double gunPosY, int startPosition, int numberOfShips, Color color) throws Exception {
        this.speed = speed;
        this.turningAcceleration = turningAcceleration;
        this.shootingRate = shootingRate;
        this.classRank = ShipFactory.getRank(className);
        this.className = className;
        this.name = name;
        this.shapeX = shapeX;
        this.shapeY = shapeY;
        this.flameX = flameX;
        this.flameY = flameY;
        this.gunPosX = gunPosX;
        this.gunPosY = gunPosY;

        score = 0;
        this.color = color;

        boundingRadius = 0.0;
        for (int i = 0; i<shapeX.length; i++){
            double auxBoundingRadius = Math.sqrt(shapeX[i]*shapeX[i]+shapeY[i]*shapeY[i]);
            if (auxBoundingRadius > boundingRadius) boundingRadius = auxBoundingRadius;
        }

        timeSinceLastShoot = 0; //Cooldown fra start
        canShoot = false;
        shots = new LinkedList<>();
        velR = 0;
        velX = 0;
        velY = 0;

        moveForward = false;
        turnLeft = false;
        turnRight = false;
        shoot = false;

        startPosition(startPosition, numberOfShips);
    }

    public void startPosition(int startPosition, int numberOfShips) throws Exception {
        //Ship and scoreboard layout
        short scorePadding = 35;
        switch (numberOfShips){
            case 2:
                switch (startPosition) {
                    case 0 -> {
                        positionX = 1280.0 / 4;
                        positionY = 720.0 / 2;
                        angle = 270;
                        scoreX = 1280.0 / 2 - scorePadding;
                    }
                    case 1 -> {
                        positionX = 1280.0 * 3 / 4;
                        positionY = 720.0 / 2;
                        angle = 90;
                        scoreX = 1280.0 / 2 + scorePadding;
                    }
                    default -> throw new Exception("Start position " + startPosition + " not allowed...");
                }
                break;
            case 3:
                switch (startPosition) {
                    case 0 -> {
                        positionX = 1280.0 / 4;
                        positionY = 720.0 / 4;
                        angle = 295;
                        scoreX = 1280.0 / 2 - 2 * scorePadding;
                    }
                    case 1 -> {
                        positionX = 1280.0 / 2;
                        positionY = 720.0 * 3/ 4;
                        angle = 180;
                        scoreX = 1280.0 / 2;
                    }
                    case 2 -> {
                        positionX = 1280.0 * 3 / 4;
                        positionY = 720.0 / 4;
                        angle = 65;
                        scoreX = 1280.0 / 2 + 2 * scorePadding;
                    }
                    default -> throw new Exception("Start position " + startPosition + " not allowed...");
                }
                break;
            case 4:
                switch (startPosition) {
                    case 0 -> {
                        positionX = 1280.0 / 4;
                        positionY = 720.0 / 4;
                        angle = 270;
                        scoreX = 1280.0 / 2 - 3 * scorePadding;
                    }
                    case 1 -> {
                        positionX = 1280.0 * 3 / 4;
                        positionY = 720.0 / 4;
                        angle = 90;
                        scoreX = 1280.0 / 2 - scorePadding;
                    }
                    case 2 -> {
                        positionX = 1280.0 / 4;
                        positionY = 720.0 * 3 / 4;
                        angle = 270;
                        scoreX = 1280.0 / 2 + scorePadding;
                    }
                    case 3 -> {
                        positionX = 1280.0 * 3 / 4;
                        positionY = 720.0 * 3 / 4;
                        angle = 90;
                        scoreX = 1280.0 / 2 + 3 * scorePadding;
                    }
                    default -> throw new Exception("Start position " + startPosition + " not allowed...");
                }
                break;
            default:
                throw new Exception("Too Many ships in the game");

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
        canShoot = timeSinceLastShoot >= shootingRate;
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

        dynamicFlameX = new double[flameX.length];
        dynamicFlameY = new double[flameY.length];
        for (int i = 0; i<flameX.length; i++){
            //Rotation https://www.youtube.com/watch?v=OYuoPTRVzxY
            double shapePosX = flameX[i] * Math.cos(Math.toRadians(angle)) - flameY[i] * Math.sin(Math.toRadians(angle));
            double shapePosY = flameY[i] * Math.cos(Math.toRadians(angle)) + flameX[i] * Math.sin(Math.toRadians(angle));

            dynamicFlameX[i] = (shapePosX+positionX) * Controller.factor;
            dynamicFlameY[i] = (shapePosY+positionY) * Controller.factor;
        }
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
        //TODO: Check all dynamic x and y coordinates instead of just position.
        if (positionY - boundingRadius <= 0.0){
            velY *= -0.2;
            positionY = 0.1 + boundingRadius;
        } else if (positionY + boundingRadius >= 720.0) {
            velY *= -0.2;
            positionY = 719.9 - boundingRadius;
        }
        if (positionX - boundingRadius <= 0.0){
            velX *= -0.2;
            positionX = 0.1 + boundingRadius;
        } else if(positionX + boundingRadius >= 720 * 16.0/9.0) {
            velX *= -0.2;
            positionX = 1279.9 - boundingRadius;
        }

        //position update by speed
        positionX += velX * time;
        positionY += velY * time;
    }

    public void detroyShots(){
        List<Shot> removeObjects = new LinkedList<>();
        for (Shot shot : shots) {
            if (!shot.isInGameField()) removeObjects.add(shot);
        }
        shots.removeAll(removeObjects);
    }

    public void destroyShots(List<Shot> hits){
        shots.removeAll(hits);
    }

    //Collidable
    @Override
    public double[] getDynamicShapeX(){
        return dynamicShapeX;
    }
    @Override
    public double[] getDynamicShapeY(){
        return dynamicShapeY;
    }
    @Override
    public double getPositionX() {
        return positionX;
    }
    @Override
    public double getPositionY() {
        return positionY;
    }
    @Override
    public double getBoundingRadius(){return boundingRadius;}

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

    public void drawScore(GraphicsContext gc){
        gc.setFill(color);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 30 * Controller.factor));
        gc.fillText(Integer.toString(score), scoreX * Controller.factor, 40 * Controller.factor);
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillPolygon(dynamicShapeX, dynamicShapeY, dynamicShapeX.length);
        if (moveForward) gc.fillPolygon(dynamicFlameX, dynamicFlameY, dynamicFlameX.length);

        //Score
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
