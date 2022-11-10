import java.util.ArrayList;


enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Snake {
    int snakeLength;
    Dot[] body;

    public Snake(int cols, int rows) {
        this.snakeLength = 5;
        this.body = new Dot[snakeLength];

        for (int i=0; i<snakeLength; i++) {
            body[i] = new Dot(i+2, 3);
        }


        /*this.body.add(new Dot(2, 3));
        this.body.add(new Dot(3, 3));
        this.body.add(new Dot(4, 3));
        this.body.add(new Dot(4, 4));
        this.body.add(new Dot(5, 4));*/
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
