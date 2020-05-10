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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import spacewar.gameobjects.players.Player;
import spacewar.util.ImageRadioButton;

public class StartScreen extends JPanel {

    JButton start;
    JPanel container;
    Image background;
    JLabel choosePlayer;
    GameScreen gameScreen;
    ButtonGroup playerSelection;
    ImageRadioButton player1RadioButton;
    ImageRadioButton player2RadioButton;
    ImageRadioButton player3RadioButton;
    ImageRadioButton player4RadioButton;
    private int playerId = 1;

    public StartScreen(JPanel container, int w, int h, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);
        this.container = container;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        try {
            background = ImageIO.read(new File("assets/" + "background" + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));

        choosePlayer = new JLabel("Choose player : ");
        choosePlayer.setSize(200, 75);
        choosePlayer.setPreferredSize(new Dimension(200, 75));
        choosePlayer.setLocation(50, 150);
        choosePlayer.setForeground(Color.WHITE);
        choosePlayer.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        add(choosePlayer);

        playerSelection = new ButtonGroup();
        player1RadioButton = new ImageRadioButton("player1");
        player1RadioButton.setLocation(260, 150);
        playerSelection.add(player1RadioButton.getButton());
        player1RadioButton.getButton().setSelected(true);
        player1RadioButton.getButton().addActionListener((al) -> {
            playerId = 1;
        });
        add(player1RadioButton);

        player2RadioButton = new ImageRadioButton("player2");
        player2RadioButton.setLocation(360, 150);
        playerSelection.add(player2RadioButton.getButton());
        player2RadioButton.getButton().addActionListener((al) -> {
            playerId = 2;
        });
        add(player2RadioButton);

        player3RadioButton = new ImageRadioButton("player3");
        player3RadioButton.setLocation(460, 150);
        playerSelection.add(player3RadioButton.getButton());
        player3RadioButton.getButton().addActionListener((al) -> {
            playerId = 3;
        });
        add(player3RadioButton);

        player4RadioButton = new ImageRadioButton("player4");
        player4RadioButton.setLocation(560, 150);
        playerSelection.add(player4RadioButton.getButton());
        player4RadioButton.getButton().addActionListener((al) -> {
            playerId = 4;
        });
        add(player4RadioButton);

        start = new JButton("Start");
        start.setSize(120, 50);
        start.setPreferredSize(new Dimension(120, 50));
        start.setOpaque(false);
        start.setBorder(null);
        start.setLocation(295, 280);
        start.setFocusPainted(false);
        start.setForeground(Color.WHITE);
        start.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        start.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        add(start);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
    }

    private void initListeners() {
        start.addActionListener(al -> {
            CardLayout layout = (CardLayout) container.getLayout();
            gameScreen.intializePlaying(playerId);
            System.out.println(playerId);
            layout.show(container, "gameScreen");

        });
    }
}
