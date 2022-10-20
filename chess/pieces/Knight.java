package chess.pieces;

import chess.Board;
import chess.Position;

public class Knight extends Piece {
    public Knight(Position p, char c, Board b) {
        super(p, c, b);
    }
    public void refreshLegalMoves() {
        legalMoves.clear();
        for (int i = -1; i <= 1; i += 2) {
            Position p = new Position(position.row + 2 * i, position.column + 1);
            if (p.isLegal())
                if (!board.isOccupied(p)) legalMoves.add(p);
            p = new Position(position.row + 2 * i, position.column - 1);
            if (p.isLegal())
                if (!board.isOccupied(p)) legalMoves.add(p);
        }
        for (int i = -1; i <= 1; i += 2) {
            Position p = new Position(position.row + 1, position.column + 2*i);
            if (p.isLegal())
                if (!board.isOccupied(p)) legalMoves.add(p);
            p = new Position(position.row - 1, position.column + 2*i);
            if (p.isLegal())
                if (!board.isOccupied(p)) legalMoves.add(p);
        }
        System.out.println(legalMoves.get(0).toString() + legalMoves.get(1).toString());
    }

    @Override
    public String toString() {
        if (color == 'w') return "KN ";
        else return "kn ";
    }
}