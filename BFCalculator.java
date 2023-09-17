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
   * store will store the value of the previous expression as tracked by runningTotal
   * into the index corresponding to the inputed char
   */
  public void store(char register){

    this.registers[(int) register - (int) 'a'] = this.runningTotal;

  }//store(char)

  /*
   * seperate will separae exp into a String[] according to " "
   */
  public String[] separate(String exp){
    String[] apart = new String[exp.length()];
    int start = 0;
    int end = 0;
    int j = 0;

    for(int i = 0; i < exp.length(); i++){
       end = i;
      if((String.valueOf(exp.charAt(i))).equals(" ")){

        apart[j] = exp.substring(start, end);
        j++;
        start = end + 1;
      
      }//if
      else if(!((String.valueOf(exp.charAt(exp.length() - 1))).equals(" "))) {
       
        apart[j] = exp.substring(start, exp.length());

      }//else if
  
    }//for
    return apart;
  }//separate(String exp)

  /*
   * exp will not have any chars, exp will be in the form 'num operator num', it will then be seperated by " "
   * and evaluated
   */
  public void evaluateHelp(String exp){
    
    String[] apart = separate(exp);
    BigFraction first = new BigFraction(apart[0]); 
    BigFraction second = new BigFraction(apart[2]);

    if(apart[1].equals("+")){
      this.runningTotal = first.add(second).reduce();

    }//if
    else if(apart[1].equals("/")) {

      this.runningTotal = first.divide(second).reduce();

    }//else if
    else if(apart[1].equals("-")){

      this.runningTotal = first.subtract(second).reduce();

    }//elseif
    else if(apart[1].equals("*")){

      this.runningTotal = first.multiply(second).reduce();

    }//elseif
  }//evaluateHelper


  public BigFraction evaluate(String exp){

    //seperated the string by spaces into String[]
    String[] apart = separate(exp);

    //error case
    if(apart[1].equals(null)){
      System.err.println("Please input a full axpression with +, -, / , *, or STORE seperated by spaces");
     }//if

    for(int i = 0; (i < apart.length) && (!(apart[i + 2] == (null))); i++){

      //check for is the value at i is a char
      String first;
      String second;
      String newExp;

      //tests
      System.out.println("0:" + apart[i]);
      System.out.println("1:" + apart[i + 1]);
      System.out.println("2:" + apart[i + 2]);
      System.out.println("runningTotal: " + this.runningTotal);
      System.out.println("registers: " + this.registers[0]);

      //if on both sides of the operator there is a char
      if((apart[i].matches("[a-z]")) && (apart[i + 2].matches("[a-z]"))){
        first = this.registers[(int) apart[i].charAt(0) - (int) 'a'].toString();
        second = this.registers[apart[i + 2].charAt(0) - (int) 'a'].toString();

        newExp = first.concat(" ");
        newExp = newExp.concat(apart[i + 1]);
        newExp = newExp.concat(" ");
        newExp = newExp.concat(second);

        evaluateHelp(newExp);
      }//if
      //if on the left side of the operator there is a char
      else if((apart[i].matches("[a-z]"))){
        first = registers[(int) apart[i].charAt(0) - (int) 'a'].toString();

        newExp = first.concat(" ");
        newExp = newExp.concat(apart[i + 1]);
        newExp = newExp.concat(" ");
        newExp = newExp.concat(apart[i + 2]);

        evaluateHelp(newExp);
      }//else if
      //if on the left side of the operator there is a char
      else if((apart[i + 1].matches("[a-z]"))){
        second = registers[(int) apart[i + 2].charAt(0) - (int) 'a'].toString();

        newExp = apart[i].concat(" ");
        newExp = newExp.concat(apart[1+1]);
        newExp = newExp.concat(" ");
        newExp = newExp.concat(second);

        evaluateHelp(newExp);
      }//else if
      else if(!((apart[i].matches("[a-z]")) && (apart[i + 2].matches("[a-z]")))) {
        evaluateHelp(exp);
      }//else if
      else if(apart[i].equals("STORE ")){
        store(apart[i+1].charAt(0));
        System.out.println("store");
      }//else if

    }//for

    return this.runningTotal;

  }//evaluate
}//class BFCalculator
