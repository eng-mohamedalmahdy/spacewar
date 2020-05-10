package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy3 extends Enemy {

    public Enemy3(GameScreen container) {
        super(50, 37, "alienship3", container);
        laserId = 8;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
