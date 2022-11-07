import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.awt.*;
import java.util.ArrayList;
import gifAnimation.*;
import javax.swing.*;

public  class GameSketch extends PApplet {


    //VARIABLES DE CARACTER GLOBAl
    int GameState =1; //0 MAINMENU 1 SELECTBUSMENU 2 GAME 3 WINNER MENU
    int cant = 10;
    int busPlayer = -1;
    int FinishTime;
    Button bSombusa,bChomdis,bPuecto;
    int dirB1=1;
    int dirB2=1;
    int winner;
    int  width = 600;
    boolean IAinU = true;
    int w=1;
    int t=1;
    Gif menuAni,winmenu,losemenu;
    PImage menusel;
    PFont sfont;
    PFont Classroom;
    PFont retrogaming;
    obstaculo a,b,c,d,e,f,g,h,i,j,k;
    bus b1;
    bus b2;
    PImage bd,bi,alerta,meta;
    PImage skinp, skinm;
    PImage VelBon, PolCos, VelDb;
    PImage unaVidas, dosVidas, tresVidas,creditos,marco;
    PImage chomdis,sombusa,puecto;
    calle c1;
    calle c2;
    goal m1,m2;
    ImageIcon junioricon = new ImageIcon("src\\data\\icons\\junior.png");
    Obstaculos o = new Obstaculos(cant);


    //METODOS DE CARACTER GLOBAL



    void drawAlerta(){
        image(alerta,0,0);
    }




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
            c1.v= 30;
            r = r-1;
            delay(19);
        }
        c1.v = saverc1;
    }
    public void speedUPC2(){
        float saverc2 = c2.v;
        int r = 80;
        while(r>0) {
            c2.v= 30;
            r = r-1;
            delay(19);
        }
        c2.v = saverc2;
    }
    public void finishVidas(){
        FinishTime=millis()/1000;
        GameState=4;
    }
    @Override
    public void settings() {
        size(width, 600);
    }
    @Override
    public void setup() {
        frameRate(45);
        unaVidas = loadImage("images/ingame/background/1vida.png");
        dosVidas = loadImage("images/ingame/background/2vidas.png");
        tresVidas = loadImage("images/ingame/background/3vidas.png");
        chomdis = loadImage("images/ingame/buses/chomdis.png");
        puecto = loadImage("images/ingame/buses/puecto.png");
        sombusa = loadImage("images/ingame/buses/sombusa.png");
        VelBon = loadImage("images/ingame/obs/aguila.png");
        PolCos = loadImage("images/ingame/obs/policiaacostado.png");
        VelDb = loadImage("images/ingame/obs/demandadian.png");
        creditos =loadImage("images/creditsmenu.jpg");
        meta = loadImage("images/ingame/background/meta.png");
        marco = loadImage("images/ingame/background/marco.png");
        VelBon.resize(65,65);
        PolCos .resize(65,65);
        VelDb.resize(65,65);
        chomdis.resize(40,80);
        puecto.resize(40,80);
        sombusa.resize(40,80);
        skinm = chomdis;





        b1= new bus(width/4+width/2,height,40,80,skinm,3,false,false,false,1,false);
        if(IAinU){
            b1.vX=1;
        }
        b2= new bus(width/2-width/4,height,40,80,skinp,3,false,false,false,2,false);
        bi= loadImage("images/ingame/background/carretera.png");
        bd= loadImage("images/ingame/background/carreteraD.png");
        alerta = loadImage("images/ingame/background/alerta.png");
        bi.resize((int) (350), height*5);
        bd.resize((int) (300), height*5);
        retrogaming = createFont("fonts/retrogaming.ttf",20);

        winmenu = new Gif(this,"images/winmenu.gif");
        winmenu.play();
        losemenu = new Gif(this,"images/losemenu.gif");
        losemenu.play();
        /*
        menuAni = new Gif(this, "images/menugif.gif");
        menuAni.play();

         */
        menusel = loadImage("images/mensel.png");
        bSombusa = new Button(60,120,300,100,"SOMBUSA",1);
        bPuecto = new Button(60,240,300,100,"PUECTO LOCOMBIA",2);
        bChomdis = new Button(60,360,300,100,"CHOMDIS",3);
        retrogaming = createFont("fonts/retrogaming.ttf",20);





        imageMode(CORNER);
        rectMode(CORNER);
        c1= new calle(-20,-1500,20,bi);
        c2= new calle(width/2+20,-1500,20,bd);
        m1 = new goal(width/2+10,-30000,b1,c2);
        m2 = new goal(10,-30000,b2,c1);
        a = new obstaculo(-2000,15,0,55,55,1,VelDb);
        b = new obstaculo(-3000,15,1,55,55,1,VelDb);
        c = new obstaculo(-2800,15,2,55,55,1,VelDb);
        d = new obstaculo(-3700,15,3,55,55,1,VelDb);
        e = new obstaculo(-2600,15,4,55,55,2,VelBon);
        f= new obstaculo(-3600,15,5,55,55,2,VelBon);
        g= new obstaculo(-2900,15,6,55,55,2,VelBon);
        h= new obstaculo(-2800,15,7,55,55,2,VelBon);
        i= new obstaculo(-2500,15,8,55,55,3,PolCos);
        j = new obstaculo(-2400,15,9,55,55,3,PolCos);
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
        if(GameState == 0 && keyCode==ENTER){
            GameState = 1 ;
        }
        if(GameState == 0 && keyCode==CONTROL){
            GameState = 5;
        }
        if(GameState == 5 && keyCode==CONTROL){
            GameState = 0;
        }

        if((GameState==3 || GameState==4)&& keyCode==ENTER){
            GameState = 0;
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


        //Inicio obstaculos debuff de velocidad
        a.loop();
        a.display();
        a.move();
        a.choque(b1);
        a.choque(b2);


        b.loop();
        b.display();
        b.move();
        b.choque(b1);
        b.choque(b2);


        c.loop();
        c.display();
        c.move();
        c.choque(b1);
        c.choque(b2);



        //Final de obstaculos de velocidad debuff

        //Inicio de obstaculos de tipo aumento de velocidad



        f.loop();
        f.display();
        f.move();
        f.choque(b1);
        f.choque(b2);

        h.loop();
        h.display();
        h.move();
        h.choque(b1);
        h.choque(b2);


        //Fin de obstaculo de tipo aumento de velocidad

        //Inicio de obstaculos de tipo policia acotado
        i.loop();
        i.display();
        i.move();
        i.choque(b1);
        i.choque(b2);

        j.loop();
        j.display();
        j.move();
        j.choque(b1);
        j.choque(b2);






        m1.display();
        m1.move();
        m1.finish();

        m2.display();
        m2.move();
        m2.finish();



        if(IAinU) {
            IAmove(b1);
        }
        if(b1.isSliding && Math.random()>0.5){
            b1.vX=-b1.vX;
        }

        if(b2.isSliding){
            drawAlerta();
        }






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


        b1.display();
        b1.move();
        b2.display();
        b2.move();

        image(marco,0,0);

        if(b1.x<width/2+35){
            b1.x=width/2+35;
        }
        if(b1.x>width-50){
            b1.x=width-50;
        }
        if(b2.x<b2.ancho+30){
            b2.x=b2.ancho+30;
        }
        if(b2.x>width/2-b2.ancho/2-30){
            b2.x=width/2-b2.ancho/2-30;
        }

        if(b2.vidas==3){
            image(tresVidas,0,0);
        }else if(b2.vidas==2){
            image(dosVidas,0,0);
        }else if(b2.vidas==1){
            image(unaVidas,0,0);
        }




    }
    void mainmenu(){
        textAlign(CENTER);
        text("cargando",0,0);
        image(menuAni,0,0);
        textFont(retrogaming);
        textAlign(LEFT,BOTTOM);
        text("CREDITOS: CONTROL",40,40);


    }
    void winnermenu(){
        background(winmenu);
        textFont(retrogaming);
        textAlign(CENTER);
        textSize(25);
        text("Te embalaste por "+FinishTime+" Segundos",300,500);
    }
    void losermenu(){
        background(losemenu);
        textFont(retrogaming);
        textAlign(CENTER);
        textSize(25);
        fill(203, 50, 52);
        text("Te embalaste por "+FinishTime+" Segundos",300,500);
    }
    void selectmenu(){
        background(menusel);
        bSombusa.display();
        bSombusa.clicked(mouseX,mouseY);
        bChomdis.display();
        bChomdis.clicked(mouseX,mouseY);
        bPuecto.display();
        bPuecto.clicked(mouseX,mouseY);
        delay(100);
    }
    void creditos(){
        background(creditos);
    }

    @Override
    public void draw() {

        switch (GameState){
            case  0:
                mainmenu();
                break;
            case 1:
                selectmenu();
                break;
            case 2:
                game();
                break;
            case 3:
                winnermenu();
                break;
            case 4:
                losermenu();
                break;
            case 5:
                creditos();
                break;
        }



    }

    class calle{
        float x;
        float y;
        float v;
        PImage c;
        //si

        calle(float tx, float ty, float tv, PImage C){
            x=tx;
            y=ty;
            v=tv;
            c = C;
        }

        public void move() {
            y+=v;
        }

        public void display(){
            image(c,x,y);
        }

        //Funcion que redibuja la imagen
        public void loop(){
            if(y>=0.0){
                y=-1200;

            }
        }


    }
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
            isSliding= false;
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
    class obstaculo {
        int tipo; //1 OBSTACULO, 2 BONOVELOCIDAD 3 POLICIACOSTADO
        float ox, oy;
        float voy;
        float h;
        PImage skin;

        float alturaobs, anchoobs;
        ArrayList<Float> obsta = o.generador();

        //Constructor
        obstaculo(float o_y, float Voy, int pos, int alto, int ancho, int TIPO,PImage s) {
            tipo = TIPO;
            oy = o_y;
            h = oy; //Guardo su posicion inicla en y para que vuelva a ella al momento de llegar a y =0.0
            ox = obsta.get(pos);
            voy = Voy;
            alturaobs = alto;
            anchoobs = ancho;
            skin = s;
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
                    bus.isSliding = true;
                }



            }

        }

        public void move() {
            oy = oy + voy;
        }
        public void display() {
            image(skin,ox,oy);
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
            image(meta,x,y);
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
                FinishTime=millis()/1000;
                if(bus.num==1){
                    System.out.println("Gano el bus 1");
                    GameState=4;
                }else {
                    System.out.println("Gano el bus 2");
                    GameState=3;
                }
                w=0;
            }
        }
    }


    boolean dodge = true;
    public void IAmove(bus bu){
        // reemplazar toda esta funcion
        boolean x = comp(bu,a) || comp(bu,b) || comp(bu,c) || comp(bu,d);
        boolean y = bu.x >= width-50 || bu.x - bu.ancho <= width/2+35;
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
        Rectangle rec1 = new Rectangle((int)(o.ox-10),(int)(o.oy),(int)(o.anchoobs + 15 ),(int)(o.alturaobs+ 200));
        Rectangle rec2 = new Rectangle((int)(b.x-b.ancho),(int)(b.y-b.largo),(int)(b.ancho),(int)(b.largo));
        if(rec1.intersects(rec2) && o.ox>=width/2-55){
            return true;
        }else{
            return false;
        }

    }






    class Button{
        ///INSTANCE VARIABLES
        float x,y; //position
        int nBoton;
        float w,h; //size
        boolean selected; //is the button selected / on? true/false
        int selectedColor, defaultColor, currentColor;
        String label;

        ///CONSTRUCTORS - no return type declared - match Class-name
        Button(float x, float y, float w, float h, String label, int n ){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.label = label;
            this.nBoton= n;
            selected = false;
            defaultColor = color( 255,255,255); //slightly darker?
            currentColor = defaultColor;
        }


        ///METHODS
        void display(){
            fill( currentColor);
            rect( x, y, w, h);
            fill( 0);//black for text
            textFont(retrogaming);
            textAlign(CENTER);
            textFont(retrogaming);
            text( label, x + w/2, y + (h/2));
        }

        void  clicked( int mx, int my){
            if( mx > x && mx < x + w  && my > y && my < y+h){
                //mouse has been clicked
                selected = !selected;  //toggle the value between true and false
                if( selected){
                    currentColor = color(232, 235, 68);
                    if(mousePressed){
                        GameState =2;
                        if(this.nBoton==1){
                            b2.skin= sombusa;
                        }else if(this.nBoton==2){
                            b2.skin= puecto;
                        } else if(this.nBoton==3) {
                            b2.skin= chomdis;
                        }
                    }
                }
            }else{
                currentColor = defaultColor;
            }

        }



    }  //end Button class






    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }


}