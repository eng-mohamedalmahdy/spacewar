package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy2 extends Enemy {

    public Enemy2(GameScreen container) {
        super(50, 48, "alienship2", container);
        laserId = 7;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
