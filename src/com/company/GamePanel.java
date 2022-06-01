package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    //define screen size
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    //define object size
    static final int UNIT_SIZE = 15;
    //
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    // define Snake speed
    static final int DELAY = 80;
    // create x and y arrays for Snake body part
    final  int x[] = new int[GAME_UNITS];
    final  int y[] = new int[GAME_UNITS];
   //define initial Snake body
    int bodyParts = 5;
    // define how many apples are eaten
     int applesEaten;
     // where do the apple appears
    int appleX;
    int appleY;
     //where does the snake moves first
    String direction = "L";
    boolean running = false;
    //Set apple appearance
    Timer timer;
    Random random;

    GamePanel() {
    //define random aperience
        random = new Random ();
        //define
    this.setPreferredSize (new Dimension(SCREEN_HEIGHT,SCREEN_WIDTH));
    this.setBackground (Color.black);
    this.setFocusable (true);
    this.addKeyListener (new MyKeyAdapter ());
    startGame ();
    }
    public void  startGame(){
        anotherApple ();
        running = true;
        timer = new Timer (DELAY,this);
        timer.start ();

}
    public void paintComponent(Graphics g){
        super.paintComponent (g);
        draw (g);
}

    public  void  draw( Graphics g) {
       g.setColor (Color.blue);
       g.fillOval ( appleX, appleY, UNIT_SIZE, UNIT_SIZE );
      //  for (int i = 0; i<SCREEN_HEIGHT / UNIT_SIZE;i++){
      //      g.drawLine (i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
       //     g.drawLine (0, i* UNIT_SIZE,  SCREEN_HEIGHT, i*UNIT_SIZE );
      //  }
        for( int  i = 0; i<bodyParts; i++){
            if ( i ==0){
                g.setColor (Color.green);
                g.fillRect (x[i], y[i], UNIT_SIZE, UNIT_SIZE);

            }else{
                g.setColor (new Color (81, 180,0));
                g.fillRect (x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }
    public void anotherApple () {
     appleX = random.nextInt ((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
     appleY = random.nextInt ((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public  void  move() {
        int i;
        for (i = bodyParts; i > 0;i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case "U":
                y[0] = y[0] - UNIT_SIZE;
                break;
            case "D":
                y[0] = y[0] + UNIT_SIZE;
                break;
            case "L":
                x[0] = x[0] -UNIT_SIZE;
                break;
            case "R":
                x[0] = x[0] + UNIT_SIZE;
                break;

        }
    }
      public  void checkApple(){
}

      public  void checkCollisions(){
        for( int i = bodyParts; i>0; i--){
            if((x[0] == x[i]) && (y[0] ==y[i])){
                running = false;
            }
        }
}

     public void gameOver(Graphics g){
}

    @Override
    public void actionPerformed(ActionEvent e) {

         if(running){
             move ();
             checkApple ();
             checkCollisions ();
         }
         repaint ();
    }

    public class MyKeyAdapter extends KeyAdapter{
    @Override
        public  void  keyPressed(KeyEvent e){

    }
    }
}
