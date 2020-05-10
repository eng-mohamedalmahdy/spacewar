package spacewar.gameobjects.players;

public class PlayerFactory {

    public static Player getPlayer(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return new Player1();

            case 2:
                return new Player2();
            case 3:
                return new Player3();
            case 4:
                return new Player4();
            default:
                return null;
        }
    }
}
