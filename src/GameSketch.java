import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.util.Random;

public  class GameSketch extends PApplet {
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

    @Override
    public void settings() {
        size(width, 600);
    }


   //Metodo generador de posicion inicial en x de los obstaculos

    @Override
    public void setup() {
        frameRate(60);
        b1= new bus(width/4+width/2,height,40,80);
        b2= new bus(width/2-width/4,height,40,80);
        bg= loadImage("background2.jpg");
        imageMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,10);
        c2= new calle(width/2-20,-1500,10);
        a = new obstaculo(-2000,15,0);
        b = new obstaculo(-3000,15,1);
        c = new obstaculo(-2800,15,3);
        d = new obstaculo(-3700,15,4);
        e = new obstaculo(-2600,15,5);
        f= new obstaculo(-2400,15,6);
        g = new obstaculo(-3300,15,7);


        //Objetos del tipo obstaculo

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
        background(0); // reset del fondo
        //orden igual a capa
        System.out.println(frameRate);
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

        a.display();
        a.move();
        a.loop();

        b.display();
        b.move();
        b.loop();

        c.display();
        c.move();
        c.loop();

        d.display();
        d.move();
        d.loop();

        e.display();
        e.move();
        e.loop();

        f.display();
        f.move();
        f.loop();

        g.display();
        g.move();
        g.loop();






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
            fill(255);
            rect(ox,oy,40,40);
        }

        public void loop(){
            if(oy>600){
                System.out.println("PING");
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


