import java.util.ArrayList;


enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Snake {
    int snakeLength;
    ArrayList<Dot> body;

    //Direction direction;

    int cols;
    int rows;


    public Snake(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.snakeLength = 5;
        this.body = new ArrayList<Dot>();

        this.body.add(new Dot(2, 3));
        this.body.add(new Dot(3, 3));
        this.body.add(new Dot(4, 3));
        this.body.add(new Dot(4, 4));
        this.body.add(new Dot(5, 4));
    }


    public boolean isOnSelf() {
        Dot head = this.body.get(0);
        for (int i=1; i<this.body.size(); i++) {
            Dot d = this.body.get(i);
            if (head.col == d.col && head.row == d.row) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds() {
        Dot head = body.get(0);
        if (head.col > cols || head.row > rows || head.col < 0 || head.row < 0) {
            return true;
        }
        return false;
    }


    // Check if a given dot is on the snake
    boolean isOnSnake(Dot dotToCheck) {
        // Loop through the snake to see if the dot is on the snake
        for (Dot dot : this.body) {
            if (dotToCheck.col == dot.col && dotToCheck.row == dot.row) {
                return true;
            }
        }
        return false;
    }


    // Check if a given dot is on the snake
    boolean isOnSnake(int col, int row) {
        // Loop through the snake to see if the dot is on the snake
        for (Dot dot : this.body) {
            if (col == dot.col && row == dot.row) {
                return true;
            }
        }
        return false;
    }


}
