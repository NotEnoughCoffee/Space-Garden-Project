package dev.sgp.entity;

import dev.sgp.gui.GamePanel;

import java.awt.*;

public class Entity {
    //placeholder base class for players and npc to extend
    public String name, direction;
    GamePanel gamePanel;
    public Point mapLocation; //pixel position of upper left corner of the current game map
    public int defaultSpeed, currentSpeed;


    //buffered images setup and HashMap<String, BufferedImage> "direction#" and associated sprite.
    //likely add buffered image to constructor
    //sprite counters setup (for walking animations)

    //collision setup -> define collision area,

    public Entity (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
