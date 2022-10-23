import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public  class GameSketch extends PApplet {

    boolean IAinU = true;
    int cant = 8;
    int time = 0;
    int vcalle1 = 15;int vcalle2 =15;
    float[] obsposx = new float[5];
    float[] obsposy = new float[5];


    int t=1;

    int w=1;
    Obstaculos o = new Obstaculos(cant);
    DetectorDeColisiones colisionador = new DetectorDeColisiones();
    float xr;
    obstaculo a,b,c,d,e,f,g;
    bus b1;
    bus b2;
    PImage bg;
    calle c1;
    calle c2;

    goal meta1;
    goal meta2;

    //METODOS DE CARACTER GLOBAL
     void devuelveObs(bus bus){
            int h = bus.colisiones(60,60,obsposx,obsposy,6);
            if (h!=-1){
                switch (h){
                    case 1:
                        b.oy=620;
                        break;
                    case 0:
                        a.oy=620;
                        break;

                    case 2:
                        c.oy = 620;
                        break;

                    case 3:
                        d.oy = 620;
                        break;

                    case 4:
                        e.oy = 620;
                        break;
                    default:
                        break;
                }

        }






    }

    @Override
    public void settings() {
        size(600, 600);
    }


   //Metodo generador de posicion inicial en x de los obstaculos

    @Override
    public void setup() {
        frameRate(50);
        b1= new bus(width/4+width/2,height,40,80,1);
        b1.vX=1;
        b2= new bus(width/2-width/4,height,40,80,2);
        bg= loadImage("background2.jpg");
        imageMode(CORNER);
        rectMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c2= new calle(-20,-1500,vcalle2);
        c1= new calle(width/2-20,-1500,vcalle1);
        meta1 = new goal(width/2+10,-200000,b1,c1);
        meta2 = new goal(10,-200000,b2,c2);
        a = new obstaculo(-2000,15,0,55,55);
        b = new obstaculo(-3000,15,1,55,55);
        c = new obstaculo(-1500,15,2,55,55);
        d = new obstaculo(-3500,15,3,55,55);
        e = new obstaculo(-2500,15,4,55,55);






    }

    //manejar teclas presionadas
    @Override
    public void keyPressed() {
        if (keyCode == LEFT && !IAinU) {
            b1.vX = -5;
        }
        if (keyCode == RIGHT && !IAinU) {
            b1.vX = 5;
        }
        if (key == 'a') {
            b2.vX = -5;
        }
        if (key == 'd') {
            b2.vX = 5;
        }
    }

    // manejar teclas soltadas
    @Override
    public void keyReleased() {
        if (keyCode == LEFT) {
            b1.vX = 0;
        }
        if (keyCode == RIGHT) {
            b1.vX = 0;
        }
        if (key == 'a') {
            b2.vX = 0;
        }
        if (key == 'd') {
            b2.vX = 0;
        }
    }

    @Override
    public void draw() {
        background(0);

        obsposx[0]=a.ox;
        obsposy[0]=a.oy;

        obsposx[1]=b.ox;
        obsposy[1]=b.oy;

        obsposx[2]=c.ox;
        obsposy[2]=c.oy;

        obsposx[3]=d.ox;
        obsposy[3]=d.oy;



        c1.move();
        c1.display();
        c1.loop();


        c2.display();
        c2.move();
        c2.loop();


        b1.display();
        if(IAinU) {
            IAmove(b1);
        }
        b1.move();



       b2.display();
       b2.move();

        meta1.display();
        meta1.move();
        meta1.finish();

        meta2.display();
        meta2.move();
        meta2.finish();

        a.display();
        a.move();
        a.loop();

        b.display();
        b.move();
        b.loop();

         c.loop();
         c.display();
         c.move();

         d.loop();
         d.display();
         d.move();

         e.loop();
         e.display();
         e.move();

        b1.colisiones(55,55,obsposx,obsposy,5);
        b2.colisiones(55,55,obsposx,obsposy,5);


        if(b1.x<width/2+b1.ancho){
            b1.x=width/2+b1.ancho;
        }
        if(b1.x>width){
            b1.x=width;
        }
        if(b2.x<b2.ancho){
            b2.x=b2.ancho;
        }
        if(b2.x>width/2-b2.ancho/2){
            b2.x=width/2-b2.ancho/2;
        }
    }

    //clase calle
    class calle{
        float x;
        float y;
        float v;

        calle(float tx, float ty, int tv){
            x=tx;
            y=ty;
            v=tv;
        }

        public void move() {
            y+=v;
        }

        public void display(){
            image(bg,x,y);
        }
        //Funcion que redibuja la imagen
        public void loop(){
            if(y>-10){
                y=-1500;

            }
        }

    }


    //clase bus
    class bus {

        float x; //posicion en x
        float y; // posicion en y
        float vX; // velocidad X
        float ancho;
        float largo;
        int num;

        //posicion de los bordes
        float BAr() {
            return y - largo / 2;
        }

        float BAb() {
            return y + largo / 2;
        }

        //constructor
        bus(float tx, float ty, float a, float l, int n) {
            x = tx;
            y = ty;
            ancho = a;
            largo = l;
            num=n;
            vX = 0;
        }

        public int colisiones(int anchoOb, int largoOb, float []xarray,float []yarray,int tamañoarreglo) {
            int h=-1;
            for(int i=0;i<tamañoarreglo;i++) {
                float obsx = (xarray[i]);
                float obsy =(yarray[i]);
                boolean AlertaColision = colisionador.rectRect(obsx,obsy,anchoOb,largoOb,x,y,ancho, largo);
                if (AlertaColision) {
                    System.out.println(i);
                    return i;
                }
            }
            return h;
        }

        //mover bus
        public void move() {
            x += vX;
        }

        //mostrar-generar bus
        public void display() {
            fill(255);
            rect(x-ancho,y-largo,ancho,largo);

        }
    }

    public void IAmove(bus bu){
        if ( comp(bu,a) || comp(bu,b) || comp(bu,c) || comp(bu,d) || comp(bu,e)) {

            if(bu.x-bu.ancho/2 > width-width/4+ bu.ancho && bu.x-bu.ancho/2 < width-width/4- bu.ancho ){
                t=1;
            }else{
                if (bu.x-bu.ancho/2 > width-width/4){
                    t=-1;
                }
                if(bu.x-bu.ancho/2 < width-width/4){
                    t=1;
                }
                if(bu.x-bu.ancho/2 == width-width/4){
                    t=0;
                }
            }
            bu.vX=4*t;
        }

        if (bu.x>=width){
            bu.vX=-4;
        }
        if(bu.x<= width/2+b1.ancho){
            b1.vX=4;
        }
    }
    //Clase del tipo Obstaculo
    class obstaculo {
        float ox, oy;
        float voy;

        float alturaobs, anchoobs;
        ArrayList <Float> obsta = o.generador();

        //Constructor
        obstaculo( float o_y, float Voy,int pos, int alto, int ancho) {
            oy = o_y;
            ox = obsta.get(pos);
            voy = Voy;
            alturaobs = alto;
            anchoobs = ancho;
        }

        public void move(){
            oy += voy;
        }

        public void  display(){
            fill(102, 102, 255);
            rect(ox,oy,alturaobs,anchoobs);
        }

        public void choque(){
            oy = 620;
        }


        public void loop(){
            if(oy>600){
                oy = -3500;
                ArrayList <Float> obsta2 = o.generador();
                ox = obsta2.get((int) (Math.random() * cant) + 1);
            }
        }

    }
    class goal{
        float x;
        float y;
        bus bus;
        calle calle;

        goal(float ox, float oy, bus ob,calle oc){
            this.x  = ox;
            this.y  = oy;
            this.bus  = ob;
            this.calle  = oc;
        }

        public void  display(){
            fill(255);
            rect(x,y,width/2,40);
        }

        public void move(){
            this.y += this.calle.v;
        }

        public void finish() {
            if(this.y > this.bus.BAr()+20 && w!=0){
                b1.vX=0;
                c1.v=0;
                b2.vX=0;
                c2.v=0;
                a.voy=0;
                b.voy=0;
                c.voy=0;
                d.voy=0;
                e.voy=0;
                if(bus.num==1){
                    System.out.println("Gano el bus 1");
                }else {
                    System.out.println("Gano el bus 2");
                }
                w=0;
            }
        }

    }

    public boolean comp(bus b,obstaculo o){
        boolean z = o.ox>width/2;
        boolean x = b.x-b.ancho/2 > o.ox+17 && b.x-b.ancho/2 < o.ox+23 && o.oy+40>b.BAr()-80;

        if( x || (z && o.oy+40>=b.BAr()) ){
            return true;
        }else{
            return false;
        }
    }



    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }


}


