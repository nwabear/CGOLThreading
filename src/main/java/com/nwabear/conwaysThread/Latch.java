package com.nwabear.conwaysThread;

public class Latch {
    private int curCount = 0;
    private ConwaySurface cs;

    public Latch(ConwaySurface cs) {
        this.cs = cs;
    }

    public synchronized void addOne() {
        // add one to the completed threads count
        this.curCount++;

        // if every row is completed, reset the counter and draw the board
        if(this.curCount == AppContext.ROWS) {
            this.cs.tickPaint();
            this.curCount = 0;
        }
    }
}
