package Project.Devops.Sec;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CsvParser test = new CsvParser("rsc\\testFile10.csv");
        test.printDataFrame();
    }
}
