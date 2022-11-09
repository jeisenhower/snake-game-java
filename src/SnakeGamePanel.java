import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGamePanel extends JPanel {
    int dotSize;
    Snake snake;
    int cols;
    int rows;
    int originX;
    int originY;

    Direction snakeDirection;

    Dot fruit;


    public SnakeGamePanel(int cols, int rows, int dotSize) {
        this.snakeDirection = Direction.DOWN;
        this.dotSize = dotSize;
        this.originX = 0;
        this.originY = 0;
        this.cols = cols;
        this.rows = rows;



        this.fruit = generateFruit(this.cols, this.rows);

        setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        //setPreferredSize(new Dimension(cols*dotSize + dotSize, rows*dotSize + dotSize));
        //setSize(new Dimension(cols*dotSize + dotSize, rows*dotSize + dotSize));
        setPreferredSize(new Dimension(cols*dotSize, rows*dotSize));
        setSize(new Dimension(cols*dotSize, rows*dotSize));

        snake = new Snake(this.cols, this.rows);
    }

    public Dot generateFruit(int cols, int rows) {

        Random r = new Random();
        int low = 2;
        int maxColVal = cols - 2;
        int maxRowVal = rows -2;


        int col = r.nextInt(maxColVal-low) + low;

        int row = r.nextInt(maxRowVal-low) + low;

        //return (int) ((Math.random() * (max - min)) + min);

        return new Dot(col, row);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //drawGrid(g2);
        drawSnake(g2);
        //drawBoardEdges(g2);
        drawFruit(g2);
    }

    protected void drawGrid(Graphics2D g2) {
        g2.setColor(Color.gray);

        for (int row=0; row<rows+1; row++) {
            g2.drawLine(originX, originY + row*dotSize, originX + cols*dotSize, originY + row*dotSize);
        }

        for (int col=0; col<cols+1; col++) {
            g2.drawLine(originX + col*dotSize, originY, originX + col*dotSize, originY + rows*dotSize);
        }
    }

    protected void drawBoardEdges(Graphics2D g2) {
        g2.setColor(Color.gray);

        // Horizontal bounding lines
        //g2.drawLine(originX, originY, originX + cols*dotSize, originY);
        g2.drawLine(originX, originY + rows*dotSize, originX + cols*dotSize, originY + rows*dotSize);

        // Vertical bounding lines
        //g2.drawLine(originX, originY, originX, originY + rows*dotSize);
        g2.drawLine(originX + cols*dotSize, originY, originX + cols*dotSize, originY + rows*dotSize);
    }

    protected void drawSnake(Graphics2D g2) {
        if (snake.body.isEmpty()) {
            return;
        }

        g2.setColor(Color.GREEN);
        Dot head = snake.body.get(0);
        g2.fillRect(originX + head.col*dotSize, originY + head.row*dotSize, dotSize, dotSize);

        g2.setColor(Color.BLUE);
        for (int i=1; i<snake.body.size(); i++) {
            Dot dot = snake.body.get(i);
            g2.fillRect(originX + dot.col*dotSize, originY + dot.row*dotSize, dotSize, dotSize);
        }
    }

    protected void drawFruit(Graphics2D g2) {
        if (fruit == null) {
            return;
        }

        g2.setColor(Color.RED);
        g2.fillRect(originX + fruit.col*dotSize, originY + fruit.row*dotSize, dotSize, dotSize);
    }


    public void updateSnake(Dot newHead) {
        ArrayList<Dot> newSnakeBody = new ArrayList<>();
        // Add the new head to the new snake
        newSnakeBody.add(newHead);

        // Check if the snake is out of bounds
        /*if (newHead.col > cols || newHead.row > rows || newHead.col < 0 || newHead.row < 0) {
            // We know the snake is out of bounds, so simply move the snake in the opposite direction of which it was going,
            // which will cause the snake to eat itself and end the game.
            newHead.col = snake.body.get(0).col;
            newHead.row = snake.body.get(0).row;
        }*/

        // Check if the new head is on the dot that is the prize. If it is, append the new head to the start of the snake
        if (newHead.col == fruit.col && newHead.row == fruit.row) {
            // Add to the snake, so append the new head to the old snake
            newSnakeBody.addAll(snake.body);
            // Redraw the fruit somewhere else on the board
            fruit = generateFruit(cols, rows);
        } else {
            for (int i=0; i<snake.body.size()-1; i++) {
                newSnakeBody.add(snake.body.get(i));
            }
        }


        snake.body = newSnakeBody;

    }


    public void moveSnakeLeft() {
        if (snake.body.isEmpty()) {
            return;
        }

        Dot newHead = new Dot(snake.body.get(0).col-1, snake.body.get(0).row);
        updateSnake(newHead);


    }

    public void moveSnakeRight() {
        if (snake.body.isEmpty()) {
            return;
        }

        Dot newHead = new Dot(snake.body.get(0).col+1, snake.body.get(0).row);
        updateSnake(newHead);
    }

    public void moveSnakeDown() {
        if (snake.body.isEmpty()) {
            return;
        }

        Dot newHead = new Dot(snake.body.get(0).col, snake.body.get(0).row+1);
        updateSnake(newHead);

    }

    public void moveSnakeUp() {
        if (snake.body.isEmpty()) {
            return;
        }

        Dot newHead = new Dot(snake.body.get(0).col, snake.body.get(0).row-1);
        updateSnake(newHead);
    }


    // Checks if the head of the snake is intersecting any dots that make up its body.


}
