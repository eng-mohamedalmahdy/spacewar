
package spacewar.gameobjects.bosses;

import spacewar.ui.GameScreen;


public class Boss1 extends Boss{
    
    public Boss1(GameScreen container, int life) {
        super(100, 184, "boss1", container, life);
        laserId = 12;
    }
    
}
