
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
    System.out.println("index: " + ((int) register - (int) 'a'));

  }// store(char)

  /*
   * will separate exp into a String[] according to " "
   */
  public String[] separate(String exp) {
    String[] apart = new String[exp.length()];
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

    String[] apart = separate(exp);
    BigFraction first = new BigFraction(apart[0]);
    BigFraction second = new BigFraction(apart[2]);

    if (apart[1].equals("+")) {
      this.runningTotal = first.add(second).reduce();
    } // if
    else if (apart[1].equals("/")) {
      this.runningTotal = first.divide(second).reduce();
    } // else if
    else if (apart[1].equals("-")) {
      this.runningTotal = first.subtract(second).reduce();
    } // elseif
    else if (apart[1].equals("*")) {
      this.runningTotal = first.multiply(second).reduce();
    } // elseif
  }// evaluateHelper(String)

  /*
   * Precondition: exp will only be a string with one operation or a single STORE
   * 
   * Will evaluate the expression in exp
   */
  public void evaluate(String exp) {

    // variable declarations
    String first;
    String second;
    String newExp;
    int left = 0;
    int right = 2;
    int operator = 1;

    // seperated the string by spaces into String[]
    String[] apart = separate(exp);

    // error case
    if (apart[1].equals(null)) {
      System.err.println("Please input a full axpression with +, -, / , *, or STORE seperated by spaces");
    } // if

    // storing a value
    if (apart[0].equals("STORE")) {
      store(apart[1].charAt(0));
    } // if
    else if ((apart[left].matches("[a-z]")) && (apart[right].matches("[a-z]"))) {
      // if you are adding to chars with stored values

      first = this.registers[(int) apart[left].charAt(0) - (int) 'a'].toString();
      second = this.registers[apart[right].charAt(0) - (int) 'a'].toString();

      newExp = first.concat(" ");
      newExp = newExp.concat(apart[operator]);
      newExp = newExp.concat(" ");
      newExp = newExp.concat(second);

      evaluateHelp(newExp);
    } // else if
    else if ((apart[left].matches("[a-z]"))) {
      // if there is a char with a stored value on the left of the arithmetic sign

      first = registers[(int) apart[left].charAt(0) - (int) 'a'].toString();

      newExp = first.concat(" ");
      newExp = newExp.concat(apart[operator]);
      newExp = newExp.concat(" ");
      newExp = newExp.concat(apart[right]);

      evaluateHelp(newExp);
    } // else if
    else if ((apart[right].matches("[a-z]"))) {
      // if there is a char with a stored value on the right of the arithmetic sign
      second = registers[(int) apart[right].charAt(0) - (int) 'a'].toString();

      newExp = apart[left].concat(" ");
      newExp = newExp.concat(apart[operator]);
      newExp = newExp.concat(" ");
      newExp = newExp.concat(second);

      evaluateHelp(newExp);
    } // else if
    else {
      evaluateHelp(exp);
    } // else

  }//evaluate(String)

  //gets the runningTotal
  public BigFraction getRunningTotal(){
    return this.runningTotal;
  }//getRunningTotal()

}// class BFCalculator
