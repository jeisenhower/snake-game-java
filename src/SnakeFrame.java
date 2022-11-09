import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeFrame extends JFrame {
    long frameRate;
    long previousRenderedTime;
    SnakeGamePanel gamePanel;
    Timer timer;
    Timer repaintTimer;

    SnakeFrame(int cols, int rows, int dotSize, long frameRate) {
        super();
        super.setLayout(null);
        super.setSize((cols)*dotSize, (rows+1)*dotSize);
        super.setTitle("Snake");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        gamePanel = new SnakeGamePanel(cols, rows, dotSize);
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.snakeDirection = Direction.UP;
                    //gamePanel.snake.moveUp();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.snakeDirection = Direction.DOWN;
                    //gamePanel.snake.moveDown();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    gamePanel.snakeDirection = Direction.RIGHT;
                    //gamePanel.snake.moveRight();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    gamePanel.snakeDirection = Direction.LEFT;
                    //gamePanel.snake.moveLeft();
                }
            }
        });
        super.add(gamePanel);

        this.frameRate = frameRate;
        timer = new Timer();
        repaintTimer = new Timer();

    }

    public void play() {

        // Initialize the snake to be moving down
        gamePanel.snakeDirection = Direction.DOWN;


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Direction d = gamePanel.snakeDirection;
                if (d == Direction.UP) {
                    gamePanel.moveSnakeUp();
                } else if (d == Direction.DOWN) {
                    gamePanel.moveSnakeDown();
                } else if (d == Direction.LEFT) {
                    gamePanel.moveSnakeLeft();
                } else if (d == Direction.RIGHT) {
                    gamePanel.moveSnakeRight();
                }


                //repaint();

                // If snake is on itself, end the game and break out of the loop
                if (gamePanel.snake.isOnSelf()) {
                    System.out.println("Snake ate itself! Game over");
                    this.cancel();
                }

                if (gamePanel.snake.isOutOfBounds()) {
                    System.out.println("Snake is out of bounds! Game over");
                    this.cancel();
                }


            }
        }, 80, frameRate);


       repaintTimer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               repaint();
           }
       }, 0, frameRate);

        //for (int i=0; i<20; i++) {
        /*while (true) {

            long currentTime = System.currentTimeMillis();

            // Redraw every specified number of milliseconds
            if (currentTime - previousRenderedTime >= frameRate) {
                // Set the previous rendered time to the current time, so we can repeat the process
                previousRenderedTime = currentTime;
                Direction d = gamePanel.snake.direction;
                if (d == Direction.UP) {
                    gamePanel.snake.moveUp();
                } else if (d == Direction.DOWN) {
                    gamePanel.snake.moveDown();
                } else if (d == Direction.LEFT) {
                    gamePanel.snake.moveLeft();
                } else if (d == Direction.RIGHT) {
                    gamePanel.snake.moveRight();
                }


                repaint();

                // If snake is on itself, end the game and break out of the loop
                if (gamePanel.snake.isOnSelf()) {
                    System.out.println("Snake ate itself! Game over");
                    break;
                }
            }

        }*/
    }
}
