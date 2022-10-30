import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;
import java.util.ArrayList;
public  class GameSketch extends PApplet {
    //VARIABLES DE CARACTER GLOBAl
    int cant = 8;
    int  width = 600;
    float speedDownDelay = 3000;
     Boolean calle1c = false;
     Boolean calle2c = false;

    obstaculo a,b,c,d,e;
    bus b1;
    bus b2;
    PImage bg,puerto;
    calle c1;
    calle c2;


    Obstaculos o = new Obstaculos(cant);
    boolean ejecutar = false;



    //METODOS DE CARACTER GLOBAL



    public void loop(){
        float saver = c1.v;
        int rt = 80;
        while(rt>0) {
            c1.v= 5;
            System.out.println(rt);
            rt= rt-1;
            delay(20);
        }
        c1.v = saver;
    }










    @Override
    public void settings() {
        size(width, 600);
    }
    @Override
    public void setup() {
        bg= loadImage("background2.jpg");
        puerto = loadImage("puerto.png");
        frameRate(50);
        b1= new bus(width/4+width/2,height,40,80,puerto,6,false);
        b2= new bus(width/2-width/4,height,40,80,puerto,6,false);
        imageMode(CORNER);
        rectMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,15);
        c2= new calle(width/2-20,-1500,15);
        a = new obstaculo(-2000,10,0,55,55);
        b = new obstaculo(-3000,10,1,55,55);
        c = new obstaculo(-2800,10,2,55,55);
        d = new obstaculo(-3700,10,3,55,55);
        e = new obstaculo(-2600,10,4,55,55);
    }


    public void speedDownC1(){
        float saver = c1.v;
        int rt = 80;
        while(rt>0) {
            c1.v= 2;
            System.out.println(rt);
            rt = rt-1;
            delay(20);
        }
        c1.v = saver;
    }

    public  void speedDownC2(){
        float saver = c2.v;
        int rt = 80;
        while(rt>0) {
            c2.v= 2;
            System.out.println(rt);
            rt = rt-1;
            delay(20);
        }
        c2.v = saver;
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

        //Limpio colisiones de ambos buses
        b1.colision = false;
        b2.colision = false;





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
        b.choque(b1,2);
        b.choque(b2,1);
        a.loop();
        a.display();
        a.move();
        a.choque(b1,2);
        a.choque(b2,1);

        c.loop();
        c.display();
        c.move();
        c.choque(b1,2);
        c.choque(b2,1);

        d.loop();
        d.display();
        d.move();
        d.choque(b1,2);
        d.choque(b2,1);
        e.loop();
        e.display();
        e.move();
        e.choque(b1,2);
        e.choque(b2,1);
        if(b1.colision){
            b1.vidas = b1.vidas-1;
            thread("speedDownC2");
        }
        if(b2.colision){
            b2.vidas = b2.vidas-1;
            thread("speedDownC1");
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
        boolean colision;
        PImage skin;
        int vidas;
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
        bus(float tx, float ty, float a, float l,PImage tipo, int Vidas, Boolean col) {
            colision = col;
            vidas = Vidas;
            x = tx;
            skin = tipo;
            y = ty;
            ancho = a;
            largo = l;
            vX = 0;
            c = 255;

        }

        public  void velocity(){}


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
        ArrayList<Float> obsta = o.generador();

        //Constructor
        obstaculo(float o_y, float Voy, int pos, int alto, int ancho) {
            oy = o_y;
            h = oy; //Guardo su posicion inicla en y para que vuelva a ella al momento de llegar a y =0.0
            ox = obsta.get(pos);
            voy = Voy;
            alturaobs = alto;
            anchoobs = ancho;
        }


        public int  choque(bus bus, int ncalle){ //Pide como parametro el bus y el numero de la calle en la que esta ese bus
            Rectangle rec1 = new Rectangle((int)(ox),(int)(oy),(int)(anchoobs),(int)(alturaobs));
            Rectangle rec2 = new Rectangle((int)(bus.x),(int)(bus.y),(int)(bus.ancho),(int)(bus.largo));
            if(rec2.intersects(rec1)){
                bus.colision = true;
                System.out.println("Choque");
                this.oy = 690;
                bus.colision = true;
                delay(60);

            }
            return 0;
        }

        public void move() {
            oy = oy + voy;
        }

        public void display() {
            fill(0, 255, 208);
            rect(ox, oy, alturaobs, anchoobs);
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