package chess;
import chess.pieces.*;
import javax.swing.*;
import java.awt.*;



abstract class GameLogic {
    static char turn;

    private static boolean flag;
    private static BoardFrame f;
    private static Board b;
    private static Position p1, p2;
    private static Piece selectedPiece;

    public static void main(String[] args) {
        Piece[][] p = new Piece[9][9];
        b = new Board(p);
        setPieces(b);
        turn = 'w';
        flag = false;
        
        //create frame
        f = new BoardFrame(b); //BoardFrame creates frame, panel and buttons. Has "JButton click" instance that detects clicks
        f.setVisible(true);
    }

    private static void setPieces(Board b) {
        b.board[1][1] = new Rook(new Position(1, 1), 'w', b);
        b.board[1][2] = new Knight(new Position(1, 2), 'w', b);
        b.board[1][3] = new Bishop(new Position(1, 3), 'w', b);
        b.board[1][4] = new Queen(new Position(1, 4), 'w', b);
        b.board[1][5] = new King(new Position(1, 5), 'w', b);
        b.board[1][6] = new Bishop(new Position(1, 6), 'w', b);
        b.board[1][7] = new Knight(new Position(1, 7), 'w', b);
        b.board[1][8] = new Rook(new Position(1, 8), 'w', b);

        b.board[8][1] = new Rook(new Position(8, 1), 'b', b);
        b.board[8][2] = new Knight(new Position(8, 2), 'b', b);
        b.board[8][3] = new Bishop(new Position(8, 3), 'b', b);
        b.board[8][4] = new Queen(new Position(8, 4), 'b', b);
        b.board[8][5] = new King(new Position(8, 5), 'b', b);
        b.board[8][6] = new Bishop(new Position(8, 6), 'b', b);
        b.board[8][7] = new Knight(new Position(8, 7), 'b', b);
        b.board[8][8] = new Rook(new Position(8, 8), 'b', b);

        for (int i = 1; i <= 8; i++) {
            b.board[2][i] = new Pawn(new Position(2, i), 'w', b);
            b.board[7][i] = new Pawn(new Position(7, i), 'b', b);
        }
    }
    public static int Move(JButton click) {
        if (!flag) {
            //get position of click and piece @ position
            String click1 = click.getName();
            p1 = new Position(click1.charAt(0) - 48, click1.charAt(1) - 48);
            selectedPiece = b.board[p1.row][p1.column];

            if (selectedPiece != null)    //selected a piece
                if (selectedPiece.color == turn) {  //selected right color
                    flag = true;
                    selectedPiece.refreshLegalMoves();
                    System.out.println(selectedPiece + "Position: " + p1);
                    for (Position p : selectedPiece.legalMoves) System.out.println(p + " ");
                    System.out.println("---------");
                }
            return 0;
        }

        //when valid piece has been selected, select a destination square
        String click2 = click.getName();
        p2 = new Position(click2.charAt(0) - 48, click2.charAt(1) - 48);

        if (b.isOccupied(p2)) { //case: occupied square
            Piece secondClickedPiece = b.whichPiece(p2);
            if (secondClickedPiece.color == turn) { //case: second piece is friendly piece
                p1 = p2;
                selectedPiece = secondClickedPiece;
                selectedPiece.refreshLegalMoves();
                System.out.println(selectedPiece + "Position: " + p1);
                for (Position p : selectedPiece.legalMoves) System.out.println(p + " ");
                System.out.println("---------");
                return 0;
            } else if ((selectedPiece.legalMoves).contains(p2)) {    //case: second piece is enemy piece (eating behaviour)
                //updates board
                b.board[p2.row][p2.column] = selectedPiece;
                b.board[p1.row][p1.column] = null;

                //update piece!!!!! FUCK! Ive lost a good hour on this
                selectedPiece.position = p2;
                if (selectedPiece instanceof Pawn || selectedPiece instanceof King || selectedPiece instanceof  Rook)  selectedPiece.hasMoved = true;

                //update buttons
                Component[] c = f.buttonPanel.getComponents();
                JButton b1 = (JButton) c[(8 - p1.row) * 8 + p1.column - 1];
                b1.setIcon(null);
                JButton b2 = (JButton) c[(8 - p2.row) * 8 + p2.column - 1];
                b2.setIcon(selectedPiece.icon);

                //change turns
                if (GameLogic.turn == 'w') GameLogic.turn = 'b';
                else GameLogic.turn = 'w';

                flag = false;
                return 1;

            }
        } else if ((selectedPiece.legalMoves).contains(p2)) {    //case: second piece is empty square
            //updates board
            b.board[p2.row][p2.column] = selectedPiece;
            b.board[p1.row][p1.column] = null;
            //CASTLING
            {
                if (selectedPiece instanceof King && (p2.column - p1.column) < -1) { //king is long castling
                    if (selectedPiece.color == 'w') {
                        b.board[1][4] = b.board[1][1];
                        b.board[1][1] = null;
                        b.board[1][4].position = new Position(1, 4);

                        Component[] c = f.buttonPanel.getComponents();
                        JButton b1 = (JButton) c[(8 - 1) * 8 + 1 - 1];
                        b1.setIcon(null);
                        JButton b2 = (JButton) c[(8 - 1) * 8 + 4 - 1];
                        b2.setIcon(b.board[1][4].icon);

                    } else {
                        b.board[8][4] = b.board[8][1];
                        b.board[8][1] = null;
                        b.board[8][4].position = new Position(8, 4);

                        Component[] c = f.buttonPanel.getComponents();
                        JButton b1 = (JButton) c[(8 - 8) * 8 + 1 - 1];
                        b1.setIcon(null);
                        JButton b2 = (JButton) c[(8 - 8) * 8 + 4 - 1];
                        b2.setIcon(b.board[8][4].icon);
                    }
                } else if (selectedPiece instanceof King && (p2.column - p1.column) > 1) { //king is short castling
                    if (selectedPiece.color == 'w') {
                        b.board[1][6] = b.board[1][8];
                        b.board[1][8] = null;
                        b.board[1][6].position = new Position(1, 6);

                        Component[] c = f.buttonPanel.getComponents();
                        JButton b1 = (JButton) c[(8 - 1) * 8 + 8 - 1];
                        b1.setIcon(null);
                        JButton b2 = (JButton) c[(8 - 1) * 8 + 6 - 1];
                        b2.setIcon(b.board[1][6].icon);

                    } else {
                        b.board[8][6] = b.board[8][8];
                        b.board[8][8] = null;
                        b.board[8][6].position = new Position(8, 6);

                        Component[] c = f.buttonPanel.getComponents();
                        JButton b1 = (JButton) c[(8 - 8) * 8 + 8 - 1];
                        b1.setIcon(null);
                        JButton b2 = (JButton) c[(8 - 8) * 8 + 6 - 1];
                        b2.setIcon(b.board[8][6].icon);
                    }
                }
            }
            //update piece position
            selectedPiece.position = p2;

            //change turns
            if (GameLogic.turn == 'w') GameLogic.turn = 'b';
            else GameLogic.turn = 'w';


            //update buttons
            Component[] c = f.buttonPanel.getComponents();

            JButton b1 = (JButton) c[(8 - p1.row) * 8 + p1.column - 1];
            b1.setIcon(null);
            JButton b2 = (JButton) c[(8 - p2.row) * 8 + p2.column - 1];
            b2.setIcon(selectedPiece.icon);

            flag = false;
            if (selectedPiece instanceof Pawn || selectedPiece instanceof King || selectedPiece instanceof  Rook)  selectedPiece.hasMoved = true;
            return 1;
        }
        return -1;
    }
}
