package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy5 extends Enemy {

    public Enemy5(GameScreen container) {
        super(50, 47, "alienship5", container);
         laserId = 10;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
