
package week05;

public class CSVTokenTest1 {
    public static void main(String[] args){
        CSVTokenizer csvt = new CSVTokenizer(" \"this\" ,1, \"is\" , \"a\" , \"test\" ", ",", "\"");
        while (csvt.hasMoreTokens()) {
            System.out.println(csvt.nextToken());
        }

    }
}
