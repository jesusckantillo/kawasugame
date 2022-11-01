import java.util.ArrayList;

public class Obstaculos {
    ArrayList<Float> obs = new ArrayList<Float>();
    int numobstaculos;
    Obstaculos(int obstaculos){
        numobstaculos = obstaculos;
    }
    public ArrayList<Float> generador(){
        float num;
        for(int i=0;i<numobstaculos;i++){
             num = (float) (Math.random() * 600) + 1;
            while(num<40 || num>560 ||(num>240 &num<320)){
                num = (float) (Math.random() * 600) + 1;
            }
            obs.add(num);
        }
        return(obs);
    }
}
