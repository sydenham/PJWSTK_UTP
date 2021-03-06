/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Calc {
	
    private static HashMap<String, BinaryOperator<BigDecimal>> commands = new HashMap<String, BinaryOperator<BigDecimal>>();
	
    static {
    	commands.put("+" , new BinaryOperator<BigDecimal>(){
			public BigDecimal apply(BigDecimal t, BigDecimal u) {
				return t.add(u);
			}
    	});
    	commands.put("-" , new BinaryOperator<BigDecimal>(){
			public BigDecimal apply(BigDecimal t, BigDecimal u) {
				return t.subtract(u);
			}
    	});
    	
    	commands.put("*" , new BinaryOperator<BigDecimal>(){
			public BigDecimal apply(BigDecimal t, BigDecimal u) {
				return t.multiply(u);
			}
    	});
    	commands.put("/" , new BinaryOperator<BigDecimal>(){
			public BigDecimal apply(BigDecimal t, BigDecimal u) {
				return t.divide(u, 7, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
			}
    	});
    }
    
	public String doCalc(String calc){
		try {
			String [] args = calc.split("\\s+");
			return commands.get(args[1]).apply(new BigDecimal(args[0]), new BigDecimal(args[2])).toString();
		} catch (Exception e) {
			return "Invalid command to calc";			
		}
	}	
}
  



	
	
