package chess;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

     public MyFrame() {
         //for (int i = 0; i < 64; i++);
            //c.add(new JButton(Action ));
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

         Icon icon = new ImageIcon("images.bking.png");
         JButton button = new JButton(icon);
         button.setVisible(true);
         add(button);

    }

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
