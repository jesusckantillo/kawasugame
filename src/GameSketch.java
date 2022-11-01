import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;
import java.util.ArrayList;
public  class GameSketch extends PApplet {
    //VARIABLES DE CARACTER GLOBAl
    int cant = 10;
    int  width = 600;

    int w=1;
    obstaculo a,b,c,d,e,f,g,h,i,j,k;
    bus b1;
    bus b2;
    PImage bg,puerto;
    calle c1;
    calle c2;
    goal m1,m2;


    Obstaculos o = new Obstaculos(cant);



    //METODOS DE CARACTER GLOBAL

    public void jumpB1(){
        float ancho = 40;
        float largo = 80;
        int salto = 10;
        int caida = 10;
        while(salto>0){
            b1.ancho+=1;
            b1.largo+=1;
            salto = salto-1;
            delay(100);
        }
        while(caida>0){
            b1.ancho = b1.ancho-1;
            b1.largo = b1.largo -1;
            caida = caida -1;
            delay(100);

        }
        b1.ancho = ancho;
        b1.largo = largo;
    }


    public void jumpB2(){
        float ancho2 = 40;
        float largo2 = 80;
        int salto2 = 10;
        int caida2 = 10;
        while(salto2>0){
            b2.ancho+=1;
            b2.largo+=1;
            salto2 = salto2-1;
            delay(100);
        }
        while(caida2>0){
            b1.ancho = b1.ancho-1;
            b1.largo = b1.largo -1;
            caida2 = caida2 -1;
            delay(100);

        }

        b2.ancho = ancho2;
        b2.largo = largo2;
    }




    public void speedDownC1(){
        float saversd1 = c1.v;
        int tsc1 = 80;
        while(tsc1>0) {
            c1.v= 2;
            System.out.println(tsc1);
            tsc1 = tsc1-1;
            delay(19);
        }
        c1.v = saversd1;
    }

    public  void speedDownC2(){
     float saverd2 = c2.v;
     int tg = 80;
     while(tg>0){
         c2.v = 2;
         System.out.println(tg);
         tg = tg -1;
         delay(19);
     }
     c2.v = saverd2;
    }

    public void speedUPC1(){
        float saverc1 = c1.v;
        int r = 80;
        while(r>0) {
            c2.v= 20;
            System.out.println(r);
            r = r-1;
            delay(19);
        }
        c1.v = saverc1;
    }


    public void speedUPC2(){
        float saverc2 = c2.v;
        int r = 80;
        while(r>0) {
            c2.v= 20;
            System.out.println(r);
            r = r-1;
            delay(19);
        }
        c2.v = saverc2;
    }







    @Override
    public void settings() {
        size(width, 600);
    }
    @Override
    public void setup() {
        bg= loadImage("background2.jpg");
        puerto = loadImage("puerto.png");
        frameRate(40);
        b1= new bus(width/4+width/2,height,40,80,puerto,6,false,false,false,1);
        b2= new bus(width/2-width/4,height,40,80,puerto,6,false,false,false,2);
        imageMode(CORNER);
        rectMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        c1= new calle(-20,-1500,15);
        c2= new calle(width/2-20,-1500,15);
        m1 = new goal(width/2+10,-200000,b1,c1);
        m2 = new goal(10,-2000000,b2,c2);

        a = new obstaculo(-2000,15,0,55,55,1);
        b = new obstaculo(-3000,15,1,55,55,1);
        c = new obstaculo(-2800,15,2,55,55,1);
        d = new obstaculo(-3700,15,3,55,55,1);
        e = new obstaculo(-2600,15,4,55,55,1);
        f= new obstaculo(-3600,15,5,55,55,2);
        g= new obstaculo(-2900,15,6,55,55,2);
        h= new obstaculo(-2800,15,7,55,55,2);
        i= new obstaculo(-2500,15,8,55,55,2);

        i= new obstaculo(-2500,15,9,55,55,3);;
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
        b1.colObs = false;
        b1.colVel = false;

        b2.colObs = false;
        b2.colVel = false;

        b1.colPol = false;
        b2.colPol = false;


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
        b.choque(b1);
        b.choque(b2);


        a.loop();
        a.display();
        a.move();
        a.choque(b1);
        a.choque(b2);


        c.loop();
        c.display();
        c.move();
        c.choque(b1);
        c.choque(b2);


        d.loop();
        d.display();
        d.move();
        d.choque(b1);
        d.choque(b2);


        e.loop();
        e.display();
        e.move();
        e.choque(b1);
        e.choque(b2);

        f.loop();
       f.display();
       f.move();
        f.choque(b1);
        f.choque(b2);

       g.loop();
       g.display();
       g.move();
       g.choque(b1);
       g.choque(b2);

        h.loop();
        h.display();
        h.move();
        h.choque(b1);
        h.choque(b2);

        i.loop();
        i.display();
        i.move();
        i.choque(b1);
        i.choque(b2);


        m1.display();
        m1.move();
        m1.finish();

        m2.display();
        m2.move();
        m2.finish();




       // i.loop();
       // i.display();
       // i.move();
       // i.choque(b1);
        //i.choque(b2);




        if(b1.colObs){
            b1.vidas = b1.vidas-1;
            thread("speedDownC2");
        }
        if(b2.colObs){
            b2.vidas = b2.vidas-1;
            thread("speedDownC1");
        }

        if(b1.colVel){
            thread("speedUPC2");
        }
        if(b2.colVel){
            thread("speedUPC1");
        }

        if(b1.colPol){
            thread("jumpB1");
        }
        if(b2.colPol){
            thread("jumpB2");
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
        boolean colObs;
        boolean colVel;
        boolean colPol;
        int num;
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
        bus(float tx, float ty, float a, float l,PImage tipo, int Vidas, Boolean col, Boolean colv,Boolean colp, int Num) {
            num = Num;
            colPol = colp;
            colVel = colv;
            colObs = col;
            vidas = Vidas;
            x = tx;
            skin = tipo;
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
        int tipo; //1 OBSTACULO, 2 BONOVELOCIDAD 3 POLICIACOSTADO
        float ox, oy;
        float voy;
        float h;

        float alturaobs, anchoobs;
        ArrayList<Float> obsta = o.generador();

        //Constructor
        obstaculo(float o_y, float Voy, int pos, int alto, int ancho, int TIPO) {
            tipo = TIPO;
            oy = o_y;
            h = oy; //Guardo su posicion inicla en y para que vuelva a ella al momento de llegar a y =0.0
            ox = obsta.get(pos);
            voy = Voy;
            alturaobs = alto;
            anchoobs = ancho;
        }


        public void choque(bus bus){ //Pide como parametro el bus y el numero de la calle en la que esta ese bus
                    Rectangle rec1 = new Rectangle((int)(ox),(int)(oy),(int)(anchoobs),(int)(alturaobs));
                    Rectangle rec2 = new Rectangle((int)(bus.x-bus.ancho),(int)(bus.y-bus.largo),(int)(bus.ancho),(int)(bus.largo));
                    if(rec2.intersects(rec1)){
                        if(this.tipo==1){
                            this.oy = 690;
                            bus.colObs = true;
                            delay(60);
                        }
                        if(this.tipo==2){
                            bus.colVel = true;
                        }
                        if(this.tipo==3){
                            bus.colPol= true;
                        }



                    }

        }

        public void move() {
            oy = oy + voy;
        }










        public void display() {
            if(this.tipo==1) {
                fill(0, 255, 208);
                rect(ox, oy, alturaobs, anchoobs);
            }
            if(this.tipo==2){
                fill(90, 3, 252);
                rect(ox, oy, alturaobs, anchoobs);
            }
            if(this.tipo==3){
                fill(66, 245, 102);
                rect(ox, oy, alturaobs, anchoobs);
            }

        }
        public void loop(){
            if(oy>700){
                oy = h;
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

    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }


}