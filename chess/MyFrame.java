package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyFrame extends JFrame {

    public MyFrame() {
        setSize(1000, 1000);
        JPanel panel = new JPanel();

        /*create new chess piece icon*/
        JButton button = new JButton();
        BufferedImage image = null;
        try {
            Image img = ImageIO.read(new File("resources/images/bking.png"));
            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        add(button);
        button.setVisible(true);


        panel.add(button);
        panel.setVisible(true);
        add(panel);
        pack();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        //paint board

         for (int i = 1; i <= 7; i += 2)
            for (int j = 1; j <= 8; j++){
                g2D.setPaint(Color.pink);
                if (j % 2 != 0) g2D.fillRect(i * 100, j * 100, 100, 100);
                else {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(i * 100, j * 100, 100, 100);
                }
            }
        for (int i = 2; i <= 8 ; i += 2)
            for (int j = 1; j <= 8; j++){
                g2D.setPaint(Color.pink);
                if (j % 2 == 0) g2D.fillRect(i * 100, j * 100, 100, 100);
                else {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(i * 100, j * 100, 100, 100);
                }
            }


    }
}
