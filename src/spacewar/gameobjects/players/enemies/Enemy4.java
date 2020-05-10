package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy4 extends Enemy {

    public Enemy4(GameScreen container) {
        super(50, 48, "alienship4", container);
        laserId = 9;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
