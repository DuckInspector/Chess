package chess;
import chess.pieces.*;
import javax.swing.*;
import java.util.Scanner;



abstract class GameLogic {
    /*public enum row {
        a, b, c, d, e, f, g;
    }*/
    static char turn;
    static Piece selectedPiece;
    static int moveRow, moveColumn, pRow, pColumn;
    static String s;
    static Scanner reader;

    public static void main(String[] args) {
        //set new board
        Piece[][] p = new Piece[9][9];
        Board b = new Board(p);
        setPieces(b);
        turn = 'w';

        //display board
        new MyFrame();
        /*while (true) {
            Display(b);
            MakeMove(turn, b);
            if (turn == 'w') turn = 'b';
            else turn = 'w';
        }*/

        //check if move is legal, eventually try again
        //update board
        //repeat
    }

    private static void setPieces(Board b) {
        b.board[1][1] = new Rook(new Position(1, 1), 'w', b);
        b.board[2][1] = new Knight(new Position(2, 1), 'w', b);
        b.board[3][1] = new Bishop(new Position(3, 1), 'w', b);
        b.board[4][1] = new Queen(new Position(4, 1), 'w', b);
        b.board[5][1] = new King(new Position(5, 1), 'w', b);
        b.board[6][1] = new Bishop(new Position(6, 1), 'w', b);
        b.board[7][1] = new Knight(new Position(7, 1), 'w', b);
        b.board[8][1] = new Rook(new Position(8, 1), 'w', b);

        b.board[1][8] = new Rook(new Position(1, 8), 'b', b);
        b.board[2][8] = new Knight(new Position(2, 8), 'b', b);
        b.board[3][8] = new Bishop(new Position(3, 8), 'b', b);
        b.board[4][8] = new Queen(new Position(4, 8), 'b', b);
        b.board[5][8] = new King(new Position(5, 8), 'b', b);
        b.board[6][8] = new Bishop(new Position(6, 8), 'b', b);
        b.board[7][8] = new Knight(new Position(7, 8), 'b', b);
        b.board[8][8] = new Rook(new Position(8, 8), 'b', b);

        for (int i = 1; i <= 8; i++) {
            b.board[i][2] = new Pawn(new Position(i, 2), 'w', b);
            b.board[i][7] = new Pawn(new Position(i, 7), 'b', b);
        }
    }

    private static void Display(Board b) {
        for (int columns = 8; columns >= 1; columns --) {
            for (int rows = 1; rows <= 8; rows++) {
                if (b.board[rows][columns] == null) System.out.printf("- ");
                else System.out.printf(b.board[rows][columns].toString());
            }
            System.out.println();
        }
    }

    private static void MakeMove(char turn, Board b) {
        boolean flag1 = false, flag2 = false, flag3 = false;
        while (true) {
            if (flag1) System.out.println("Wrong piece! Select your piece.");
            if (flag2) System.out.println("You didn't select a piece. Try again.");

            reader = new Scanner(System.in);
            System.out.println("Choose a piece: ");
            s = reader.next();
            pRow = s.charAt(0);
            pColumn = s.charAt(1);
            selectedPiece = b.board[pRow - 96][pColumn - 48];



            if (selectedPiece == null) {
                flag2 = true;
            }
            else{
                flag1 = true;
                flag2 = false;
                if (selectedPiece.color == turn) break;
            }

        }
        while (true) {
            if (flag3) System.out.println("Move's illegal, try another move.");

            System.out.println("Make a move: ");
            s = reader.next();
            moveRow = s.charAt(0);
            moveColumn = s.charAt(1);

            //check if move is legal, eventually try again
            flag3 = true;
            if (selectedPiece.Move(new Position(moveRow - 96, moveColumn - 48))) break;
        }

        b.board[moveRow - 96][moveColumn - 48] = selectedPiece;
        b.board[pRow - 96][pColumn - 48] = null;
    }


}
