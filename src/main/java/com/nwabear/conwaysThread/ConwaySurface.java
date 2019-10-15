package com.nwabear.conwaysThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ConwaySurface extends JPanel {
    private ConwayCell[][] cells = new ConwayCell[AppContext.ROWS][AppContext.COLS];
    private BufferedImage bi = new BufferedImage(AppContext.ROWS, AppContext.COLS, BufferedImage.TYPE_3BYTE_BGR);
    private Graphics2D g = (Graphics2D) bi.getGraphics();
    private ArrayList<Thread> rows = new ArrayList<>();
    private Latch latch = new Latch(this);
    private boolean curIterator = false;
    private long startTime;

    public ConwaySurface() {
        // initialize board and threads
        for(int x = 0; x < this.cells.length; x++) {
            for(int y = 0; y < this.cells[0].length; y++) {
                this.cells[x][y] = new ConwayCell();
            }
            RowScrubber rs = new RowScrubber(this.cells[x], this.latch, this.g, x);
            Thread t = new Thread(rs);
            t.start();

            this.rows.add(t);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) (graphics);

        g2d.drawImage(this.bi.getScaledInstance(AppContext.WIDTH, AppContext.HEIGHT, Image.SCALE_AREA_AVERAGING), 0, 0, null);
        System.out.println("Time of execution: " + ((System.nanoTime() - this.startTime) / 1000000));
        this.tick();
    }

    public void randomize() {
        // randomize board
        for(ConwayCell[] row : this.cells) {
            for(ConwayCell cell : row) {
                cell.setAlive(new Random().nextBoolean());
            }
        }
    }

    public void tick() {
        this.startTime = System.nanoTime();

        // change the current generation value
        ConwayCell.setCurIterator(this.curIterator ? 0 : 1);
        ConwayCell.setGrid(this.cells);
        this.curIterator = !this.curIterator;

        // start the threads
        synchronized (RowScrubber.obj) {
            RowScrubber.obj.notifyAll();
        }
    }

    public void tickPaint() {
        this.repaint();
    }
}
