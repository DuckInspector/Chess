package chess.pieces;

import chess.Board;
import chess.Position;

public class Pawn extends Piece {
    boolean hasMoved = false;
    public Pawn(Position p, char c, Board b) {super(p, c, b);}
    public void refreshLegalMoves(){
        legalMoves.clear();
        if (color == 'w') {
            if (!hasMoved) legalMoves.add(new Position(position.row, position.column + 2));
            Position p = new Position(position.row, position.column + 1);
            if (p.isLegal() && !board.isOccupied(p)) legalMoves.add(p);
        }
        else {
            if (!hasMoved) legalMoves.add(new Position(position.row, position.column - 2));
            Position p = new Position(position.row, position.column - 1);
            if (p.isLegal() && !board.isOccupied(p)) legalMoves.add(p);
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "P ";
        else return "p ";
    }
}
