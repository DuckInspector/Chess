package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pawn extends Piece {
    public Pawn(Position p, char c, Board b) {
        super(p, c, b);
        hasMoved = false;

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wpawn.png"));
            else img = ImageIO.read(new File("resources/images/bpawn.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void refreshLegalMoves(){
        legalMoves.clear();
        Position p1, p2, p3, p4;

        if (color == 'w') {
            p1 = new Position(position.row + 1, position.column);
            p2 = new Position(position.row + 2, position.column);
            p3 = new Position(position.row + 1, position.column + 1);
            p4 = new Position(position.row + 1, position.column - 1);
            if (p1.isLegal() && !board.isOccupied(p1)){
                legalMoves.add(p1);
                if (!hasMoved) legalMoves.add(p2);
            }
            //eating behaviour
            if (p3.isLegal() && board.isOccupied(p3))
                if (board.whichPiece(p3).color != color)
                    legalMoves.add(p3);
            if (p4.isLegal() && board.isOccupied(p4))
                if (board.whichPiece(p4).color != color)
                    legalMoves.add(p4);
        }
        else {
            p1 = new Position(position.row - 1, position.column);
            p2 = new Position(position.row - 2, position.column);
            p3 = new Position(position.row - 1, position.column + 1);
            p4 = new Position(position.row - 1, position.column - 1);
            if (p1.isLegal() && !board.isOccupied(p1)) {
                legalMoves.add(p1);
                if (!hasMoved) legalMoves.add(p2);
            }
            //eating behaviour
            if (p3.isLegal() && board.isOccupied(p3))
                if (board.whichPiece(p3).color != color)
                    legalMoves.add(p3);
            if (p4.isLegal() && board.isOccupied(p4))
                if (board.whichPiece(p4).color != color)
                    legalMoves.add(p4);
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "P ";
        else return "p ";
    }
}
