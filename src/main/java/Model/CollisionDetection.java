package Model;

public class CollisionDetection {
//Knowledge from https://www.youtube.com/watch?v=vrk2kAuSyLg&t=515s&ab_channel=BytesNBitsBytesNBits
    public static boolean isColliding(Collidable one, Collidable two){
        //Basic collision with boundingCircle
        double distanceX = Math.abs(one.getPositionX() - two.getPositionX());
        double distanceY = Math.abs(one.getPositionY() - two.getPositionY());
        if (Math.sqrt(distanceX*distanceX + distanceY*distanceY) <= one.getBoundingRadius() + two.getBoundingRadius()){
            //Complex test   - 35:08 in the video
            return true;
        }
        return false;
    }
}
