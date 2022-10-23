package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Rook extends Piece {
    public Rook(Position p, char c, Board b) {
        super(p, c, b);
        hasMoved = false;

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wrook.png"));
            else img = ImageIO.read(new File("resources/images/brook.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void refreshLegalMoves() {
        legalMoves.clear();
        //checks for available rows and columns
        Position p1;
        for (int i = 1; i < position.row; i++) {
            p1 = new Position(position.row - i, position.column);
            if(!board.isOccupied(p1)) {
                legalMoves.add(p1);
            }
            else if (board.whichPiece(p1).color != color) legalMoves.add(p1);
            else break;
        }
        for (int i = 1; i < 9 - position.row; i++) {
            p1 = new Position(position.row + i, position.column);
            if(!board.isOccupied(p1)) {
                legalMoves.add(p1);
            }
            else if (board.whichPiece(p1).color != color) legalMoves.add(p1);
            else break;
        }
        for (int i = 1; i < position.column; i++) {
            p1 = new Position(position.row, position.column - i);
            if(!board.isOccupied(p1)) {
                legalMoves.add(p1);
            }
            else if (board.whichPiece(p1).color != color) legalMoves.add(p1);
            else break;
        }
        for (int i = 1; i < 9 - position.column; i++) {
            p1 = new Position(position.row, position.column + i);
            if(!board.isOccupied(p1)) {
                legalMoves.add(p1);
            }
            else if (board.whichPiece(p1).color != color) legalMoves.add(p1);
            else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "R ";
        else return "r ";
    }
}