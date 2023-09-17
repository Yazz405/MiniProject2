import java.math.BigInteger;
import java.lang.String;

public class BigFraction {

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Alma Ordaz
 */
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented 
   * with a negative numerator. Similarly, if a fraction has a negative numerator, it 
   * is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a fraction 
   * in simplified form, one must call the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the Bigfraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the Bigfraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // Fraction(int, int)

  /**
   * Build a new fraction by parsing a string, the string is either in the form "x/y" or just "x".
   */
  public BigFraction(String str) {

    if(!(str.contains("/"))){
      this.num = BigInteger.valueOf(java.lang.Integer.parseInt(str));
      this.denom = BigInteger.valueOf(1);

    }//if
    else{
      int index = str.indexOf("/");
      String num = str.substring(0, index);
      String denom = str.substring((index + 1), str.length());
  
      this.num = BigInteger.valueOf(java.lang.Integer.parseInt(num));
      this.denom = BigInteger.valueOf(java.lang.Integer.parseInt(denom));
    }//else

  } // Fraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /*
   * Reduce the fraction
   */
  public BigFraction reduce(){
    BigInteger gcf = BigInteger.valueOf(0);

    for(BigInteger i = BigInteger.valueOf(1); i.compareTo(this.num.max(this.denom)) <= -1; i = i.add(BigInteger.valueOf(1))){
      if((this.num.mod(i) == BigInteger.valueOf(0)) && (this.denom.mod(i) == BigInteger.valueOf(0))){
        gcf = i;
      }//if
    }//for
    
    return new BigFraction(this.num.divide(gcf), this.denom.divide(gcf));

  }//reduce

  /**
   * Add the fraction `addMe` to this fraction.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(addMe.denom);
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    return new BigFraction(resultNumerator, resultDenominator);
  }// add(BigFraction)

  /*
   * subtract dif from this fraction
   */
  public BigFraction subtract(BigFraction dif) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(dif.denom);
    resultNumerator = (dif.num.multiply(this.denom)).subtract(this.num.multiply(dif.denom));

    return new BigFraction(resultNumerator, resultDenominator);
  }// subtract(BigFraction)

  /*
   * divide this fraction by div
   */
  public BigFraction divide(BigFraction div){
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultNumerator = this.num.multiply(div.denom);
    resultDenominator = this.denom.multiply(div.num);

    return new BigFraction(resultNumerator, resultDenominator);

  }//divide(BigFraction)

  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()
  
  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()
  
  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  /*
   * multiply this fraction and other
   */
  public BigFraction multiply (BigFraction other){
    BigInteger resultDenominator;
    BigInteger resultNominator;
    
    resultDenominator = this.denom.multiply(other.denom);
    resultNominator = this.num.multiply(other.num);

    return new BigFraction(resultNominator, resultDenominator);

  }

  /*
   * returns the fractional of this fraction
   */
  public BigFraction fractional (){

    BigInteger resultNumerator;

    resultNumerator = this.num.mod(this.denom);

    return new BigFraction(resultNumerator, this.denom);

  }

} // class BigFraction

