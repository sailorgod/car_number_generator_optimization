import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {

    public static void main ( String[] args ) throws Exception {

        ThreadPoolExecutor executor = ( ThreadPoolExecutor ) Executors.newFixedThreadPool ( 5 );

        for ( int i = 0 ; i < executor.getMaximumPoolSize ( ) ; i++ ) {
            String threadNumber = Integer.toString ( i );
            executor.execute ( new TaskWriter ( threadNumber ) );
        }
        executor.shutdown ( );

    }

}
