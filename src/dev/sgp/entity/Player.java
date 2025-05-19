package dev.sgp.entity;

import dev.sgp.gui.GamePanel;
import dev.sgp.gui.KeyInput;

import java.awt.*;

public class Player extends Entity {
    Point screenLocation; //centered unless at edge of map
    KeyInput keyInput;

    Rectangle temporaryPlayer = new Rectangle(0,0,GamePanel.tileSize, GamePanel.tileSize); //for testing purposes

    //inventory setup


    //INITIALIZATION//
    public Player(GamePanel gamePanel, KeyInput keyInput) {
        super(gamePanel);
        this.name = "Player";
        this.keyInput = keyInput;
        loadPlayer();
        spriteSetup();
        //buffer sound effects?

    }
    private void loadPlayer() {
        centerPlayerOnScreen();
        collisionSetup();

        mapLocation = getMapLocation("TEST");
        //Update later when maps are implemented.

        currentSpeed = 6;
        defaultSpeed = currentSpeed;
        direction = "down";
    }

    //UPDATE PLAYER//


    public void update() {
        setActiveDirection();
        move();

        //update sprite

    }
    public void draw(Graphics2D g2D) {
        //get direction and draw sprite facing correct direction
        g2D.setColor(Color.red);
        g2D.fillRect(mapLocation.x - temporaryPlayer.x, mapLocation.y - temporaryPlayer.y, GamePanel.tileSize, GamePanel.tileSize);

    }


    private void move() {
        //if collision not detected

        if(KeyInput.up) {
            mapLocation.y -= currentSpeed;
        }
        if(KeyInput.down) {
            mapLocation.y += currentSpeed;
        }
        if(KeyInput.left) {
            mapLocation.x -= currentSpeed;
        }
        if(KeyInput.right) {
            mapLocation.x += currentSpeed;
        }
    }
    public void setActiveDirection() {
        //sets direction -> should update the method on keyInput to determine so it is based off of last button pressed
        if(KeyInput.up) {
            direction = "up";
        }
        if(KeyInput.down) {
            direction = "down";
        }
        if(KeyInput.right) {
            direction = "right";
        }
        if (KeyInput.left) {
            direction = "left";
        }
    }





    //Unimplemented Methods//
    private void collisionSetup() {

    }

    private void spriteSetup() {

    }

    //UTILITY//
    private void centerPlayerOnScreen() {
        //Get offset for centering player on screen
        int x = GamePanel.screenPixelWidth/2 - GamePanel.tileSize/2;
        int y = GamePanel.screenPixelHeight/2 - GamePanel.tileSize/2;
        screenLocation = new Point(x,y);
    }

    private Point getMapLocation(String mapName) {
        int tile = GamePanel.tileSize;
        return switch (mapName.toUpperCase()) {
            //placeholder method for implementing multiple maps later
            case "TEST" -> new Point(5 * tile, 5 * tile);
            case "TEST2" -> new Point(10 * tile, 10 * tile);
            default -> new Point(tile,tile);
        };

    }
}
