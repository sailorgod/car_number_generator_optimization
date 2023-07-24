import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TaskWriter implements Runnable {

    private final String taskNumber;

    public TaskWriter ( String taskNumber ) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run () {
        long start = System.currentTimeMillis ( );

        //Заменен FileOutputStream на PrintWriter, время работы программы уменьшилось с 34с до 3000мс.
        //Причина такой разницы в том, что FileOutputStream записывает побитово.

        try {
            PrintWriter writer = new PrintWriter ( "src/main/resources/numbers" + taskNumber + ".txt" );
            StringBuffer buffer = new StringBuffer ( );

            char[] letters = { 'У' , 'К' , 'Е' , 'Н' , 'Х' , 'В' , 'А' , 'Р' , 'О' , 'С' , 'М' , 'Т' };
            for ( int number = 1 ; number < 1000 ; number++ ) {
                int regionCode = 199;
                for ( char firstLetter : letters ) {
                    for ( char secondLetter : letters ) {
                        for ( char thirdLetter : letters ) {
                            //Конкатенация строк заменена на StringBuffer.
                            //Время работы программы уменьшилось с 3000 мс до 2500 мсс.
                            //Конкатенация - долгая по времени операция, потому что строки иммутабельны
                            //и при конкатенации каждый раз создается новая строка на основе предыдущей.
                            buffer.append ( firstLetter + padNumber ( number , 3 )
                                    + secondLetter + thirdLetter + padNumber ( regionCode , 2 ) + "\n" );
                        }
                    }
                }
                //Запись в файл происходит с каждой итерацией внешнего цикла, время уменьшилось с 2500 мс до 2300 мс
                //Это происходит, потому что запись в файл ведется не построчно, в файл записывается сразу буфер.
                writer.write ( buffer.toString ( ) );
                buffer = new StringBuffer ( );
            }
            writer.flush ( );
            writer.close ( );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ( );
        }

        System.out.println ( "Thread " + taskNumber + ": " + ( System.currentTimeMillis ( ) - start ) + " ms" );
    }

    private String padNumber ( int number , int numberLength ) {
        String numberStr = Integer.toString ( number );
        StringBuffer buffer = new StringBuffer ( );
        buffer.append ( numberStr );
        int padSize = numberLength - numberStr.length ( );

        //Конкатенация строк и цикл заменены на StringBuffer с методом repeat().
        //Время выполнения программы уменьшилось до 1800 мс
        buffer.append ( ( "0" + numberStr ).repeat ( Math.max ( 0 , padSize ) ) );

        return numberStr;
    }
}
