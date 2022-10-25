package chess;

import chess.pieces.*;

public class Board {
    //instances
    public Piece[][] board;
    public static boolean kingInCheck;

    //constructor
    public Board(Piece[][] board) {
        this.board = board;
        kingInCheck = false;
    }

    //methods
    public boolean isOccupied (Position p){
        return board[p.row][p.column] != null;
    }
    public Piece whichPiece(Position p) {
        return board[p.row][p.column];
    }
}

