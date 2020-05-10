package spacewar.gameobjects.players.lasers;

import spacewar.ui.GameScreen;

public class BoomerangLaser extends Laser {

    public BoomerangLaser(GameScreen container, int boomarangNumber) {
        super(25, 25, "laser" + boomarangNumber, container);
    }

}
