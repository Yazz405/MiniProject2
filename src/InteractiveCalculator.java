
/**
 * using calls to BFCalculator there will be continual user input to evaluate the given expressions
 * 
 * @author Alma Ordaz
 */

import java.io.PrintWriter;
import java.lang.String;
import java.math.BigInteger;
import java.util.Scanner;

public class InteractiveCalculator {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();

    String exp;
    BigFraction result;

    while (true) {
      exp = input.nextLine();

      if (exp.equals("QUIT")) {
        break;
      } // if

      calculator.evaluate(exp);
      result = calculator.getRunningTotal();
      
      if (!(exp.contains("STORE"))) {
        if (result.denominator().intValue() == 1){
          pen.println(result.numerator());
        }//if
        else{
          pen.println(result);
        }//else
      } // if

    } // while

    input.close();
  }// main
}// class InteractiveCalculator
