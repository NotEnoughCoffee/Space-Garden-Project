package dev.sgp.gui;

import dev.sgp.entity.Player;
import dev.sgp.utility.GameScreen;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
                            implements Runnable {
    //main GUI, implements all screen elements and game thread

    //SCREEN SETUP//

    //Tile Size + Scaling//
    final static int ogTileSize = 16;
    final static int scale = 2;
    final public static int tileSize = ogTileSize * scale;

    //Grid Definition 16:9 Aspect//
    final public static int gridWidth = 16 * 2;
    final public static int gridHeight = 9 * 2;

    //Screen Pixel Dimensions//
    final public static int screenPixelWidth = gridWidth * tileSize;
    final public static int screenPixelHeight = gridHeight * tileSize;

    //GUI ELEMENTS//
    public KeyInput keyInput = new KeyInput(this);
    public GameScreen currentScreen = GameScreen.DEFAULT;
    //Etc.

    //GAME THREAD//
    Thread gameThread;
    final int FPS = 60;
    final long nanoTimeSecond = 1_000_000_000; // (1 Second = 1 Billion Nano)

    //ASSETS//
    public Player player = new Player(this, keyInput);
    //Items... NPCs... Etc.

    //MEMORY//

    //RENDER TIME//
    public static boolean maintenance = true;
    private int drawTimeCounter = 0;
    private long timePassedAverage = 0;

    //INITIALIZATION//
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenPixelWidth,screenPixelHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyInput);
        this.setFocusable(true);

        startGame();//placeholder -> move function when menus added
    }
    public void startGame() {
        //Placeholder -> load game save and start at last position.
        //Load and begin playing bg music.
        //load world objects?

        currentScreen = GameScreen.PLAY;
    }
    public void runGameThread() { //Starts Game Clock and Screen Refreshing
        gameThread = new Thread(this);
        gameThread.start();
    }

    //GAME LOOP + SCREEN REFRESH//
    @Override
    public void run() { //called when thread.start();
        //Time Between Frames in nano time
        double updateInterval = (double) nanoTimeSecond / FPS;

        double timePassed = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            timePassed += (currentTime - lastTime) / updateInterval;
            //increments and keeps track of nano time passed between frames
            lastTime = currentTime;

            if(timePassed >= 1) { //Refresh Screen on FPS interval
                update();
                repaint();
                timePassed--;
            }
        }

    }
    public void update() {
        player.update();
        if(currentScreen == GameScreen.PLAY) {

            // activate entities update methods
        } // else if based on other screens?
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D)graphics;

        long drawStart = System.nanoTime();

        //call draw methods on elements passing g2D
        //tiles/background
        player.draw(g2D);
        //player / entities
        //hud
        //etc.

        long drawEnd = System.nanoTime();
        if(maintenance) {
            renderTime(drawStart, drawEnd);
        }
        g2D.dispose();
    }


    //MAINTENANCE//
    private void renderTime (long drawStart, long drawEnd) {
        //if maintenance is toggled checks time it takes to render graphics
        long timePassed = drawEnd - drawStart;
        drawTimeCounter++;
        timePassedAverage += timePassed;
        if(drawTimeCounter >= (FPS * 5)) { // 5 Seconds
            timePassedAverage = timePassedAverage / drawTimeCounter;
            //average render speed in nano time
            double renderSeconds = (double) timePassedAverage / nanoTimeSecond;
            System.out.printf("Average Render Time over 5 Seconds: %.5f seconds%n", renderSeconds);
            drawTimeCounter = 0;
            timePassedAverage = 0;
        }
    }
}
