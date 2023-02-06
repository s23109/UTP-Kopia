/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Anagrams {

    private List<String> slowa;

    public boolean checkAnagram(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        if (s1.length() != s2.length())
            return false;

        List<Character> list1 = new ArrayList<Character>();
        List<Character> list2 = new ArrayList<Character>();

        for (int i = 0; i < s1.length(); i++) {
            list1.add(s1.charAt(i));
        }
        for (int i = 0; i < s2.length(); i++) {
            list2.add(s2.charAt(i));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        if (list1.equals(list2))
            return true;
        else
            return false;
    }

    //konst
    public Anagrams(String adres) {
        this.slowa = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(adres));
            String linia;


            // uwaga linia ma wiele slow
            while ((linia = br.readLine()) != null) {
                this.slowa.addAll(Arrays.asList(linia.split(" ")));
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<ArrayList<String>> getSortedByAnQty() {
        //po wielkości
        ArrayList<ArrayList<String>> anagramy = new ArrayList<>();

        for (String slowo : slowa) {
            //dla każdego slowa w liscie

            boolean czy_juz_jest = false;

            // dla kazdej dotychczasowej listy w anagramy sprawdz czy jest podobny
            for (ArrayList<String> lista : anagramy) {

                //jesli jest anagramem z 1 slowem to dodaj
                if (this.checkAnagram(lista.get(0), slowo)) {
                    lista.add(slowo);
                    czy_juz_jest = true;
                    break;
                }
            }

            //jesli nie znaleziono, to zrob nową listę i ją dodaj do większej
            if (czy_juz_jest == false) {
                ArrayList<String> nowalista = new ArrayList<>();
                nowalista.add(slowo);
                anagramy.add(nowalista);
            }

            //dodawanie argumentow
        }

        anagramy.sort((list1, list2) -> {

            int roz = list2.size() - list1.size();

            if (list1 == list2) {
                return list1.get(0).compareTo(list2.get(0));
            }

            return roz;
        });

        return anagramy;

    }

    public String getAnagramsFor(String szukany) {

        ArrayList<String> anagramy = new ArrayList<>();
        boolean czytosamo = true;
        for (String slowo : slowa) {
            if (checkAnagram(slowo, szukany)) {
                anagramy.add(slowo);
                if (slowo != szukany) czytosamo = false;
            }

        }

        //brak slowa w slowach
        if (anagramy.size() == 0) return szukany + ":null";


        //jest jedno ale nie wiadomo czy to samo, czy anagram
        if (anagramy.size() == 1) {
            if (!czytosamo) return szukany + ": []";
            else return szukany + ":null";
        }


        //dla 1+

            return szukany + ": " + (
                    anagramy.stream()
                            .filter(s -> !s.equals(szukany))
                            .collect(Collectors.toList())
            );


    }
}
