package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame gameWindow = new JFrame("ShooTron");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);

        GraphicsPanel graphicsPanel = new GraphicsPanel();
        gameWindow.add(graphicsPanel);

        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        System.out.println("nejlepsi hra fr");
    }
}