
/**
 * An implementation of a calculator that will do operations on fractions
 * 
 * @author Alma Ordaz
 */

import java.lang.String;

public class BFCalculator {

  BigFraction runningTotal;
  BigFraction[] registers = new BigFraction[26];

  /*
   * store will store the value of the previous expression as tracked by
   * runningTotal
   * into the index corresponding to the inputed char
   */
  public void store(char register) {

    this.registers[(int) register - (int) 'a'] = this.runningTotal;
  }// store(char)

  /*
   * will separate exp into a String[] according to " "
   */
  public String[] separate(String exp) {
    String[] apart = new String[exp.length() + 1];
    int start = 0;
    int end = 0;
    int j = 0;

    for (int i = 0; i < exp.length(); i++) {
      end = i;
      if ((String.valueOf(exp.charAt(i))).equals(" ")) {

        apart[j] = exp.substring(start, end);
        j++;
        start = end + 1;

      } // if
      else if (!((String.valueOf(exp.charAt(exp.length() - 1))).equals(" "))) {
        apart[j] = exp.substring(start, exp.length());
      } // else if

    } // for
    return apart;
  }// separate(String)

  /*
   * exp will not have any chars, exp will be in the form 'num operator num', it
   * will then be seperated by " "
   * and evaluated
   */
  public void evaluateHelp(String exp) {

    // variable declarations
    String[] apart = separate(exp);
    BigFraction first = new BigFraction(apart[0]);
    BigFraction second;
    BigFraction total = new BigFraction("1");

    for (int i = 1; apart[i] != null; i += 2) {

      second = new BigFraction(apart[i + 1]);

      if (apart[i].equals("+")) {
        total = first.add(second).reduce();
      } // if
      else if (apart[i].equals("/")) {
        total = first.divide(second).reduce();
      } // else if
      else if (apart[i].equals("-")) {
        total = first.subtract(second).reduce();
      } // elseif
      else if (apart[i].equals("*")) {
        total = first.multiply(second).reduce();
      } // elseif

      first = total;
    } // for

    this.runningTotal = total;

  }// evaluateHelper(String)

  /*
   * Precondition: exp will only be a string with one operation or a single STORE
   * 
   * Will evaluate the expression in exp
   */
  public void evaluate(String exp) {

    String newExp;

    // seperated the string by spaces into String[]
    // separate the expression by its idividual components
    String[] apart = separate(exp);

    // error checking
    if (apart[1] == null) {
      System.err.println("this is not an expresssion");
      System.exit(1);
    } // if

    if(!(isOperation(apart[1])) && !(Character.isAlphabetic(apart[1].charAt(0)))){
      System.err.println("not a valid expression");
      System.exit(1);
    }//if

    // storing a value
    if (apart[0].equals("STORE")) {
      store(apart[1].charAt(0));
    } // if
    else {
      for (int i = 0; apart[i] != null; ++i) {
        if (apart[i].matches("[a-z]")) {
          apart[i] = registers[(int) apart[i].charAt(0) - (int) 'a'].toString();
        } // if
      } // for

      newExp = apart[0];
      for (int i = 1; apart[i] != null; i++) {

        newExp = newExp.concat(" ");
        newExp = newExp.concat(apart[i]);
      } // for
      evaluateHelp(newExp);
    } // else

  }// evaluate(String)

  /*
   * gets the runningTotal
   */
  public BigFraction getRunningTotal() {
    return this.runningTotal;
  }// getRunningTotal()

  /*
   * returns is the string represents an operation
   */
  public boolean isOperation(String operator) {

    return operator.equals("+") || operator.equals("-") ||
        operator.equals("/") || operator.equals("*");

  }// isOperation(String)

}// class BFCalculator
