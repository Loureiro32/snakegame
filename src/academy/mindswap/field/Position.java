package academy.mindswap.field;

public class Position {

    private final int col;
    private final int row;


    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }


    @Override
    public boolean equals(Object position) {
        if (position instanceof Position) {
            return ((Position) position).getCol() == this.col && ((Position) position).getRow() == this.row;
        }

        return false;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
