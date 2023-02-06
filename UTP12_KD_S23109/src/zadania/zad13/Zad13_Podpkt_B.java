package zadania.zad13;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zad13_Podpkt_B{

    Connection con;



    public Zad13_Podpkt_B() {
        //tryb embedded
        String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        String url = "jdbc:derby:D:/Programowanie/Derby_sterowniki+Baza/Derby_DBS/ksidb";

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url);
            polecenie();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void polecenie (){
        System.out.println("Zad13_Podpkt_B - od końca");
        String sel = "select * from pozycje\n" +
                "join autor on pozycje.autid=autor.autid\n" +
                "where rok>2000 and cena>30\n" ;

        try  {
            Statement stmt =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sel);
            rs.last();
             do {
                System.out.print("Autor: " + rs.getString("Name"));
                System.out.print(" | Tytuł: ,," + rs.getString("Tytul") + "''");
                System.out.print(" | Rok wydania: " + rs.getString("Rok"));
                System.out.print(" | Cena: " + rs.getString("Cena"));
                System.out.println("");
            }while (rs.previous()) ;
            System.out.print("------------------------\nRekordy 3,7,9:\n");
             rs.first();
            Integer[] akceptowalne = {3,7,9};
            List<Integer> integerList = new ArrayList<>(Arrays.asList(akceptowalne));
            int lp=1;
            int ile_ogarnietych=0;
            do{

                if (integerList.contains(lp)){
                    //ma to, więc wypisz
                    ile_ogarnietych++;
                    System.out.print("Rekord nr:"+lp);
                    System.out.print(" | Autor: " + rs.getString("Name"));
                    System.out.print(" | Tytuł: ,," + rs.getString("Tytul") + "''");
                    System.out.print(" | Rok wydania: " + rs.getString("Rok"));
                    System.out.print(" | Cena: " + rs.getString("Cena"));
                    System.out.println("");
                }
                lp++;

            }
            while (rs.next());
            lp--;

            if (integerList.size()>ile_ogarnietych-1){
                //nie wszystkie wypisane
                System.out.println("Nie udalo sie wypisac wszystkich rekordow, gdyz takowe nie istnieja: LP max rekordu:"+lp);
            }

            stmt.close();
            con.close();
        } catch (SQLException exc)  {
            System.out.println(exc.getMessage());
        }


    }
    public static void main(String[] args) {

        new Zad13_Podpkt_B();
    }


}
