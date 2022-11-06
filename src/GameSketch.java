import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import java.awt.*;
import java.util.ArrayList;
public  class GameSketch extends PApplet {


    //VARIABLES DE CARACTER GLOBAl
    int GameState =0; //0 MENU  1 JUEGO 2 GANADORES
    float vObs = 15;
    int cant = 10;

    int dirB1=1;
    int dirB2=1;
    int winner;
    int  width = 600;
    boolean IAinU = true;
    int w=1;
    int t=1;
    PFont sfont;
    PFont Classroom;
    obstaculo a,b,c,d,e,f,g,h,i,j,k;
    bus b1;
    bus b2;
    PImage bg,puerto,fmenu,skin1,skin2;

    calle c1;
    calle c2;
    goal m1,m2;


    Obstaculos o = new Obstaculos(cant);



    //METODOS DE CARACTER GLOBAL

    public void SlidingB1 (){
        int tsc1 = 100;
        while(tsc1>0) {
            dirB1=-1;
            tsc1 = tsc1-1;
            delay(19);
        }
        dirB1=1;
        b1.isSliding=false;
    }

    public void SlidingB2 (){
        int tsc1 = 100;
        while(tsc1>0) {
            dirB2=-1;
            tsc1 = tsc1-1;
            delay(19);
        }
        dirB2=1;
        b2.isSliding=false;
    }


    public void speedDownC1(){
        float saversd1 = c1.v;
        int tsc1 = 80;
        while(tsc1>0) {
            c1.v= 2;
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
            tg = tg -1;
            delay(19);
        }
        c2.v = saverd2;
    }
    public void speedUPC1(){
        float saverc1 = c1.v;
        int r = 80;
        while(r>0) {
            c1.v= 20;
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
            r = r-1;
            delay(19);
        }
        c2.v = saverc2;
    }

    public void finishVidas(){
        GameState=2;
    }
    @Override
    public void settings() {
        size(width, 600);
    }
    @Override
    public void setup() {
        bg= loadImage("images/background2.jpg");
        skin1= loadImage("images/firstmenu.jpg");
        skin2= loadImage("images/bu.png");
        puerto = loadImage("images/puerto.png");
        fmenu = loadImage("images/firstmenu.jpg");
        sfont = createFont("fonts/SketchBook-B5pB.ttf",20);
        Classroom = createFont("fonts/ClassroomPersonalUse-7Bv34.ttf",30);
        frameRate(45);
        b1= new bus(width/4+width/2,height,40,80,skin1,6,false,false,false,1,false);
        if(IAinU){
            b1.vX=1;
        }
        b2= new bus(width/2-width/4,height,40,80,skin2,6,false,false,false,2,false);
        imageMode(CORNER);
        rectMode(CORNER);
        bg.resize((int) (width/2+b1.ancho), height*5);
        skin1.resize(40,80);
        skin2.resize(40,80);
        c1= new calle(-20,-1500,17);
        c2= new calle(width/2-20,-1500,17);
        m1 = new goal(width/2+10,-10000,b1,c2);
        m2 = new goal(10,-10000,b2,c1);
        a = new obstaculo(-2000,15,0,55,55,1);
        b = new obstaculo(-3000,15,1,55,55,1);
        c = new obstaculo(-2800,15,2,55,55,1);
        d = new obstaculo(-3700,15,3,55,55,1);
        e = new obstaculo(-2600,15,4,55,55,2);
        f= new obstaculo(-3600,15,5,55,55,2);
        g= new obstaculo(-2900,15,6,55,55,2);
        h= new obstaculo(-2800,15,7,55,55,2);
        i= new obstaculo(-2500,15,8,55,55,3);

    }


    //manejar teclas presionadas
    @Override
    public void keyPressed() {

        if (keyCode == LEFT && !IAinU) {
            b1.vX = -5*dirB1;
        }
        if (keyCode == RIGHT && !IAinU) {
            b1.vX = 5*dirB1;
        }
        if (key == 'a') {
            b2.vX = -5*dirB2;
        }
        if (key == 'd') {
            b2.vX = 5*dirB2;
        }
    }

    // manejar teclas soltadas
    @Override
    public void keyReleased() {
        if(GameState == 0){
            GameState = 1 ;
        }
        if(GameState == 2){
            if(keyCode == ENTER) {
                GameState = 0;
            }
        }
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

    //METODOS DE EJECUCION DEL JUEGO
    void game(){
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


        f.loop();
        f.display();
        f.move();

        g.loop();
        g.display();
        g.move();

        h.loop();
        h.display();
        h.move();

         //choques bus 1 (derecha)
        a.choque(b1);
        b.choque(b1);
        c.choque(b1);
        d.choque(b1);
        e.choque(b1);
        f.choque(b1);
        g.choque(b1);
        h.choque(b1);

        //choques bus 2 (izquierda)
        a.choque(b2);
        b.choque(b2);
        c.choque(b2);
        d.choque(b2);
        e.choque(b2);
        f.choque(b2);
        g.choque(b2);
        h.choque(b2);



        m1.display();
        m1.move();
        m1.finish();

        m2.display();
        m2.move();
        m2.finish();


        b1.display();
        if(IAinU) {
            IAmove(b1);
        }
        if( b1.isSliding && Math.random()>0.5){
            b1.vX=-b1.vX;
        }
        b1.move();


        b2.display();
        b2.move();


         i.loop();
        i.display();
        i.move();
        i.choque(b1);
        i.choque(b2);




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
            thread("SlidingB1");
        }
        if(b2.colPol){
            thread("SlidingB2");
        }

        if(b1.vidas==0){
            winner =2;
            finishVidas();
        }

        if(b2.vidas==0){
            winner =1;
            finishVidas();
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
    void mainmenu(){
        background(fmenu);
        textFont(Classroom);
        textAlign(CENTER);
        text("Presione cualquier\n tecla para continuar",width/2,height/2);


    }
    void winnermenu(){
        background(255);
    }




    @Override
    public void draw() {
        System.out.println(GameState);
        if(GameState==0){
            mainmenu();
        } else if(GameState==1){
            game();
        }else if(GameState==2){
            winnermenu();
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
            if(y>=-10.0){
                y=-1500;

            }
        }


    }


    //clase bus
    class bus {
        boolean colObs;
        boolean colVel;
        boolean colPol;

        boolean isSliding;
        int num;
        PImage skin;
        int vidas;
        float x; //posicion en x
        float y; // posicion en y
        float vX; // velocidad X
        float ancho;
        float largo;
        int co; // color RGB


        //posicion de los bordes
        float BAr() {
            return y - largo / 2;
        }

        float BAb() {
            return y + largo / 2;
        }

        //constructor
        bus(float tx, float ty, float a, float l,PImage tipo, int Vidas, Boolean col, Boolean colv,Boolean colp, int Num,Boolean fly) {
            isSliding=false;
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
            co = 255;

        }

        //mover bus
        public void move() {
            x += vX;
        }


        //mostrar-generar bus
        public void display() {
            image(skin,x-ancho,y-largo);

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
                    this.oy = 690;
                    bus.colVel = true;
                }
                if(this.tipo==3){
                    this.oy = 690;
                    bus.colPol= true;
                    bus.isSliding=true;
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
                oy = -3700;
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
                b2.vX=0;
                c1.v=0;
                c2.v=0;
                a.voy=0;
                b.voy=0;
                c.voy=0;
                d.voy=0;
                e.voy=0;
                f.voy=0;
                g.voy=0;
                h.voy=0;
                i.voy=0;
                GameState =2;
                if(bus.num==1){
                    System.out.println("Gano el bus 1");
                }else {
                    System.out.println("Gano el bus 2");
                }
                w=0;
            }
        }

    }

    boolean dodge = true;
    public void IAmove(bus bu){
        // reemplazar toda esta funcion
        boolean x = comp(bu,a) || comp(bu,b) || comp(bu,c) || comp(bu,d);
        boolean y = bu.x >= width || bu.x - bu.ancho <= width/2;
        if(x && dodge ) {
            t=-t;
            dodge =false;
        }
        if(y && Math.random()>0.5 && dodge ) {
                t = -t;
        }
        bu.vX= 6 *t;
        if(x==false){ dodge=true;}
    }

    public boolean comp(bus b,obstaculo o){
        // reemplazar toda esta fucion
        Rectangle rec1 = new Rectangle((int)(o.ox-20),(int)(o.oy),(int)(o.anchoobs + 25 ),(int)(o.alturaobs+ 200));
        Rectangle rec2 = new Rectangle((int)(b.x-b.ancho),(int)(b.y-b.largo),(int)(b.ancho),(int)(b.largo));
        if(rec1.intersects(rec2) && o.ox>=width/2-55){
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