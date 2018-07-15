/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(e -> ((String) e).split("\\s+")[0].equals("WAW")
                        )
                       .mapEvery(e -> new String("to " + ((String) e)
                      		   .split("\\s+")[1] + " - price in PLN: " + 
                       		   (int)(Integer.parseInt(((String) e)
                       				   .split("\\s+")[2]) * xrate))    
                    		   
                    		   /* e -> {
                    	   String [] s = ((String) e).split("\\s+");
                    	   String airport = new String(s[1]);
                    	   int price = (int)(Integer.parseInt(s[2]) * xrate);
                    	   return String.format("to %s - price in PLN: %2d", airport, price);
                       } */
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
