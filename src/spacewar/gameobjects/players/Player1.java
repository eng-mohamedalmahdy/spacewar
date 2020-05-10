package spacewar.gameobjects.players;

public final class Player1 extends Player {

    public Player1() {
        super(56, 55, "player1");
        this.playerId = 1;
        this.laserId = 5;
        scaleFactor.x = 10;
        scaleFactor.y = 27;
    }

}
