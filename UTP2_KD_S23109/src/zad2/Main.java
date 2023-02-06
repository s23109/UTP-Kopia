/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad2;


import java.util.*;
import java.util.stream.Collectors;

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
    List<String> result = dest.stream()
            .filter(lista ->  lista.startsWith("WAW"))
            .map( lista -> {
                      String[] linia = lista.split(" ");
                      int cena_po = (int) ((Integer.parseInt(linia[2])) * ratePLNvsEUR);
                      String do_ret = "to " + linia[1] + " - price in PLN:\t" + cena_po;
                      return do_ret;
                    }
            )
            .collect(Collectors.toList());


    for (String r : result) System.out.println(r);
  }
}
