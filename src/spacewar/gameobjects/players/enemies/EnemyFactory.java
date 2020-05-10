package spacewar.gameobjects.players.enemies;

import spacewar.ui.GameScreen;

public class EnemyFactory {

    public static Enemy getEnemy(int EnemyId, GameScreen container) {

        switch (EnemyId) {
            case 1:
                return new Enemy1(container);

            case 2:
                return new Enemy2(container);
            case 3:
                return new Enemy3(container);
            case 4:
                return new Enemy4(container);
            case 5:
                return new Enemy5(container);
            case 6:
                return new Enemy6(container);

            default:
                return null;
        }
    }

    public static void generateEnemies(GameScreen container) {

        int randEnemy = (int) ((Math.random() * 10) % 6) + 1;
        Enemy enemy = EnemyFactory.getEnemy(randEnemy, container);
        int randLocation = (int) ((Math.random() * 1000) % container.getWidth()) + 1;
        enemy.setLocation(randLocation, 0);
        container.add(enemy);
        container.getEnemies().add(enemy);
    }
}
