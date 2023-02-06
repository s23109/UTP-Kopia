/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */
package zad2;
import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class Main {
  public static void main(String[] args) {
      BufferedReader br = null;
      try {
          br = new BufferedReader(new FileReader("./src/zad2/unixdict.txt"));
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }// sorted slowo, reszta slow
      Map<String,List<String>> slowa = br.lines()
              .collect(Collectors.groupingBy(s -> {
                char[] l=s.toCharArray();
                Arrays.sort(l);
                return String.valueOf(l);
              }, LinkedHashMap::new, Collectors.toList()
              ));

      int max=slowa.values().stream().map(List::size).max(Integer::compare).get();
      slowa.values().stream().filter(e -> e.size()==max).forEach(e ->System.out.println(String.join(" ", e)));
  }
}
