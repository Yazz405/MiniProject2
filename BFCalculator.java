public class BFCalculator {

  public BigFraction evaluate(String exp){

    String[] apart = new String[exp.length()];
   // String currentStr = exp;
    int start = 0;
    int end = 0;

    for(int i = 0; i < exp.length(); i++){
      int j = 0;
      if((String.valueOf(exp.charAt(i))).equals(" ")){
        apart[j] = exp.substring(start, (end + 1));
        //currentStr = exp.substring(end + 1, currentStr.length() + 1);
        
        start = end;
        j++;
      }//if

      end = i;
    }//for


  }//evaluate
  
}
