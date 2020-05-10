/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacewar.gameobjects.bosses;

import spacewar.ui.GameScreen;

/**
 *
 * @author GodOfHammers
 */
public class Boss4 extends Boss {

    public Boss4(GameScreen container, int life) {
        super(100, 78, "boss4", container, life);
        laserId = 15;
    }

}
