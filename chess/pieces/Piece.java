package chess.pieces;

import chess.Board;
import chess.Position;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    //instance variables
    public Position position;
    public char color;
    public List<Position> legalMoves = new ArrayList<>();
    public Board board;
    public Icon icon;
    public boolean hasMoved;

    //constructor
    public Piece(Position p, char c, Board b) {
        position = p;
        color = c;
        board = b;
    }

    //methods
    public void refreshLegalMoves(){}
    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass();
    }
}






