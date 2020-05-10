package spacewar.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import spacewar.gameobjects.bosses.Boss;
import spacewar.gameobjects.bosses.BossFactory;
import spacewar.gameobjects.players.Player;
import spacewar.gameobjects.players.PlayerFactory;
import spacewar.gameobjects.players.enemies.Enemy;
import spacewar.gameobjects.players.enemies.EnemyFactory;
import spacewar.gameobjects.players.lasers.Laser;
import spacewar.util.MoveAction;

public class GameScreen extends JPanel {

    private JPanel container;
    private Player player;
    private JFrame window;
    private Image objectImage;
    private ArrayList<Laser> playerLaser;
    private ArrayList<Enemy> enemies;
    private ArrayList<Laser> enemiesLaser;
    private boolean gameRunning = false;
    private AudioInputStream ais;
    private int level = 1;
    private int score = 0;
    private Clip clip;
    private JLabel scoreLabel;
    private JLabel levelLabel;

    public GameScreen(JFrame window, JPanel container, int w, int h, int playerNumber) {
        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);

        this.container = container;
        this.player = PlayerFactory.getPlayer(playerNumber);
        this.window = window;
        try {
            ais = AudioSystem.getAudioInputStream(new File("assets/sounds/" + "explosion.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            // open audioInputStream to the clip 
            clip.open(ais);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerLaser = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesLaser = new ArrayList<>();
        initComponents();
        initListeners();
        start();
    }

    public ArrayList<Laser> getEnemiesLaser() {
        return enemiesLaser;
    }

    private void initComponents() {
        setBackground(null);

        try {
            objectImage = ImageIO.read(new File("assets/" + "background" + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

        scoreLabel = new JLabel();
        scoreLabel.setPreferredSize(new Dimension(200, 30));
        scoreLabel.setSize(200, 30);
        scoreLabel.setLocation(0, 0);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        add(scoreLabel);

        levelLabel = new JLabel();
        levelLabel.setPreferredSize(new Dimension(200, 30));
        levelLabel.setSize(200, 30);
        levelLabel.setLocation(570, 0);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        add(levelLabel);
    }

    private void initListeners() {

        InputMap im = container.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = container.getActionMap();
        im.put(KeyStroke.getKeyStroke("W"), MoveAction.Action.MOVE_UP);
        im.put(KeyStroke.getKeyStroke("S"), MoveAction.Action.MOVE_DOWN);
        im.put(KeyStroke.getKeyStroke("A"), MoveAction.Action.MOVE_LEFT);
        im.put(KeyStroke.getKeyStroke("D"), MoveAction.Action.MOVE_RIGHT);
        im.put(KeyStroke.getKeyStroke("SPACE"), MoveAction.Action.FIRE);
        am.put(MoveAction.Action.MOVE_UP, new MoveAction(window, MoveAction.Action.MOVE_UP, player, this));
        am.put(MoveAction.Action.MOVE_DOWN, new MoveAction(window, MoveAction.Action.MOVE_DOWN, player, this));
        am.put(MoveAction.Action.MOVE_LEFT, new MoveAction(window, MoveAction.Action.MOVE_LEFT, player, this));
        am.put(MoveAction.Action.MOVE_RIGHT, new MoveAction(window, MoveAction.Action.MOVE_RIGHT, player, this));
        am.put(MoveAction.Action.FIRE, new MoveAction(window, MoveAction.Action.FIRE, player, this));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(objectImage, 0, 0, null);
    }

    private void start() {
        generateEnemies();
        checkDestroy();
    }

    private void generateEnemies() {

        new Thread(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (gameRunning) {
                EnemyFactory.generateEnemies(GameScreen.this);
                try {
                    Thread.sleep(10000 / level);
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(EnemyFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public ArrayList<Laser> getPlayerLaser() {
        return playerLaser;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private void checkDestroy() {
        new Thread(() -> {
            while (true) {

                try {
                    Iterator<Laser> laserIterator = playerLaser.iterator();
                    OUTER:
                    while (laserIterator.hasNext()) {
                        Laser laser = laserIterator.next();
                        Iterator<Enemy> enemiesIterator = enemies.iterator();
                        while (enemiesIterator.hasNext()) {
                            Enemy enemy = enemiesIterator.next();
                            if (laser.getObjectRectangle().intersects(enemy.getObjectRectangle())) {
                                if (enemy instanceof Boss) {
                                    laserIterator.remove();
                                    remove(laser);
                                    Boss boss = (Boss) enemy;
                                    int life = boss.getLife();
                                    if (life > 0) {
                                        life--;
                                        boss.setLife(life);
                                    } else {
                                        destroyEnemy(laser, enemy);
                                    }
                                } else {
                                    destroyEnemy(laser, enemy);
                                    gameRunning = true;
                                    generateEnemies();
                                }
                                break OUTER;
                            }
                        }
                    }
                    Iterator<Laser> enemiesLaserIterator = enemiesLaser.iterator();
                    while (enemiesLaserIterator.hasNext()) {
                        Laser laser = enemiesLaserIterator.next();
                        if (player != null && laser.getObjectRectangle().intersects(player.getObjectRectangle())) {
                            destroyPlayer(laser, player);
                            player = null;
                            break;
                        }
                    }
                    Thread.sleep(15);
                    repaint();
                } catch (InterruptedException | ConcurrentModificationException ex) {
                    Logger.getLogger(EnemyFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void destroyEnemy(Laser killer, Enemy killed) {
        new Thread(() -> {
            enemies.remove(killed);
            clip.start();
            for (int i = 1; i < 21; i++) {
                try {
                    killed.setObjectImage(ImageIO.read(new File("assets/explotion/" + i + ".png")));
                    killed.repaint();
                    Thread.sleep(10);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            clip.setMicrosecondPosition(0);
            killed.kill();
            remove(killed);
            remove(killer);
            playerLaser.remove(killer);
        }).start();
        score += 50;
        int currentLevel = level;
        int temp = score / 500 + 1;
        if (temp > currentLevel) {
            gameRunning = false;
            score = temp;
            createBoss();
        }

        scoreLabel.setText("Score : " + score);
        levelLabel.setText("Level : " + level);
        repaint();
    }

    private void destroyPlayer(Laser killer, Player player) {
        Thread t = new Thread(() -> {
            for (int i = 1; i < 21; i++) {
                try {
                    clip.start();
                    player.setObjectImage(ImageIO.read(new File("assets/explotion/" + i + ".png")));
                    player.repaint();
                    Thread.sleep(10);

                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            remove(player);
            remove(killer);
            enemiesLaser.remove(killer);
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip.setMicrosecondPosition(0);
        gameRunning = false;
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy enemy = it.next();
            enemy.kill();

        }
        playerLaser = new ArrayList();
        enemiesLaser = new ArrayList();
        enemies = new ArrayList();
        removeAll();
        repaint();
        CardLayout layout = (CardLayout) container.getLayout();
        JOptionPane.showMessageDialog(container, "Game over");
        level = 1;
        score = 0;
        layout.show(container, "startScreen");

    }

    public void intializePlaying(int playerNumber) {
        gameRunning = true;
        player = PlayerFactory.getPlayer(playerNumber);
        player.setLocation(325, 500);
        add(player);
        initListeners();
        initComponents();
        generateEnemies();
    }

    private void createBoss() {

        BossFactory.generateBosses(this, level * 2);
    }
}
