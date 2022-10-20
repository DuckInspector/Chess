package chess.pieces;

import chess.Board;
import chess.Position;

import javax.swing.*;

public class King extends Piece {
    public King(Position p, char c, Board b) {
        super(p, c, b);
        if (c == 'w') icon = new ImageIcon("images.wking");
        else icon = new ImageIcon("images.bking");
    }

    public void refreshLegalMoves() {
        legalMoves.clear();
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                Position p = new Position(position.row + i, position.column + j);
                if (i == 0 && j == 0) break;
                else if (p.isLegal() && !board.isOccupied(p))
                    legalMoves.add(p);
            }
        }
    }

    @Override
    public String toString() {
        if (color == 'w') return "KI ";
        else return "ki ";
    }
}