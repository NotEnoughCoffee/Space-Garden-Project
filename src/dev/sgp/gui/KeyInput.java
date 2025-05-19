package dev.sgp.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyInput implements KeyListener {
    public GamePanel gamePanel;

    //BUTTONS//
    public static boolean up, down, left, right, interact, escape, nullKey;
    final private Set<Integer> pressedKeys = new HashSet<>();

    //INITIALIZATION//
    public KeyInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //KEY EVENTS//
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        pressedKeys.forEach(keyPressed -> {
            switch (keyPressed) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> up = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = true;
                case KeyEvent.VK_E, KeyEvent.VK_SPACE -> interact = true;
                case KeyEvent.VK_ESCAPE -> escape = true;
                default -> nullKey = true;
            }
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyReleased = e.getKeyCode();
        pressedKeys.remove(keyReleased);
        switch (keyReleased) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = false;
            case KeyEvent.VK_E, KeyEvent.VK_SPACE -> interact = false;
            case KeyEvent.VK_ESCAPE -> escape = false;
            default -> nullKey = false;
        }
    }

    //UNUSED METHODS//
    @Override
    public void keyTyped(KeyEvent e) {} //unused
}
