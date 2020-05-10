package spacewar.gameobjects.players;

import java.awt.Point;
import spacewar.gameobjects.GameObject;

public abstract class Player extends GameObject {

    public static int playerId;
    public static int laserId;
    protected Point scaleFactor = new Point();

    public Player(int w, int h, String name) {
        super(w, h, "spaceships/" + name);
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getLaserId() {
        return laserId;
    }

    public Point getScaleFactor() {
        return scaleFactor;
    }

}
