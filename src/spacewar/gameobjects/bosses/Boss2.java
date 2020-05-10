package spacewar.gameobjects.bosses;

import spacewar.ui.GameScreen;

public class Boss2 extends Boss {

    public Boss2(GameScreen container, int life) {
        super(100, 92, "boss2", container, life);
        laserId = 13;
    }

}
