package chess;

public class Position {
    public int row;
    public int column;

    public Position(int row, int c) {
        this.row = row;
        column = c;
    }

    public boolean isLegal(){
        if (row > 0 && row < 9 && column > 0 && column < 9) return true;
        else return false;
    }

    //getters and setters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        Position p = (Position) o;
        if (row == p.row && column == p.column) return true;
        else return false;
    }

    @Override
    public String toString() {
        String s = (row + ", " + column);
        return s;
    }
}
