import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {

    public static void main ( String[] args ) throws Exception {

        List<PrintWriter> writerList = new ArrayList<> (  );
        for ( int i = 1 ; i <= 5 ; i++ ) {
            writerList.add ( new PrintWriter ( "src/main/resources/numbers" + i + ".txt" ) );
        }

        char[] letters = { 'У' , 'К' , 'Е' , 'Н' , 'Х' , 'В' , 'А' , 'Р' , 'О' , 'С' , 'М' , 'Т' };

        ThreadPoolExecutor executorGenerator = ( ThreadPoolExecutor ) Executors.newFixedThreadPool ( 10 );


        for ( int i = 10; i <= 100; i += 10 ) {
            executorGenerator.execute ( new TaskGenerator ( writerList , i , letters ) );
        }

        executorGenerator.shutdown ();
    }

}
