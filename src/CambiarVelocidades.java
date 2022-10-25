public class CambiarVelocidades  extends  Thread{

    float cambio;
    float tc;
    GameSketch.calle c;
    float tiempo;

   CambiarVelocidades( float c,float tc, GameSketch.calle cl, float tiempo){
        this.cambio = c;
        this.tc = tc;
        this.c = cl;
        this.tiempo = tiempo;
    }

    public void run(){
       /**
        int u = (int)(tiempo);
        float v = c.v;
        while(u>=0){
            c.v = c.v+((cambio)*tc);
        }
        */
        System.out.println("Hola");
    }




}
