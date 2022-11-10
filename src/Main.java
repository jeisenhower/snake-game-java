import java.time.Duration;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SnakeFrame frame = new SnakeFrame(600, 600, 25, 70);
        Thread.sleep(Duration.ofSeconds(3));
        frame.play();
    }
}