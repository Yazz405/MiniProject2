import java.io.PrintWriter;
import java.lang.String;

public class InteractiveCalculator {
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    BigFraction f1 = new BigFraction(3, 4);
    BigFraction f2 = new BigFraction(5, 4);

    pen.println((f1.reduce()).toString());
    pen.println(((f1.subtract(f2)).reduce()).toString());
    pen.println("hello". charAt(1));
    pen.println("a".equals("a"));
    


  }//main
}//InteractiveCalculator
