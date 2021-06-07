package Model;

import Controller.Controller;

public class CollisionDetection {
//Knowledge from https://www.youtube.com/watch?v=vrk2kAuSyLg&t=515s&ab_channel=BytesNBitsBytesNBits
    public static boolean isColliding(Collidable one, Collidable two){
        //Basic collision with boundingCircles
        double distanceX = Math.abs(one.getPositionX() - two.getPositionX());
        double distanceY = Math.abs(one.getPositionY() - two.getPositionY());
        if (Math.sqrt(distanceX*distanceX + distanceY*distanceY) <= one.getBoundingRadius() + two.getBoundingRadius()){
            return isCollidingInBoundary(one, two);
        }
        return false;
    }

    public static Direction wallCollisionDirection(Collidable polygon){
        //Is it even close?
        double polygonX = polygon.getPositionX();
        double polygonY = polygon.getPositionY();
        double bounding = polygon.getBoundingRadius();
        if (polygonY - bounding < 0.0 || polygonY + bounding > 720.0 || polygonX - bounding < 0.0 || polygonX + bounding > 1280.0) {
            for (int i = 0; i < polygon.getDynamicShapeX().length; i++) {
                if (wallCollisionNorth(polygon.getDynamicShapeY()[i])) return Direction.NORTH;
                if (wallCollisionSouth(polygon.getDynamicShapeY()[i])) return Direction.SOUTH;
                if (wallCollisionWest(polygon.getDynamicShapeX()[i])) return Direction.WEST;
                if (wallCollisionEast(polygon.getDynamicShapeX()[i])) return Direction.EAST;
            }
        }
        return null;
    }

    public static boolean wallCollisionNorth(double pointY){
        return pointY <= 0.0;
    }

    public static boolean wallCollisionSouth(double pointY){
        return pointY >= 720.0 * Controller.factor;
    }

    public static boolean wallCollisionWest(double pointX){
        return pointX <= 0.0;
    }

    public static boolean wallCollisionEast(double pointX){
        return pointX >= 1280.0 * Controller.factor;
    }

    public static boolean wallCollision(double pointX, double pointY){
        return wallCollisionNorth(pointY) || wallCollisionSouth(pointY) || wallCollisionWest(pointX) || wallCollisionEast(pointX);
    }

    public static boolean isCollidingInBoundary(Collidable one, Collidable two){
        //Complex test   - 35:08 in the video

        for (int i = 0; i<one.getDynamicShapeX().length; i++){
            if (pointInPolygon(one.getDynamicShapeX()[i], one.getDynamicShapeY()[i], two)) return true;
        }
        for (int i = 0; i<two.getDynamicShapeX().length; i++){
            if (pointInPolygon(two.getDynamicShapeX()[i], two.getDynamicShapeY()[i], one)) return true;
        }
        return false;
    }

    private static boolean pointInPolygon(double pointX, double pointY, Collidable polygon){
        int crossingPointsLeft = 0;
        int crossingPointsRight = 0;
        double polygonY1 = polygon.getDynamicShapeY()[polygon.getDynamicShapeY().length-1];
        double polygonX1 = polygon.getDynamicShapeX()[polygon.getDynamicShapeX().length-1];

        for (int i = 0; i<polygon.getDynamicShapeX().length; i++){
            double polygonY2 = polygon.getDynamicShapeY()[i];
            double polygonX2 = polygon.getDynamicShapeX()[i];

            if (polygonY1 > pointY && polygonY2 < pointY || polygonY2 > pointY && polygonY1 < pointY){
                if (polygonX1 < pointX && polygonX2 < pointX) crossingPointsLeft++;
                else if (polygonX1 > pointX && polygonX2 > pointX) crossingPointsRight++;
                else {
                    double crossingPoint = polygonX1 + ((pointY - polygonY1) * (polygonX2 - polygonX1) / (polygonY2 - polygonY1));

                    if (pointX > crossingPoint) crossingPointsLeft++;
                    else crossingPointsRight++;
                }
            }
            polygonX1 = polygonX2;
            polygonY1 = polygonY2;
        }

        if (crossingPointsRight % 2 != 0) return true;


        return false;
    }
}
