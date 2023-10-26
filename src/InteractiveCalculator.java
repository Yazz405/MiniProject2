/**
 * using calls to BFCalculator there will be continual user input to evaluate the given expressions
 * 
 * @author Alma Ordaz
 */

import java.io.PrintWriter;
import java.lang.String;
import java.util.Scanner;

public class InteractiveCalculator {
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();

    String exp;

    while(true){
      exp = input.nextLine();

      calculator.evaluate(exp);
      pen.println(calculator.getRunningTotal());

      if(exp.equals("QUIT")){
        break;
      }//if

    }//while

    input.close();
  }//main
}//class InteractiveCalculator
