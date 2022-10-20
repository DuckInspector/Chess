package chess.pieces;

import chess.Board;
import chess.Position;

public class Queen extends Piece {
    public Queen(Position p, char c, Board b){
        super(p, c, b);
    }
    public void refreshLegalMoves()
    {
        legalMoves.clear();
        //checks diagonals
        for (int i = 1; i < position.row; i++) {
            Position p = new Position(position.row - i, position.column - i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 8 - position.row; i++) {
            Position p = new Position(position.row + i, position.column + i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < position.column; i++) {
            Position p = new Position(position.row - i, position.column - i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 8 - position.column; i++) {
            Position p = new Position(position.row + i, position.column + i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        //checks for available rows and columns
        for (int i = 1; i < position.row; i++) {
            Position p = new Position(position.row - i, position.column);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 8 - position.row; i++) {
            Position p = new Position(position.row + i, position.column);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < position.column; i++) {
            Position p = new Position(position.row, position.column - i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
        for (int i = 1; i < 8 - position.column; i++) {
            Position p = new Position(position.row, position.column + i);
            if(!board.isOccupied(p)) {
                legalMoves.add(p);
            }
            else break;
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "Q ";
        else return "q ";
    }

}
