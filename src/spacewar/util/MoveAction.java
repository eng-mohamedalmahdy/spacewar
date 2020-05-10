package spacewar.util;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import spacewar.gameobjects.players.Player;
import spacewar.gameobjects.players.lasers.Laser;
import spacewar.gameobjects.players.lasers.LaserFactory;
import spacewar.ui.GameScreen;

public class MoveAction extends AbstractAction {

    Window window;
    Action action;
    Player player;
    GameScreen gameScreen;
    private long lastShot;

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (action) {
            case MOVE_UP:
                if (player.getLocation().y > 0) {
                    player.setLocation(player.getLocation().x, player.getLocation().y - 5);
                }
                break;
            case MOVE_DOWN:
                if (player.getLocation().y + player.getHeight() < gameScreen.getHeight()) {
                    player.setLocation(player.getLocation().x, player.getLocation().y + 5);
                }
                break;
            case MOVE_LEFT:
                if (player.getLocation().x > 0) {
                    player.setLocation(player.getLocation().x - 5, player.getLocation().y);
                }
                break;
            case MOVE_RIGHT:
               if (player.getLocation().x + player.getWidth()< gameScreen.getWidth()) player.setLocation(player.getLocation().x + 5, player.getLocation().y);
                break;

            case FIRE:
                long time = System.currentTimeMillis();
                if (time - lastShot > 500) {
                    Laser laserBullet = LaserFactory.getLaser(player.getLaserId(), gameScreen);
                    Point p = new Point(player.getLocation().x + (player.getWidth() / 2) - player.getScaleFactor().x, player.getLocation().y - (player.getScaleFactor().y));
                    laserBullet.setLocation(p);
                    gameScreen.getPlayerLaser().add(laserBullet);
                    gameScreen.add(laserBullet);
                    lastShot = time;
                }
                break;
        }
    }

    public enum Action {
        MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, FIRE;
    }

    public MoveAction(Window window, Action action, Player player, GameScreen gameScreen) {
        this.window = window;
        this.action = action;
        this.player = player;
        this.gameScreen = gameScreen;
        lastShot = System.currentTimeMillis();
    }

}
