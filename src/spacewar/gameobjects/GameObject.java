/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacewar.gameobjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import spacewar.gameobjects.players.Player;

/**
 *
 * @author GodOfHammers
 */
public class GameObject extends JPanel {

    private Image objectImage;
    protected int rotationAngle;

    public GameObject(int w, int h, String name) {
        setBackground(null);
        setup(w, h, name);
        rotationAngle = 0;
        setOpaque(false);
    }

    protected final void setup(int w, int h, String objectImageName) {
        setSize(w, h);
        try {
            objectImage = ImageIO.read(new File("assets/" + objectImageName + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPreferredSize(new Dimension(w, h));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotationAngle), getWidth() / 2, getHeight() / 2);
        g2d.drawImage(objectImage, 0, 0, null);
        g2d.setTransform(backup);

    }

    public Rectangle getObjectRectangle() {
        return new Rectangle(getLocation().x, getLocation().y, getWidth(), getHeight());
    }

    public Image getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(Image objectImage) {
        this.objectImage = objectImage;
    }

}
