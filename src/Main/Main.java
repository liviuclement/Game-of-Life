package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid();
        JFrame frame = new JFrame();
        frame.getContentPane().add(grid);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
       // System.out.println(grid.readFromFile());
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.newGeneration(grid);
                grid.repaint();
            }
        }).start();
    }
}
