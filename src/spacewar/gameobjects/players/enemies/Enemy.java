package spacewar.gameobjects.players.enemies;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacewar.gameobjects.GameObject;
import spacewar.gameobjects.players.lasers.Laser;
import spacewar.gameobjects.players.lasers.LaserFactory;
import spacewar.ui.GameFrame;
import spacewar.ui.GameScreen;

public abstract class Enemy extends GameObject {

    protected int laserId = 0;
    private GameScreen container;
    private Thread shottingThread;
    private boolean alive = true;
    private final Point correctionFactor;

    public Enemy(int w, int h, String name, GameScreen container) {
        super(w, h, "alienships/" + name);
        rotationAngle = 180;
        this.container = container;
        correctionFactor = new Point();
        startMoving();
        shootLaser();
    }

    private void startMoving() {
        new Thread(() -> {
            while (true) {
                int ranx = (int) (Math.random() * 10 % 4) - 2;
                int rany = (int) (Math.random() * 10 % 2) + 1;

                if (getLocation().x <= GameFrame.w) {
                    setLocation(getLocation().x + (ranx) * 2, getLocation().y);
                }
                if (getLocation().x > GameFrame.w) {
                    setLocation(getLocation().x + (ranx) * -2, getLocation().y);
                }
                if (getLocation().x < 0) {
                    setLocation(getLocation().x + (ranx) * -2, getLocation().y);
                }
                if (getLocation().y <= GameFrame.h) {
                    setLocation(getLocation().x, getLocation().y + (rany));
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();
    }

    private void shootLaser() {
        shottingThread = new Thread(() -> {
            try {
                Thread.sleep((int) (Math.random() * 10000 % 3000) + 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (alive) {
                Laser laser = LaserFactory.getLaser(laserId, container);
                container.getEnemiesLaser().add(laser);
                laser.setLocation(getLocation().x + correctionFactor.x, getLocation().y + correctionFactor.y);
                laser.setDirection(true);
                container.add(laser);
                try {
                    Thread.sleep((int) (Math.random() * 10000 % 3000) + 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        shottingThread.start();
    }

    public Thread getShottingThread() {
        return shottingThread;
    }

    public void kill() {
        alive = false;
    }

    public Point getCorrectionFactor() {
        return correctionFactor;
    }

}
