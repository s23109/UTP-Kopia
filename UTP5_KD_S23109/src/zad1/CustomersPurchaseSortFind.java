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
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    private List<Purchase> zakupy ;

    public void readFile (String filename){

        this.zakupy=new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String linia;

            //dopóki nie będzie następna pusta
            while ((linia = br.readLine())!=null){
                zakupy.add(new Purchase(linia));
            }


            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    // /readfile

    private Comparator<Purchase> filtr(String war){

        switch (war){
            case "Nazwiska":{
                return (za1,za2) ->{
                    int roz = za1.getNazwisko().compareTo(za2.getNazwisko());
                    // 0 równe , >0 większe  ...

                    if (roz==0) {
                        //jak id to samo to olac
                        return za1.getId().compareTo(za2.getId());
                    }
                    return roz;
                };

            }
            case "Koszty":{
                return (za1,za2) ->{

                    int roz = (int)(za2.koszt_calosci()-za1.koszt_calosci());


                    if (roz==0) {
                        return za1.getId().compareTo(za2.getId());
                    }
                    return roz;
                };

            }
            default:{

                return (pu1,pu2) -> 0;
            }
        }

    }

    public void showSortedBy(String war){
        System.out.println(war);

        this.zakupy
                .stream()
                .sorted(this.filtr(war)) //sort z comparatora
                .forEach( ele -> System.out.println(ele.getCalosc((war=="Koszty")?true:false))


        );


        System.out.println("");






    }
    // /showSortedBy

    public void showPurchaseFor(String szuk_id){
        System.out.println("Klient "+szuk_id);
        this.zakupy
                .stream()
                .filter((ele)-> ele.getId().equals(szuk_id))
                .forEach((ele)-> System.out.println(ele.getCalosc()));

        System.out.println("");
    }

}
