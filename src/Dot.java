

public class Dot {
    int col;
    int row;

    public Dot(int col, int row) {
        this.col = col;
        this.row = row;
    }


    public void moveDot(int dX, int dY) {
        this.col = this.col + dX;
        this.row = this.row + dY;
    }

}
