package assignments.assignment4.gui;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private Image image;

    public Background(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
