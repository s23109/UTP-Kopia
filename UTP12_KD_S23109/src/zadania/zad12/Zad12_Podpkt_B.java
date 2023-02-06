package zadania.zad12;

import java.sql.*;

public class Zad12_Podpkt_B {

    public static void main(String[] args) {
        new Zad12_Podpkt_B();
    }

    public Zad12_Podpkt_B() {

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
            String url = "jdbc:derby:D:/Programowanie/Derby_sterowniki+Baza/Derby_DBS/ksidb";
            con = DriverManager.getConnection(url);

            String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };
            int beginKey = 15;
            int delCount =  0;
            try  {

                // przygotowanie instrukcji prekompilowanej
                stmt = con.prepareStatement("DELETE FROM Wydawca where wydid>= ? and name= ? ");	// usunięcie z tabeli WYDAWCA rekordu o podanej nazwie wydawcy z tablicy wyd lub o podanym numerze wydawcy zaczynającym się od beginKey

                for (int i=0; i < wyd.length; i++)   {
                    stmt.setString(1, String.valueOf(beginKey));
                    stmt.setString(2,wyd[i]);
                    stmt.executeUpdate();
                }
                stmt.close();
                con.close();
            } catch(SQLException exc)  {
                System.out.println(exc);
            }

            // ...

        } catch (Exception exc)  {
            System.out.println(exc);
            System.exit(1);
        }



    }



}
