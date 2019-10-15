package com.nwabear.conwaysThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConwayFrame extends JFrame {
    private ConwaySurface surface;
    private GameClock gc;

    public ConwayFrame() {
        this.initUI();
    }

    public void initUI() {
        this.surface = new ConwaySurface();

        this.add(this.surface);

        this.gc = new GameClock(this.surface);
        Thread clock = new Thread(this.gc);
        clock.start();

        this.setTitle("Conways Game of Life");

        int width = AppContext.WIDTH;
        int height = AppContext.HEIGHT;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case 32:
                        ConwayFrame.this.gc.toggleRunning();
                        System.out.println("Toggle Running");
                        break;

                    case 82:
                        ConwayFrame.this.surface.randomize();
                        System.out.println("Randomize");
                        break;

                    case 78:
                        ConwayFrame.this.gc.tick();
                        System.out.println("Force Tick");
                        break;

                    default:
                        System.out.println(e.getKeyCode());
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        ConwayFrame cf = new ConwayFrame();
        EventQueue.invokeLater( () -> {
            cf.setVisible(true);
        });
    }
}
