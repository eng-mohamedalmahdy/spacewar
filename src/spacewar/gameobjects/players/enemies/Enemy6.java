package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy6 extends Enemy {

    public Enemy6(GameScreen container) {
        super(50, 43, "alienship6", container);
        laserId = 11;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
