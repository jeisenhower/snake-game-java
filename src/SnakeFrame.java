import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeFrame extends JFrame {
    int frameRate;
    SnakeGamePanel gamePanel;
    Timer timer;
    Timer repaintTimer;

    SnakeFrame(int width, int height, int dotSize, int frameRate) {
        super();

        gamePanel = new SnakeGamePanel(width, height, dotSize);

        super.setSize(width, height);
        super.setTitle("Snake");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(gamePanel);
        super.pack();
        super.setLayout(null);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
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

        this.frameRate = frameRate;
    }

    public void play() {

        gamePanel.running = true;

        // Initialize the snake to be moving down
        gamePanel.snakeDirection = Direction.DOWN;

        timer = new Timer(frameRate, gamePanel);
        timer.start();

        /*timer.scheduleAtFixedRate(new TimerTask() {
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
       }, 0, 5);*/

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
