package zadania.zad12;

import java.sql.*;

public class Ins1 {

    static public void main(String[] args) {
        new Ins1();
    }

    Statement stmt;

    Ins1()  {
        Connection con = null;
        Statement stmt = null;
        try {
            String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
            String url = "jdbc:derby:D:/Programowanie/Derby_sterowniki+Baza/Derby_DBS/ksidb";

            con = DriverManager.getConnection(url);
            stmt=con.createStatement();
        } catch (Exception exc)  {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = new String[wyd.length];// ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...
        int pom=0;
        for (String a:wyd
             ) {
            ins[pom]="Insert into wydawca (wydid, name) values ("+(beginKey+pom)+",'"+wyd[pom]+"')";
            pom++;
        }

        int insCount = 0;   // ile rekordów wpisano
        try  {
            for (int i=0; i < ins.length; i++) {// wpisywanie rekordów
            try {
                stmt.executeUpdate(ins[i]);
                insCount++;
            }
            catch (Exception a){
                a.printStackTrace();
            }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
//...
    }
}