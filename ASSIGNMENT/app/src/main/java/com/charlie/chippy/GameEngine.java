package com.charlie.chippy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.ColorRes;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="TAPPY-SPACESHIP";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;

    Bitmap background;
    int bgXPosition;


    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------
    Player player;
    Enemy enemy;

    // ----------------------------
    // ## GAME STATS
    // ----------------------------
    int score = 0;
    int lives = 5;

    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        // @TODO: Add your sprites

        this.spawnPlayer();
        this.spawnEnemies();

        // @TODO: Any other game setup

    }


    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }


    private void spawnPlayer(){

        this.player = new Player(this.getContext(),190,this.screenHeight/2 + 200);
    }

    private  void spawnEnemies(){


    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions() {
        // @TODO: Update position of player

        // @TODO: Update position of enemy ships


        // @TODO: Collision detection between enemy and wall


        // @TODO: Collision detection between player and enemy

    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();


            this.canvas.drawColor(Color.BLACK);
            // configure the drawing tools

            paintbrush.setColor(Color.WHITE);





            //PLAYER AND ITS HITBOX

            paintbrush.setStrokeWidth(5);
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);


            this.canvas.drawBitmap(this.player.getBitmap(),this.player.getXPosition(),this.player.getYPosition(),paintbrush);
            Rect playersHitBox = this.player.getHitbox();
            this.canvas.drawRect(playersHitBox.left,playersHitBox.top,playersHitBox.right,playersHitBox.bottom,paintbrush);




            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();

        float fingerXPosition = event.getX();
        float fingerYPosition = event.getY();

        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_MOVE) {



        }
        else if (userAction == MotionEvent.ACTION_UP) {
            // move player down

        }

        return true;
    }
}