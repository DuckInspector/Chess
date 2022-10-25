package chess;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {
    public JPanel buttonPanel;
    public BoardFrame(Board b) {

        setPreferredSize(new Dimension(800, 828));
        setResizable(false);
        //create board background panel
        Background background = new Background();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 8));
        buttonPanel.setPreferredSize(new Dimension(800, 800));
        buttonPanel.setOpaque(false);


        /* --------- creates buttons ----------- */
        for (int rows = 8; rows >= 1; rows--) {
            for (int columns = 1; columns <= 8; columns++) {
                JButton button = new JButton();
                if (b.board[rows][columns] == null) {
                    button.setName("" + rows + columns);
                    button.setFocusPainted(false);
                    button.setBorderPainted(false);
                    button.setOpaque(false);
                    buttonPanel.add(button);
                } else {
                    button.setIcon(b.board[rows][columns].icon);
                    button.setName("" + rows + columns);
                    button.setContentAreaFilled(false);
                    button.setFocusPainted(false);
                    button.setBorderPainted(false);
                    button.setOpaque(false);
                }
                button.addActionListener(actionEvent -> {
                    JButton click = (JButton) actionEvent.getSource();
                    GameLogic.Move(click);
                });
                button.setVisible(true);
                buttonPanel.add(button);
            }
        }
        /* --------------------------------------- */
        //for (Component bu : buttonPanel.getComponents()) System.out.println(bu.getName() + " ");
        setContentPane(background);
        add(buttonPanel);
        background.setVisible(true);
        buttonPanel.setVisible(true);

        //setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
