import java.io.PrintWriter;
import java.util.List;

public class TaskWriter implements Runnable {

    private final String numbers;
    private final PrintWriter writer;

    public TaskWriter (String numbers, PrintWriter writer) {
        this.numbers = numbers;
        this.writer = writer;
    }

    @Override
    public void run () {

        long start = System.currentTimeMillis ();

        synchronized (writer) {
            writer.write ( numbers );
            writer.flush ();
            writer.close ();
        }

        long finish = System.currentTimeMillis () - start;
        System.out.println ("Thread writer: " + Thread.currentThread ().getName () + " - " + finish + " ms");
    }
}
