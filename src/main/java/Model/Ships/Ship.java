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
    }

    public void startPosition(StartPosition startPosition){
        if (startPosition == StartPosition.PLAYER1){
            positionX = 200;
            positionY = 200;
            angle = 115;
        } else if (startPosition == StartPosition.PLAYER2){
            positionX = 400;
            positionY = 200;
            angle = 0;
        } else {
            positionX = 0;
            positionY = 0;
        }

/*
        dynamicShapeX = new double[shapeX.length];
        //dynamicShapeY = new double[shapeY.length];
        for (int i = 0; i<shapeX.length; i++){
            dynamicShapeX[i]= shapeX[i]+positionX;
            //dynamicShapeY[i]= shapeY[i]+positionY;
        }

 */





        updatePosition();
    }

    public void updatePosition(){

        //Shape angle conversion
        dynamicShapeX = new double[shapeX.length];
        dynamicShapeY = new double[shapeY.length];
        for (int i = 0; i<shapeX.length; i++){
            double shapePosX = shapeX[i];
            double shapePosY = shapeY[i];
            boolean xWasMinus = shapePosX < 0.0;
            boolean yWasMinus = shapePosY < 0.0;





            //Find angle to center måske her den er gal
            double distanceFromCenter = Math.sqrt(shapePosX*shapePosX + shapePosY*shapePosY);
            double oriAngle = Math.toDegrees(Math.acos(shapePosX / distanceFromCenter));
            System.out.println(oriAngle);

            //New angle and position
            oriAngle += angle;
            shapePosX = shapeX[i] * Math.cos(Math.toRadians(angle)) - shapeY[i] * Math.sin(Math.toRadians(angle));
            shapePosY = shapeY[i] * Math.cos(Math.toRadians(angle)) - shapeX[i] * Math.sin(Math.toRadians(angle));

            /*
            shapePosY = distanceFromCenter * Math.cos(Math.toRadians(oriAngle));
            shapePosX = distanceFromCenter * Math.sin(Math.toRadians(oriAngle));

             */
            //if (xWasMinus) shapePosX *= -1;
           // if (yWasMinus) shapePosY *= -1;
            dynamicShapeX[i] = shapePosX+positionX;
            dynamicShapeY[i] = shapePosY+positionY;
        }
    }

    public void updateAngle(){
        angle %= 360;
    }

    public double[] getDynamicShapeX(){
        return dynamicShapeX;
    }

    public double[] getDynamicShapeY(){
        return dynamicShapeY;
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
