package zad2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scan_for_classes {

    static List<String> skanuj (){
        List<String> pliki = new ArrayList<>();

       File directoryPath = new File(System.getProperty("user.dir")+"\\src\\zad2\\Klasy");
       File fileslist[] = directoryPath.listFiles();

        for (File file :fileslist
             ) {
            int pos_of_dot = file.getName().lastIndexOf(".");

            pliki.add(file.getName().substring(0,pos_of_dot));
        }


        return pliki;
    }

}
