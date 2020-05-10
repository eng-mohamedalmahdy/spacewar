package spacewar.gameobjects.bosses;

import spacewar.gameobjects.players.enemies.Enemy;
import spacewar.ui.GameScreen;

public abstract class Boss extends Enemy {

    private int life;

    public Boss(int w, int h, String name, GameScreen container, int life) {
        super(w, h, name, container);
        this.life = life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

}
