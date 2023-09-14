import java.io.PrintWriter;

public class QuickCalculator {
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    BigFraction f1 = new BigFraction(2, 3);
    BigFraction f2 = new BigFraction(2, 5);

    pen.println(f1.add(f2));


  }//main
  
}
