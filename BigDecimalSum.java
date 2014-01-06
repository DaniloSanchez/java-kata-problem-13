import java.math.BigDecimal;
import java.util.LinkedList;

public class BigDecimalSum{

	private String result = "0";
    
    public void sumList(LinkedList<String> listOfNumbers){
        for(int count = 0; count < listOfNumbers.size() ; count++){
            result = sum( result , listOfNumbers.get(count) );
        }
        System.out.println(result);
    }

    private String sum (String num1, String num2){
        BigDecimal addend = new BigDecimal(num1);
        BigDecimal augend = addend.add(new BigDecimal(num2));
        return augend.toString();
    }

  	public String getResult(){
  		return result.substring(0, 10);
  	}

}
