/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
  public static void main(String[] args) {

    Function <String,List<String> > flines = (lokalizacja) ->{
      List<String> linie= new ArrayList<>();
      BufferedReader bufer = null;
      String linia;

      try {
        bufer = new BufferedReader(new FileReader(lokalizacja));

        while ((linia=bufer.readLine())!=null){
          linie.add(linia);
        }



        bufer.close();
        return linie;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return linie;
      }
      catch (IOException e) {
        e.printStackTrace();
        return linie;
      }


    };

    Function <List<String>,String> join = (lista) -> {
    String doret = String.join("" ,lista);
    return doret;
    };

    Function <String, List<Integer>> collectInts  = (linia) -> {

      List <Integer> doret = new ArrayList<>();
/*
      String[] wyrazy = linia.split(" ");
      Function <String,Boolean> czyLiczba = (wyraz) ->{

        String liczby[]={"0","1","2","3","4","5","6","7","8","9"};
        boolean ma_liczbe=false;
        int ile_liczb=0;
        for (int i=0;i<wyraz.length();i++){

          for (int j=0;j<10;j++){
            if (wyraz[i]==liczby[j]){ ma_liczbe=true;}
          }

          if (ma_liczbe) ile_liczb++;

        }

        if ((ile_liczb)!=wyraz.length()) return false;
        return true;
      };
*/
      // replace all _ zmień a na b , trim - wywal białe znaki ze stringa ,
      for (String i:linia.replaceAll("[^\\d]"," ").trim().split(" "))
            {

          if (i.length()>0){
            doret.add(Integer.parseInt(i));
          }


      }

    return doret;
    };

    Function <List<Integer> , Integer> sum = (liczby) ->{
      Integer suma=0;
      for (Integer i:liczby
           ) { suma+=i;

      }
      return suma;
    };

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
