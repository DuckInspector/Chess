package chess;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    public Background(){
        super();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        //paint board
        for (int i = 0; i <= 6; i += 2)
            for (int j = 0; j <= 7; j++){
                g2D.setPaint(Color.lightGray);
                if (j % 2 != 0) g2D.fillRect(i * 100, j * 100, 100, 100);
                else {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(i * 100, j * 100, 100, 100);
                }
            }
        for (int i = 1; i <= 7 ; i += 2)
            for (int j = 0; j <= 7; j++){
                g2D.setPaint(Color.lightGray);
                if (j % 2 == 0) g2D.fillRect(i * 100, j * 100, 100, 100);
                else {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(i * 100, j * 100, 100, 100);
                }
            }
    }
}
