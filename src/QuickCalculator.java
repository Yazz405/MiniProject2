/**
 * using calls to BFCalculator the inputs from the user will be evaluated
 * 
 * @author Alma Ordaz
 */

import java.io.PrintWriter;

public class QuickCalculator {
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator();

    for(int i = 0; i < args.length; i++){

      calculator.evaluate(args[i]);
      BigFraction result = calculator.getRunningTotal();

      if(!(args[i].contains("STORE"))){
        if (result.denominator().intValue() == 1){
          pen.println(args[i] + " = " + result.numerator());
        }//if
        else {
          pen.println(args[i] + " = " + result);
        }//else
      }//if
    }//for
  }//main
}//class QuickCalculator
