package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bishop extends Piece {
    public Bishop (Position p, char c, Board b) {
        super (p, c, b);

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wbishop.png"));
            else img = ImageIO.read(new File("resources/images/bbishop.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void refreshLegalMoves() {
        legalMoves.clear();
        //checks diagonals
        //first quadrant
        Position p;
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row + i, position.column + i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break; //diagonal occupied by friendly piece
        }
        //second
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row + i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p);
                else break;
        }
        //third
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p);
                else break;
        }
        //fourth
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column + i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p);
                else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "B ";
        else return "b ";
    }
}