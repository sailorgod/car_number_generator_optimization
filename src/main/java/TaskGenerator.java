import java.io.PrintWriter;
import java.util.List;

public class TaskGenerator implements Runnable {

    private final List<PrintWriter> printWriters;
    private final int regionCode;
    private final char[] letters;

    public TaskGenerator (  List<PrintWriter> printWriters, int regionCode, char[] letters ) {
        this.printWriters = printWriters;
        this.regionCode = regionCode;
        this.letters = letters;
    }

    @Override
    public void run () {
        long start = System.currentTimeMillis ( );
        StringBuilder buffer = new StringBuilder ( );

        for ( int region = regionCode - 10; region <= regionCode ; region++ ) {

            for ( int number = 1 ; number < 1000 ; number++ ) {

                for ( char firstLetter : letters ) {

                    for ( char secondLetter : letters ) {

                        for ( char thirdLetter : letters ) {
                            buffer.append ( firstLetter )
                                    .append ( padNumber ( number , 3 ) )
                                    .append ( secondLetter )
                                    .append ( thirdLetter )
                                    .append ( padNumber ( region , 2 ) ).append ( "\n" );
                        }
                    }
                }

            }

            for ( PrintWriter writer : printWriters ) {
                new TaskWriter ( buffer.toString ( ) , writer ).run ( );
            }
            buffer = new StringBuilder (  );
        }

        long finish = System.currentTimeMillis ( ) - start;
        System.out.println ( "Thread generator: " + Thread.currentThread ().getName ()  + " - " + finish + " ms" );
    }

    private String padNumber (int number , int numberLength ) {
        String numberStr = Integer.toString ( number );
        int padSize = numberLength - numberStr.length ( );

        for ( int i = 0 ; i < padSize ; i++ ) {
            numberStr = "0" + numberStr;
        }

        return numberStr;
    }

}
