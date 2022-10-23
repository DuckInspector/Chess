package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Knight extends Piece {
    public Knight(Position p, char c, Board b) {
        super(p, c, b);

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wknight.png"));
            else img = ImageIO.read(new File("resources/images/bknight.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void refreshLegalMoves() {
        legalMoves.clear();
        Position p1;

        for (int i = -1; i <= 1; i += 2) {
            p1 = new Position(position.row + (2 * i), position.column + 1);
            if (p1.isLegal()) {
                if (board.isOccupied(p1)) {
                    if (board.whichPiece(p1).color != color) legalMoves.add(p1);
                }
                else legalMoves.add(p1);
            }
            p1 = new Position(position.row + (2 * i), position.column - 1);
            if (p1.isLegal()) {
                if (board.isOccupied(p1)) {
                    if (board.whichPiece(p1).color != color) legalMoves.add(p1);
                }
                else legalMoves.add(p1);
            }
        }
        for (int i = -1; i <= 1; i += 2) {
            p1 = new Position(position.row + 1, position.column + 2*i);
            if (p1.isLegal()) {
                if (board.isOccupied(p1)) {
                    if (board.whichPiece(p1).color != color) legalMoves.add(p1);
                }
                else legalMoves.add(p1);
            }
            p1 = new Position(position.row - 1, position.column + 2*i);
            if (p1.isLegal()) {
                if (board.isOccupied(p1)) {
                    if (board.whichPiece(p1).color != color) legalMoves.add(p1);
                }
                else legalMoves.add(p1);
            }
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "KN ";
        else return "kn ";
    }
}