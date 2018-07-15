/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad2;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

/*<-- niezbędne importy */

public class Main {

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
    List<String> result = dest.stream().filter(e -> ((String) e).split("\\s+")[0].equals("WAW")).map(e -> new String("to " + ((String) e)
                    		   .split("\\s+")[1] + " - price in PLN: " + 
                    		   (int)(Integer.parseInt(((String) e)
                    				   .split("\\s+")[2]) * ratePLNvsEUR))).collect(toList());
   

    for (String r : result) System.out.println(r);
  }
}
