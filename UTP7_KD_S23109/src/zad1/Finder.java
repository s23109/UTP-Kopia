/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Finder {

    Reader plik;
    String path;
    public Finder(String Path) {
        this.path=Path;
    }

    public int getIfCount() throws IOException {
        int ile_ifow=0;

        plik = new FileReader(path);


        StreamTokenizer streamTokenizer = new StreamTokenizer(plik);

        //komentarze


       streamTokenizer.slashSlashComments(true);
       streamTokenizer.slashStarComments(true);



        // jak ma jeszcze linie
        while ( streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
         //   System.out.println(streamTokenizer.sval);

                if (streamTokenizer.sval != null) {
                 //   System.out.println(streamTokenizer.sval.toLowerCase());
                   if (streamTokenizer.sval.toLowerCase().equals("if")) ile_ifow++;
                }
        }

        plik.close();
        return ile_ifow;
    }

    public int getStringCount(String slowo) throws IOException {
        int ile_slow=0;
        plik = new FileReader(path);


        StreamTokenizer streamTokenizer = new StreamTokenizer(plik);

        //komentarze
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.slashStarComments(true);
        int token;

        while ( (token=streamTokenizer.nextToken() )!= StreamTokenizer.TT_EOF) {

            if (token == '\"')  {
                if (streamTokenizer.sval.equals(slowo)) ile_slow++;
            }



        }
        plik.close();
        return ile_slow;
    }

}
