import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import java.util.Random;

public  class GameSketch extends PApplet {
    int cant = 8;
    int time = 0;

    float acc1 = 0.5f;
    float acc2 = 0.5f;

    int t=1;
    Obstaculos o = new Obstaculos(cant);
    int  width = 600;
    float xr;
    obstaculo a,b,c,d,e,f,g;
    bus b1;
    bus b2;
    PImage bg;
    calle c1;
    calle c2;

    goal meta;

    @Override
    public void settings() {
        size(width, 600);
    }


   //Metodo generador de posicion inicial en x de los obstaculos

    @Override
    public void setup() {
        frameRate(60);
        b1= new bus(width/4+width/2,height,40,80);
        b1.vX=1;
        b2= new bus(width/2-width/4,height,40,80);
        bg= loadImage("background2.jpg");
        imageMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,1);
        c2= new calle(width/2-20,-1500,1);
        meta= new goal(width/2,-1000,b1,c2);
        a = new obstaculo(-1000,15,0);
        b = new obstaculo(-1500,15,1);
        c = new obstaculo(-2000,15,3);
        d = new obstaculo(-2500,15,4);
        e = new obstaculo(-3000,15,5);
        f= new obstaculo(-4000,15,6);
        g = new obstaculo(-3500,15,7);


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
        if(acc2 != 0) {
            IAmove(b1);
        }
        b1.move();
        b2.display();
        b2.move();

        meta.display();
        meta.move();
        meta.finish();

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


        if( (c1.v<10 || c2.v<10) && millis()-time>1000){
            if(c1.v<10) c1.v+= acc1;
            if(c2.v<10) c2.v+= acc2;
            time=millis()-time;
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

    public void IAmove(bus bu){
        if ( comp(bu,a) || comp(bu,b) || comp(bu,c) || comp(bu,d) || comp(bu,e) || comp(bu,f) || comp(bu,g)) {

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
                oy = -3500;
                ArrayList <Float> obsta2 = o.generador();
                ox = obsta2.get((int) (Math.random() * cant) + 1);
            }
        }

    }

    class goal{
        float x;
        float y;
        bus b;
        calle c;

        goal(float ox, float oy, bus ob,calle oc){
            this.x  = ox;
            this.y  = oy;
            this.b  = ob;
            this.c  = oc;
        }

        public void  display(){
            fill(255);
            rect(x,y,width/2,40);
        }

        public void move(){
            this.y += this.c.v;
        }

        public void finish() {
            if(this.y > this.b.BAr()+20){
                this.b.vX=0;
                this.c.v=0;
                acc2=0;
            }
        }

    }
    public boolean comp(bus b,obstaculo o){
        boolean c = o.ox>width/2;
        boolean a = b.x-b.ancho/2 > o.ox+17 && b.x-b.ancho/2 < o.ox+23 && o.oy+40>b.BAr()-80;

        if( a || (c && o.oy+40>=b.BAr()) ){
            return true;
        }else{
            return false;
        }
        /*
        if (o.ox+20 >= b.x-b.ancho && o.ox+20 <= b.x+b.ancho/2 && o.oy> b.BAr()-500){
            return true;
        }else {
            return false;
        }
        */
    }



    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }


}


