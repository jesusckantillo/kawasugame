import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public  class GameSketch extends PApplet {





    //VARIABLES DE CARACTER GLOBAl
    int vcalle1 = 15;int vcalle2 =15;
    float[] obsposx = new float[6];
    float[] obsposy = new float[6];
    int cant = 8;
    Obstaculos o = new Obstaculos(cant);
    int  width = 600;
    float xr;
    obstaculo a,b,c,d,e,f,g;
    bus b1;
    bus b2;
    PImage bg;
    calle c1;
    calle c2;


    //METODOS DE CARACTER GLOBAL



    @Override
    public void settings() {
        size(width, 600);
    }



    @Override
    public void setup() {
        frameRate(60);
        b1= new bus(width/4+width/2,height,40,80);
        b2= new bus(width/2-width/4,height,40,80);
        bg= loadImage("background2.jpg");
        imageMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,vcalle1);
        c2= new calle(width/2-20,-1500,vcalle2);
        a = new obstaculo(-2000,15,0);
        b = new obstaculo(-3000,15,1);
        c = new obstaculo(-2800,15,3);
        d = new obstaculo(-3700,15,4);
        e = new obstaculo(-2600,15,5);
        f= new obstaculo(-2400,15,6);





    }

    //manejar teclas presionadas
    @Override
    public void keyPressed() {
        if (keyCode == LEFT) {
            b1.vX = -3;
        }
        if (keyCode == RIGHT) {
            b1.vX = 3;
        }
        if (key == 'a') {
            b2.vX = -3;
        }
        if (key == 'd') {
            b2.vX = 3;
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
        obsposy[4]=d.oy;





        c1.move(vcalle1);
        c1.display();
        c1.loop();


        c2.display();
        c2.move(vcalle2);
        c2.loop();


        b1.display();
        b1.move();


       b1.colisiones(40,obsposx,obsposy,6);
       System.out.println(b1.colisiones(40,obsposx,obsposy,6));
       //int hb1 = b1.colisiones(40,obsposx,obsposy,6);

        //b2.display();
        //b2.move();

       // b2.colisiones(40,obsposx,obsposy,6);
       // int hb2 =b2.colisiones(40,obsposx,obsposy,6);

        b.loop();
        b.display();
        b.move();

        /**
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
        */


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

        public void move(int v) {
            y+=v;
        }

        public void display(){
            image(bg,x,y);
        }
        //Funcion que redibuja la imagen
        public void loop(){
            if(y==0.0){
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
        int c; // color RGB

        //posicion de los bordes
        float BAr() {
            return y - largo / 2;
        }

        float BAb() {
            return y + largo / 2;
        }

        //constructor
        bus(float tx, float ty, float a, float l) {
            x = tx;
            y = ty;
            ancho = a;
            largo = l;
            vX = 0;
            c = 255;

        }

        public int colisiones(int tamañoobjeto,float []xarray,float []yarray,int tamañoarreglo) {
            int h=0;
            for(int i=0;i<tamañoarreglo;i++) {
              float obsx = xarray[i];
              float obsy = yarray[i];
            if ((x>=obsx && x<=obsx+tamañoobjeto) && (y<=obsy+tamañoobjeto && y>=obsy)) {
                System.out.println("choque");
                h =i;
                break;
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
        ArrayList <Float> obsta = o.generador();

        //Constructor
        obstaculo( float o_y, float Voy,int pos) {
            oy = o_y;
            h = oy; //Guardo su posicion inicla en y para que vuelva a ella al momento de llegar a y =0.0
            ox = obsta.get(pos);
            voy = Voy;
        }




        public void move(){
            oy = oy + voy;
        }

        public void  display(){
            fill(102, 102, 255);
            rect(ox,oy,40,40);
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


