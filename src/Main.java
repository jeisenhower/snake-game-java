import java.time.Duration;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SnakeFrame frame = new SnakeFrame(20, 20, 40, 110);
        Thread.sleep(Duration.ofSeconds(3));
        frame.play();
    }
}