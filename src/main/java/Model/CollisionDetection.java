package Model;

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

    public static boolean isCollidingInBoundary(Collidable one, Collidable two){
        for (int i = 0; i<one.getDynamicShapeX().length; i++){
            if (pointInPolygon(one.getDynamicShapeX()[i], one.getDynamicShapeY()[i], two)) return true;
        }
        return false;
    }

    private static boolean pointInPolygon(double pointX, double pointY, Collidable polygon){
        int crossingPointsLeft = 0;

        for (int i = 1; i<polygon.getDynamicShapeX().length; i++){
            double polygonY1 = polygon.getDynamicShapeY()[i-1];
            double polygonY2 = polygon.getDynamicShapeY()[i];
            double polygonX1 = polygon.getDynamicShapeX()[i-1];
            double polygonX2 = polygon.getDynamicShapeX()[i];
            if (polygonY1 > pointY && polygonY2 < pointY || polygonY2 > pointY && polygonY1 < pointY){ // Hvis pointY er mellem linjens maxY og minY
                double crossingPoint = polygonX1 + (pointY - polygonY1) * (polygonX2 - polygonX1) / (polygonY2 - polygonY1);
                if (pointX >= crossingPoint) crossingPointsLeft++;
            }
            if (crossingPointsLeft % 2 != 0) return true;
        }

        return false;
    }
}
