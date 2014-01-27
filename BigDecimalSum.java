import java.math.BigDecimal;
import java.util.LinkedList;

public class BigDecimalSum{

  private String currentResult = "0";

  public BigDecimalSum(LinkedList<String> listOfNumbers){
    sumList(listOfNumbers);
  }

  public String getBigDecimalNumber(){
    return currentResult.substring(0, 10);
  }

  private void sumList(LinkedList<String> listOfNumbers){
    for(int count = 0; count < listOfNumbers.size() ; count++){
      currentResult = sumFirstTwoNumbersList ( currentResult , listOfNumbers.get(count) );
    }
    System.out.println(currentResult);
  }

  private String sumFirstTwoNumbersList (String firstNumber, String secondNumber){
    BigDecimal firstNumberToBigDecimal = new BigDecimal(firstNumber);
    BigDecimal resultOfTheSum = firstNumberToBigDecimal.add(new BigDecimal(secondNumber));
    return resultOfTheSum.toString();
  }

}
