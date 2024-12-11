package academy.mindswap.field;

public class Position {

    private final int col;
    private final int row;


    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
