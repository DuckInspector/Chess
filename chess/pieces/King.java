package chess.pieces;

import chess.Board;
import chess.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            System.out.println(e);
        }
    }

    public void refreshLegalMoves() {
        legalMoves.clear();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Position p = new Position(position.row + i, position.column + j);
                if (i == 0 && j == 0) break;
                if (p.isLegal())
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

    @Override
    public String toString() {
        if (color == 'w') return "KI ";
        else return "ki ";
    }

    public boolean canCastle(int longOrShort) {
        Position p1;
        Piece attacker;
        boolean underAttack = false;
        if (color == 'w') {
            if (longOrShort == 1) {
                if (!hasMoved && !board.whichPiece(new Position(1, 1)).hasMoved) //long castle
                    for (int i = 0; i <= 8 && !underAttack; i++) //check if castling squares are under attack
                        for (int j = 0; j <= 8 && !underAttack; j++) {
                            p1 = new Position(i, j);
                            attacker = board.whichPiece(p1);
                            if (attacker == null) break;
                            if (attacker.color != color) {
                                attacker.refreshLegalMoves();
                                for (Position p2 : attacker.legalMoves)
                                    if (p2.equals(new Position(1, 2)) || p2.equals(new Position(1, 3)) || p2.equals(new Position(1, 4)))
                                        underAttack = true;
                            }
                        }
                if (!board.isOccupied(new Position(1, 2)) && !board.isOccupied(new Position(1, 3)) && !board.isOccupied(new Position(1, 4)) && !underAttack) {
                    return true;
                }
            } else {
                if (!hasMoved && !board.whichPiece(new Position(1, 8)).hasMoved) //short castle
                    for (int i = 0; i <= 8 && !underAttack; i++) //check if castling squares are under attack
                        for (int j = 0; j <= 8 && !underAttack; j++) {
                            p1 = new Position(i, j);
                            attacker = board.whichPiece(p1);
                            if (attacker == null) break;
                            if (attacker.color != color) {
                                attacker.refreshLegalMoves();
                                for (Position p2 : attacker.legalMoves)
                                    if (p2.equals(new Position(1, 7)) || p2.equals(new Position(1, 6)))
                                        underAttack = true;
                            }
                        }
                if (!board.isOccupied(new Position(1, 7)) && !board.isOccupied(new Position(1, 6)) && !underAttack) {
                    return true;
                }
            }
        } else {
            if (longOrShort == 1) {
                if (!hasMoved && !board.whichPiece(new Position(8, 1)).hasMoved) //long castle
                    for (int i = 0; i <= 8 && !underAttack; i++) //check if castling squares are under attack
                        for (int j = 0; j <= 8 && !underAttack; j++) {
                            p1 = new Position(i, j);
                            attacker = board.whichPiece(p1);
                            if (attacker == null) break;
                            if (attacker.color != color) {
                                attacker.refreshLegalMoves();
                                for (Position p2 : attacker.legalMoves)
                                    if (p2.equals(new Position(8, 2)) || p2.equals(new Position(8, 3)) || p2.equals(new Position(8, 4)))
                                        underAttack = true;
                            }
                        }
                if (!board.isOccupied(new Position(8, 2)) && !board.isOccupied(new Position(8, 3)) && !board.isOccupied(new Position(8, 4)) && !underAttack) {
                    return true;
                }
            } else {
                if (!hasMoved && !board.whichPiece(new Position(8, 8)).hasMoved) //short castle
                    for (int i = 0; i <= 8 && !underAttack; i++) //check if castling squares are under attack
                        for (int j = 0; j <= 8 && !underAttack; j++) {
                            p1 = new Position(i, j);
                            attacker = board.whichPiece(p1);
                            if (attacker == null) break;
                            if (attacker.color != color) {
                                attacker.refreshLegalMoves();
                                for (Position p2 : attacker.legalMoves)
                                    if (p2.equals(new Position(8, 7)) || p2.equals(new Position(8, 6)))
                                        underAttack = true;
                            }
                        }
                if (!board.isOccupied(new Position(8, 7)) && !board.isOccupied(new Position(8, 6)) && !underAttack) {
                    return true;
                }
            }
        }
        return false;
    }
}