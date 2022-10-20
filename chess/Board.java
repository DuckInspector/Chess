package chess;

import chess.pieces.*;

import javax.swing.*;

public class Board {
    //instances
    public Piece[][] board;

    //constructor
    public Board(Piece[][] board) {
        this.board = board;
    }

    //methods
    public boolean isOccupied (Position p){
        if (board[p.row][p.column] == null) return false;
        else return true;
    }

}

