package zadania.zad11;

import java.sql.*;

public class Zad11 {

    static public void main(String[] args) {
        new Zad11();
    }

    Statement stmt;

    String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
    String url = "jdbc:derby:D:/Programowanie/Derby_sterowniki+Baza/Derby_DBS/ksidb";


    Zad11() {
        Connection con = null;
        try {
           // Class.forName(driverName);
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();

        } catch (Exception exc)  {
            System.out.println(exc.getMessage());
            System.exit(1);
        }

        // metoda dropTable jest naszą własną metodą napisaną dla skrócenia programu
        // usuwa ona tabelę podaną jako argument
        // Aby w każdych okolicznościach stworzyć nową tabelę AUTOR
        // musimy usunąć ew.  już istniejącą tabelę AUTOR
        dropTable("POZYCJE"); // usunięcie tabeli pochodnej, będącej w relacji z tabelą AUTOR
        dropTable("AUTOR");   // usunięcie tabeli AUTOR

        String crestmt =  "CREATE TABLE AUTOR ( " +
                "AUID  INTEGER, " +
                "NAME VARCHAR(255)," +
                "PRIMARY KEY(AUID) )";

        try {
            // wykonanie polecenia zapisanego w crestmt
            stmt.executeUpdate(crestmt);
            System.out.println("Table created.");

        } catch (SQLException exc) {                      // przechwycenie wyjątku:
            System.out.println("SQL except.: " + exc.getMessage());
            System.out.println("SQL state  : " + exc.getSQLState());
            System.out.println("Vendor errc: " + exc.getErrorCode());
            System.exit(1);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void dropTable(String tname) {
        String creatStm = "DROP TABLE " + tname;

        try {
            stmt.executeUpdate(creatStm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}