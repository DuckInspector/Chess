package chess.pieces;

import chess.Board;
import chess.Position;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop (Position p, char c, Board b) {
        super (p, c, b);
        if (c == 'w') icon = new ImageIcon("images.wbishop");
        else icon = new ImageIcon("images.bbishop");
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
            else break;
        }
        //second
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column + i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
            else break;
        }
        //third
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row - i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);

                else break;
        }
        //fourth
        for (int i = 1; i < 8; i++) {
            p = new Position(position.row + i, position.column - i);
            if (p.isLegal())
                if(!board.isOccupied(p))
                    legalMoves.add(p);
            else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "B ";
        else return "b ";
    }
}