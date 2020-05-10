package spacewar.gameobjects.players.lasers;

import spacewar.ui.GameScreen;

public class LaserFactory {

    public static Laser getLaser(int laserNumber, GameScreen container) {
        switch (laserNumber) {
            case 1:
                return new RedLaser(container);

            case 2:
                return new BlueLaser(container);

            case 3:
                return new GreenLaser(container);

            case 4:
                return new YellowLaser(container);

            case 5:
                return new PinkLaser(container);

            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return new BoomerangLaser(container, laserNumber);

            case 12:
            case 13:
            case 14:
            case 15:
                return new BossLaser(container, laserNumber);
            default:
                return null;
        }
    }
}
