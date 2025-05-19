package dev.sgp.gui;

import javax.swing.*;

public class Window {
    //Creates the program window and loads the gamePanel

    public static void open() {
        JFrame window = new JFrame();
        window.setTitle("Space Garden");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setVisible(true);

        //potentially implement a Start Screen loading here
        gamePanel.runGameThread();
    }
}
