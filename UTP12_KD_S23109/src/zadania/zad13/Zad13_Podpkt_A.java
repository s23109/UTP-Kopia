package zadania.zad13;


import java.sql.*;

public class Zad13_Podpkt_A {

    Connection con;

    ResultSet rs;

    public Zad13_Podpkt_A() {
        //tryb embedded
        String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        String url = "jdbc:derby:D:/Programowanie/Derby_sterowniki+Baza/Derby_DBS/ksidb";

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url);
            // selecty itp
            polecenie();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void polecenie (){
        System.out.println("Zad13_Podpkt_A");
        String sel = "select * from pozycje\n" +
                "join autor on pozycje.autid=autor.autid\n" +
                "where rok>2000 and cena>30\n" ;

        try  {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sel);
            while (rs.next())  {
                System.out.print("Autor: " + rs.getString("Name"));
                System.out.print(" | Tytuł: ,," + rs.getString("Tytul") + "''");
                System.out.print(" | Rok wydania: " + rs.getString("Rok"));
                System.out.print(" | Cena: " + rs.getString("Cena"));
                System.out.println("");
            }
            stmt.close();
            con.close();
        } catch (SQLException exc)  {
            System.out.println(exc.getMessage());
        }


    }




    public static void main(String[] args) {

        new Zad13_Podpkt_A();
    }


}
