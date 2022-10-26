import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public  class GameSketch extends PApplet {



    //VARIABLES DE CARACTER GLOBAl
    float[] obsposx = new float[5];
    float[] obsposy = new float[5];
    int cant = 8;

    int  width = 600;

    obstaculo a,b,c,d,e,f,g;
    bus b1;
    bus b2;
    PImage bg,puerto;
    calle c1;
    calle c2;


    Obstaculos o = new Obstaculos(cant);
    DetectorDeColisiones colisionador = new DetectorDeColisiones();



    //METODOS DE CARACTER GLOBAL

    //-- Metodo para cada opcion de colision.






    @Override
    public void settings() {
        size(width, 600);
    }
    @Override
    public void setup() {
        bg= loadImage("background2.jpg");
        puerto = loadImage("puerto.png");
        frameRate(50);
        b1= new bus(width/4+width/2,height,40,80,puerto);
        b2= new bus(width/2-width/4,height,40,80,puerto);
        imageMode(CORNER);
        rectMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,20);
        c2= new calle(width/2-20,-1500,20);
        a = new obstaculo(-2000,15,0,55,55);
        b = new obstaculo(-3000,15,1,55,55);
        c = new obstaculo(-2800,15,2,55,55);
        d = new obstaculo(-3700,15,3,55,55);
        e = new obstaculo(-2600,15,4,55,55);
    }
    public int devuelveObs(bus bus){
        int h = bus.colisiones(60,60,obsposx,obsposy,5);
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
            return 1;
        }
        return  0;
    }


    //manejar teclas presionadas
    @Override
    public void keyPressed() {
        if (keyCode == LEFT) {
            b1.vX = -5;
        }
        if (keyCode == RIGHT) {
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

        obsposx[4]=e.ox;
        obsposy[4]=e.oy;



        c1.move();
        c1.display();
        c1.loop();


        c2.display();
        c2.move();
        c2.loop();


        b1.display();
        b1.move();



        b2.display();
        b2.move();



        b.loop();
        b.display();
        b.move();

        a.loop();
        a.display();
        a.move();

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
        devuelveObs(b1);
        if(devuelveObs(b1)==1){

        }
        if(devuelveObs(b2)==1) {
            //thread("SobusaC1");
        }


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

        calle(float tx, float ty, float tv){
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
            if(y>=0.0){
                y=-1500;

            }
        }


    }


    //clase bus
    class bus {
        PImage skin;
        float x; //posicion en x
        float y; // posicion en y
        float vX; // velocidad X
        float ancho;
        float largo;
        int c; // color RGB

        //posicion de los bordes
        float BAr() {
            return y - largo / 2;
        }

        float BAb() {
            return y + largo / 2;
        }

        //constructor
        bus(float tx, float ty, float a, float l,PImage tipo) {
            x = tx;
            skin = tipo;
            y = ty;
            ancho = a;
            largo = l;
            vX = 0;
            c = 255;

        }

        public int colisiones(int anchoOb, int largoOb, float []xarray,float []yarray,int tamañoarreglo) {
            int h=-1;
            int u;
            for(int i=0;i<tamañoarreglo;i++) {
                float obsx = (xarray[i]);
                float obsy =(yarray[i]);
                boolean AlertaColision = colisionador.rectRect(obsx,obsy,anchoOb,largoOb,x,y,ancho, largo);
                if (AlertaColision) {
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
            fill(c);
            rect(x-ancho,y-largo,ancho,largo);

        }
    }

    //Clase del tipo Obstaculo
    class obstaculo {
        float ox, oy;
        float voy;
        float h;

        float alturaobs, anchoobs;
        ArrayList <Float> obsta = o.generador();

        //Constructor
        obstaculo( float o_y, float Voy,int pos, int alto, int ancho) {
            oy = o_y;
            h = oy; //Guardo su posicion inicla en y para que vuelva a ella al momento de llegar a y =0.0
            ox = obsta.get(pos);
            voy = Voy;
            alturaobs = alto;
            anchoobs = ancho;
        }




        public void move(){
            oy = oy + voy;
        }

        public void  display(){
            fill(0, 255, 208);
            rect(ox,oy,alturaobs,anchoobs);
        }

        public void choque(){
            oy = 620;
        }


        public void loop(){
            if(oy>700){
                oy = h;
                ArrayList <Float> obsta2 = o.generador();
                ox = obsta2.get((int) (Math.random() * cant) + 1);
            }
        }
    }
    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }


}
