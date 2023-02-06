package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    // nie hashmap zwykły bo autosort ?
    private Map<String , List<String>> jezyk_czlowiek = new LinkedHashMap<>();
    private Map<String , List<String>> czlowiek_jezyk = new LinkedHashMap<>();

    public ProgLang(String dest) {

        try {
            BufferedReader in = new BufferedReader(new FileReader(dest));
            String linia_plik ;

            while ((linia_plik=in.readLine())!=null){
                /*
                język1<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
                język2<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
                (...)
                 */
                String[] linia =  linia_plik.split("\t");

                List<String> ludzie_z_dupli = new ArrayList<>(Arrays.asList(linia).subList(1,linia.length));

                List<String> ludzie = new ArrayList<>();
                for (String czl: ludzie_z_dupli) {

                    if (!ludzie.contains(czl)) {
                        //jak nie ma to dodaj
                        ludzie.add(czl);
                    }

                }

                jezyk_czlowiek.put(linia[0],ludzie);


                for (String czlowiek:ludzie) {
                    if (czlowiek_jezyk.containsKey(czlowiek)){
                        //jak zawiera to dodaj sam jezyk
                        czlowiek_jezyk.get(czlowiek).add(linia[0]);
                    }
                    else {
                        // nie ma typa, dodaj liste i klucz
                        List<String> jezyki_typa = new ArrayList<>();
                        jezyki_typa.add(linia[0]);
                        czlowiek_jezyk.put(czlowiek,jezyki_typa);
                    }
                //for każdy człowiek z listy
                }

                //while z pliku
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Map<String,List<String>> getLangsMap(){
        return jezyk_czlowiek;
    }

    public Map<String,List<String>> getProgsMap(){
        return czlowiek_jezyk;
    }

    public <T,U> Map<T,List<U>> sorted (Map<T,List<U>> mapa , Comparator<Map.Entry<T, List<U>>> comparator){

        LinkedHashMap<T,List<U>> posortowana = new LinkedHashMap<>();

        mapa.entrySet()
                .stream()
                .sorted(comparator)
                .forEach(ele -> posortowana.put(ele.getKey(), ele.getValue()));

        return posortowana;
    }

    public <T,U> Map<T,List<U>> filtered (Map<T,List<U>> mapa , Predicate<Map.Entry<T,List<U>>> predicate){

        LinkedHashMap<T,List<U>> przefiltrowana = new LinkedHashMap<>();
        mapa.entrySet()
                .stream()
                .filter(predicate)
                .forEach(ele -> przefiltrowana.put(ele.getKey(),ele.getValue()));
        return przefiltrowana;
    }

    public Map<String,List<String>> getLangsMapSortedByNumOfProgs(){
        return sorted(this.jezyk_czlowiek,(el1,el2)->{
            int roz = ((el2.getValue()).size())-((el1.getValue()).size());

            if (roz==0) {
                //takie same
                return el1.getKey().compareTo(el2.getKey());
            }
            return roz;

        });
    }

    public Map<String,List<String>> getProgsMapSortedByNumOfLangs(){
        return sorted(this.czlowiek_jezyk,(el1,el2)->{
            int roz = ((el2.getValue()).size())-((el1.getValue()).size());

            if (roz==0) {
                //takie same
                return el1.getKey().compareTo(el2.getKey());
            }
            return roz;

        });
    }

    public Map<String,List<String>> getProgsMapForNumOfLangsGreaterThan(int wartosc){
        return filtered(this.czlowiek_jezyk,listEntry-> listEntry.getValue().size()>wartosc);



        }
    }


