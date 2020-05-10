package spacewar.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import spacewar.gameobjects.players.Player;

public class ImageRadioButton extends JPanel {

    private JRadioButton button;
    private Image img;

    public ImageRadioButton(String imgName) {
        button = new JRadioButton();
        button.setLocation(24, 3);
        button.setPreferredSize(new Dimension(70, 15));
        button.setSize(new Dimension(70, 15));
        setLayout(null);
        add(button);
        setPreferredSize(new Dimension(70, 75));
        setSize(new Dimension(70, 75));

        try {
            img = ImageIO.read(new File("assets/spaceships/" + imgName + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        button.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 6, 20, null);

    }

    public JRadioButton getButton() {
        return button;
    }

}
