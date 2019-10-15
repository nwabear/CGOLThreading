package com.nwabear.conwaysThread;

public class Latch {
    private int curCount = 0;
    private ConwaySurface cs;

    public Latch(ConwaySurface cs) {
        this.cs = cs;
    }

    public synchronized void addOne() {
        this.curCount++;

        if(this.curCount == AppContext.ROWS) {
            this.cs.tickPaint();
            this.curCount = 0;
        }
    }
}
