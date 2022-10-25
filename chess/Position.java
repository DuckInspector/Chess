package chess;

public class Position {
    public int row;
    public int column;

    public Position(int row, int c) {
        this.row = row;
        column = c;
    }

    public boolean isLegal(){
        return row > 0 && row < 9 && column > 0 && column < 9;
    }

    @Override
    public boolean equals(Object o) {
        Position p = (Position) o;
        return row == p.row && column == p.column;
    }

    @Override
    public String toString() {

        return ((char) (column + 96) + "" + row);
    }
}
