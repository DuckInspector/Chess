package chess.pieces;

import chess.Board;
import chess.Position;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Piece {
    //instance variables
    protected Position position;
    public char color;
    protected List<Position> legalMoves = new ArrayList<Position>();
    public Board board;

    Icon icon;

    //constructor
    public Piece(Position p, char c, Board b) {
        position = p;
        color = c;
        board = b;
    }

    //methods
    public void refreshLegalMoves(){};
    public boolean Move(Position p) {
        refreshLegalMoves();
        if(legalMoves.stream().anyMatch(p::equals)) {
            this.position = p;
            return true;
        }
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) return true;
        else return false;
    }
}






