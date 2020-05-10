package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class Enemy1 extends Enemy {

    public Enemy1(GameScreen container) {
        super(50, 55, "alienship1", container);
        laserId = 6;
        getCorrectionFactor().x = 10;
        getCorrectionFactor().y = 10;
    }

}
