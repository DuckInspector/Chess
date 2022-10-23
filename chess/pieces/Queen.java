package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Queen extends Piece {
    public Queen(Position p, char c, Board b){
        super(p, c, b);

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wqueen.png"));
            else img = ImageIO.read(new File("resources/images/bqueen.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void refreshLegalMoves()
    {
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
                else break;
        }
        //second
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column + i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
        //third
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
        //fourth
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row + i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }

        //checks for available rows and columns
        for (int i = 1; i < position.row; i++) {
            p = new Position(position.row - i, position.column);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
        for (int i = 1; i < 9 - position.row; i++) {
            p = new Position(position.row + i, position.column);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
        for (int i = 1; i < position.column; i++) {
            p = new Position(position.row, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
        for (int i = 1; i < 9 - position.column; i++) {
            p = new Position(position.row, position.column + i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
                else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour
                else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "Q ";
        else return "q ";
    }

}
