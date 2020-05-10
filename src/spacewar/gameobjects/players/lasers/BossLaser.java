package spacewar.gameobjects.players.lasers;

import spacewar.ui.GameScreen;

public class BossLaser extends Laser {

    public BossLaser(GameScreen container, int laserNumber) {
        super(50, 50, "laser" + laserNumber, container);
    }

}
