package spacewar.gameobjects.players.lasers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import spacewar.gameobjects.GameObject;
import spacewar.ui.GameScreen;

public class Laser extends GameObject {

    private boolean direction = false;
    private final Laser me;
    private final GameScreen container;
    protected int laserId;
    private AudioInputStream ais;
    private Clip clip;

    public Laser(int w, int h, String ImageName, GameScreen container) {
        super(w, h, "laser/" + ImageName);
        me = this;
        this.container = container;
        try {
            ais = AudioSystem.getAudioInputStream(new File("assets/sounds/" + "laserShot.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            // open audioInputStream to the clip 
            clip.open(ais);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Laser.class.getName()).log(Level.SEVERE, null, ex);
        }
        startMoving();

    }

    private void startMoving() {
        new Thread(() -> {
            while (true) {
                if (me.getLocation().y >= 0 && me.getLocation().y <= container.getHeight()) {
                    if (!direction) {
                        me.setLocation(getLocation().x, getLocation().y - 5);
                    } else {
                        me.setLocation(getLocation().x, getLocation().y + 5);
                    }
                } else {
                    try {
                        container.getEnemiesLaser().remove(me);
                        container.getPlayerLaser().remove(me);
                        container.remove(me);
                        container.repaint();
                        me.finalize();
                        break;
                    } catch (Throwable ex) {
                        Logger.getLogger(Laser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Laser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

}
