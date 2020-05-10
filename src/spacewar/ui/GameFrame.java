package spacewar.ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    public static final int w = 700;
    public static final int h = 600;

    private JPanel container;
    private StartScreen startScreen;
    private GameScreen gameScreen;

    public GameFrame() {
        setVisible(true);
        setSize(700, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

    }

    private void initComponents() {
        container = new JPanel(new CardLayout());
        container.setBackground(null);
        setContentPane(container);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(this, container, w, h, 2);
        container.add(gameScreen, "gameScreen");

        startScreen = new StartScreen(container, w, h, gameScreen);
        container.add(startScreen, "startScreen");

       CardLayout layout=(CardLayout) container.getLayout();
       layout.show(container, "startScreen");
    }

}
