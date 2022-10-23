public class DetectorDeColisiones {
    DetectorDeColisiones(){
    }

    boolean rect(float r1x, float r1y, float r1w, float r1h, float r2x, float r2y, float r2w, float r2h) {

        // are the sides of one rectangle touching the other?

        if (r1x + r1w >= r2x &&    // r1 right edge past r2 left
                r1x <= r2x + r2w &&    // r1 left edge past r2 right
                r1y + r1h >= r2y &&    // r1 top edge past r2 bottom
                r1y <= r2y + r2h) {    // r1 bottom edge past r2 top
            return true;
        }
        return false;
    }

    boolean rectRect(float r1x, float r1y,  float r1w, float r1h,float r2x, float r2y, float r2w, float r2h){
        float distancex = (r1x + r1w/2) - (r2x + r2w/2);
        float distancey = (r1y + r1h/2) - (r2y + r2h/2);
        float halfw = r1w/2 + r2w/2;
        float halfh = r1h/2 + r2h/2;
        if(Math.abs(distancex)<halfw){
            if(Math.abs(distancey)<halfh){
                return  true;
            }
        }
        return false;
    }




}
