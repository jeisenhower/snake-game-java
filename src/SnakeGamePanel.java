import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakeGamePanel extends JPanel implements ActionListener {
    int dotSize;
    Snake snake;
    int cols;
    int rows;
    int height;
    int width;
    int originX;
    int originY;
    int score;

    boolean running = false;

    Direction snakeDirection;

    Dot fruit;

    // TODO: Add a JPanel with transparent background that displays the current score at the top of the window.

    public SnakeGamePanel(int width, int height, int dotSize) {
        score = 0;
        this.width = width;
        this.height = height;
        this.snakeDirection = Direction.DOWN;
        this.dotSize = dotSize;
        this.originX = 0;
        this.originY = 0;
        this.cols = width/dotSize;
        this.rows = height/dotSize;



        this.fruit = generateFruit();

        //setPreferredSize(new Dimension(cols*dotSize + dotSize, rows*dotSize + dotSize));
        //setSize(new Dimension(cols*dotSize + dotSize, rows*dotSize + dotSize));
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        snake = new Snake(this.cols, this.rows);
    }

    public Dot generateFruit() {

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
        super.paintComponent(g);
        //drawGrid(g);
        drawSnake(g);
        drawFruit(g);
    }

    protected void drawGrid(Graphics g) {
        g.setColor(Color.gray);

        for (int i=0; i<height/dotSize; i++) {
            g.drawLine(i*dotSize, originY, i*dotSize, height);
            g.drawLine(originX, i*dotSize, width, i*dotSize);
        }
    }

    protected void drawSnake(Graphics g) {
        if (snake.body.length == 0) {
            return;
        }

        g.setColor(Color.GREEN);
        Dot head = snake.body[0];
        g.fillRect(originX + head.col*dotSize, originY + head.row*dotSize, dotSize, dotSize);

        g.setColor(Color.BLUE);
        for (int i=1; i<snake.body.length; i++) {
            Dot dot = snake.body[i];
            g.fillRect(originX + dot.col*dotSize, originY + dot.row*dotSize, dotSize, dotSize);
        }
    }

    protected void drawFruit(Graphics g) {
        if (fruit == null) {
            return;
        }

        g.setColor(Color.RED);
        g.fillOval(originX + fruit.col*dotSize, originY + fruit.row*dotSize, dotSize, dotSize);
    }


    public void moveSnake() {
        if (snakeDirection == Direction.UP) {
            moveSnakeUp();
        } else if (snakeDirection == Direction.DOWN) {
            moveSnakeDown();
        } else if (snakeDirection == Direction.LEFT) {
            moveSnakeLeft();
        } else if (snakeDirection == Direction.RIGHT) {
            moveSnakeRight();
        }

        // If snake is on itself, end the game and break out of the loop
        if (isOnSelf()) {
            System.out.println("Snake ate itself! Game over");
            running = false;
        }

        if (isOutOfBounds()) {
            System.out.println("Snake is out of bounds! Game over");
            running = false;
        }
    }


    public void updateSnake(Dot newHead) {
        // Check if the new head is on the dot that is the prize. If it is, append the new head to the start of the snake
        if (isOnFruit()) {
            // Add to the snake, so append the new head to the old array
            Dot[] newBody = new Dot[snake.snakeLength+1];
            newBody[0] = newHead;
            for (int i=0; i<snake.snakeLength; i++) {
                newBody[i+1] = snake.body[i];
            }
            snake.body = newBody;
            snake.snakeLength++;
            fruit = generateFruit();
        } else {
            // The snake should keep moving and not add any length to its body
            for (int i=snake.snakeLength-1; i>0; i--) {
                snake.body[i] = snake.body[i-1];
            }
            snake.body[0] = newHead;
        }
    }


    public void moveSnakeLeft() {
        if (snake.body.length == 0) {
            return;
        }

        Dot newHead = new Dot(snake.body[0].col-1, snake.body[0].row);
        updateSnake(newHead);


    }

    public void moveSnakeRight() {
        if (snake.body.length == 0) {
            return;
        }

        Dot newHead = new Dot(snake.body[0].col+1, snake.body[0].row);
        updateSnake(newHead);
    }

    public void moveSnakeDown() {
        if (snake.body.length == 0) {
            return;
        }

        Dot newHead = new Dot(snake.body[0].col, snake.body[0].row+1);
        updateSnake(newHead);

    }

    public void moveSnakeUp() {
        if (snake.body.length == 0) {
            return;
        }

        Dot newHead = new Dot(snake.body[0].col, snake.body[0].row-1);
        updateSnake(newHead);
    }

    // Checks if the body is intersecting itself
    public boolean isOnSelf() {
        Dot head = snake.body[0];
        for (int i=1; i<snake.body.length; i++) {
            Dot d = snake.body[i];
            if (head.col == d.col && head.row == d.row) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds() {
        Dot head = snake.body[0];
        if (head.col > cols-1 || head.row > rows-1 || head.col < 0 || head.row < 0) {
            return true;
        }
        return false;
    }

    public boolean isOnFruit() {
        if (snake.body[0].col == fruit.col && snake.body[0].row == fruit.row) {
            score++;
            System.out.println("You ate the fruit! Current Score: " + score);
            return true;
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moveSnake();
            if (isOnSelf() || isOutOfBounds()) {
                running = false;
            }
        }
        repaint();
    }

}
