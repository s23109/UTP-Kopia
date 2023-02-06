/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(  (String lista) -> lista.startsWith("WAW")
                        )
                       .mapEvery( (String lista) ->{
                         // WAW DEST PRICE
                         String[] linia = lista.split(" ");
                         int cena_po= (int)((Integer.parseInt(linia[2]))*xrate);
                         String do_ret = "to "+linia[1]+" - price in PLN:\t"+cena_po;
                          return do_ret;

                               }
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
