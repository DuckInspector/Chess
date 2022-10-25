package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class King extends Piece {
    public King(Position p, char c, Board b) {
        super(p, c, b);
        hasMoved = false;

        try {
            Image img;
            if (c == 'w') img = ImageIO.read(new File("resources/images/wking.png"));
            else img = ImageIO.read(new File("resources/images/bking.png"));

            img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

        } catch (Exception e) {
            System.out.println("Can't open icon image!");
        }
    }

    public void refreshLegalMoves() {
        legalMoves.clear();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Position p = new Position(position.row + i, position.column + j);
                if (i == 0 && j == 0) break;
                if (p.isLegal())
                    if (!isAttacked(p))
                        if (!board.isOccupied(p)) legalMoves.add(p);
                        else if (board.whichPiece(p).color != color) legalMoves.add(p); //eating behaviour

            }
        }

        //checks for castling rights
        if (canCastle(1))
            if (color == 'w') legalMoves.add(new Position(1, 3)); //long
            else              legalMoves.add(new Position(8, 3));
        if (canCastle(0))
            if (color == 'w') legalMoves.add(new Position(1, 7)); //short
            else              legalMoves.add(new Position(8, 7));
    }

    private boolean canCastle(int longOrShort) {
        Position p1, p2, p3;
        boolean notUnderAttack, notOccupied;

        if (longOrShort == 1) { //long castle
            if (!hasMoved && !board.whichPiece(new Position(1, 1)).hasMoved){ //long castle
                if (color == 'w'){
                    p1 = new Position(1, 2);
                    p2 = new Position(1, 3);
                    p3 = new Position(1, 4);
                } else {
                    p1 = new Position(8, 2);
                    p2 = new Position(8, 3);
                    p3 = new Position(8, 4);
                }
                notUnderAttack = !isAttacked(p1) && !isAttacked(p2) && !isAttacked(p3);
                notOccupied = !board.isOccupied(p1) && !board.isOccupied(p2) && !board.isOccupied(p3);
                return (notOccupied && notUnderAttack);
            }

        } else {  //short castle
            if (!hasMoved && !board.whichPiece(new Position(1, 8)).hasMoved){ //long castle
                if (color == 'w') {
                    p1 = new Position(1, 7);
                    p2 = new Position(1, 6);
                } else {
                    p1 = new Position(8, 7);
                    p2 = new Position(8, 6);
                }
                notUnderAttack = !isAttacked(p1) && !isAttacked(p2);
                notOccupied = !board.isOccupied(p1) && !board.isOccupied(p2);
                return (notOccupied && notUnderAttack);
            }
        }
        return false;
    }

    private boolean isAttacked(Position p) {
        Piece attacker;
        Position p1;

        for (int i = 1; i <= 8; i++)
            for (int j =1; j <= 8; j++) {
                p1 = new Position(i, j);
                attacker = board.whichPiece(p1);
                if (attacker == null) {}
                else if (attacker.color != color && !(attacker.getClass() == this.getClass())) { //skips friendly pieces and king itself
                    attacker.refreshLegalMoves();
                    for (Position pos : attacker.legalMoves) {
                        if (pos.equals(p)) {
                            return true;
                        }
                    }
                }
            }
        return false;
    }

    @Override
    public String toString() {
        if (color == 'w') return "KI ";
        else return "ki ";
    }
}