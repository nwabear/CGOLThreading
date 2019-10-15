package com.nwabear.conwaysThread;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RowScrubber implements Runnable {
    private ConwayCell[] row;
    private Graphics2D g2d;
    private BufferedImage bi;
    private Latch latch;
    private int startY;

    public final static Object obj = new Object();

    public RowScrubber(ConwayCell[] row, Latch latch, Graphics2D g2d, int startY) {
        this.row = row;
        this.g2d = g2d;
        this.startY = startY;
        this.latch = latch;
        this.bi = new BufferedImage(1, AppContext.ROWS, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public void run() {
        Graphics2D g = (Graphics2D)this.bi.getGraphics();
        while(true) {
            // wait until the next generation needs to be calculated
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // calculate the next state for each cell and draw that to the row
            for (int i = 0; i < AppContext.COLS; i++) {
                this.row[i].calcNextTick(this.startY, i);
                g.setColor(this.row[i].isAliveNormal() ? Color.WHITE : Color.BLACK);
                g.fillRect(0, i, 1, 1);
            }

            // draw the row to the grid image
            this.g2d.drawImage(this.bi, this.startY, 0, null);

            this.latch.addOne();
        }
    }
}
