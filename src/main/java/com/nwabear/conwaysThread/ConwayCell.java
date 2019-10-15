package com.nwabear.conwaysThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConwayCell {
    private boolean[] state = {false, false};
    private Color[] onOff = {Color.WHITE, Color.BLACK};

    private static ConwayCell[][] grid;
    private static int curIterator;

    public void calcNextTick(int x, int y) {
        int neighborAlive = 0;

        try { if(grid[x - 1][y - 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x - 1][y].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x - 1][y + 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x][y - 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x][y + 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x + 1][y - 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x + 1][y].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }
        try { if(grid[x + 1][y + 1].isAlive()) { neighborAlive++; } } catch(Exception e) { /* do nothing */ }

        switch(neighborAlive) {
            case 2:
                this.state[curIterator] = this.isAlive();
                break;

            case 3:
                this.state[curIterator] = true;
                break;

            default:
                this.state[curIterator] = false;
                break;
        }
    }

    public void setAlive(boolean alive) {
        this.state[0] = alive;
        this.state[1] = alive;
    }

    public boolean isAlive() {
        return (curIterator == 0) ? this.state[1] : this.state[0];
    }

    public boolean isAliveNormal() {
        return this.state[curIterator];
    }

    public static void setCurIterator(int ci) {
        curIterator = ci;
    }

    public static void setGrid(ConwayCell[][] g) {
        grid = g;
    }
}
