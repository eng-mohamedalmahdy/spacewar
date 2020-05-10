package spacewar.gameobjects.bosses;

import spacewar.gameobjects.players.enemies.Enemy;
import spacewar.gameobjects.players.enemies.EnemyFactory;
import spacewar.ui.GameScreen;

public class BossFactory {

    public static Boss getBoss(int bossId, GameScreen container, int life) {
        switch (bossId) {
            case 1:
                return new Boss1(container, life);

            case 2:
                return new Boss2(container, life);
            case 3:
                return new Boss3(container, life);
            case 4:
                return new Boss4(container, life);

            default:
                return null;
        }
    }

    public static void generateBosses(GameScreen container, int life) {

        int randEnemy = (int) ((Math.random() * 10) % 4) + 1;
        Boss enemy = BossFactory.getBoss(randEnemy, container, life);
        int randLocation = (int) ((Math.random() * 1000) % container.getWidth()) + 1;
        enemy.setLocation(randLocation, 0);
        container.add(enemy);
        container.getEnemies().add(enemy);
    }
}
