package com.nwabear.conwaysThread;

public class GameClock implements Runnable {
    private ConwaySurface surface;
    private boolean running = false;

    public GameClock(ConwaySurface surface) {
        this.surface = surface;
    }

    @Override
    public void run() {
        // run clock while the program exists
        while(true) {
            if(this.running) {
                this.surface.tick();
            }

            try {
                Thread.sleep(1000 / AppContext.FPS);
            } catch(Exception e) {
                // do nothing
            }
        }
    }

    public void toggleRunning() {
        this.running = !this.running;
    }

    public void tick() {
        this.surface.tick();
    }
}
