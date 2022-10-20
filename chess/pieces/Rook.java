package chess.pieces;

import chess.Board;
import chess.Position;

public class Rook extends Piece {
    public Rook(Position p, char c, Board b) {
        super(p, c, b);
    }
    public void refreshLegalMoves() {
        legalMoves.clear();
        //checks for available rows and columns
        Position p;
        for (int i = 1; i < position.row; i++) {
            p = new Position(position.row - i, position.column);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 9 - position.row; i++) {
            p = new Position(position.row + i, position.column);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < position.column; i++) {
            p = new Position(position.row, position.column - i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 9 - position.column; i++) {
            p = new Position(position.row, position.column + i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "R ";
        else return "r ";
    }
}