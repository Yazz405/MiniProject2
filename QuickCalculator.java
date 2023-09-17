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

      BigFraction result = calculator.evaluate(args[i]);

      if(!(args[i].contains("STORE"))){
        pen.println(args[i] + " = " + result);
      }//if
    }//for
  }//main
}//class QuickCalculator
