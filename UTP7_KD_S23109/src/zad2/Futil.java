package zad2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Futil {




    public static void processDir (String dirname , String resultFileName){
    List<String> linie = new ArrayList<>();

        try {
            Files.walk(Paths.get(dirname)).filter(Files::isRegularFile ).forEach(path -> {
                //dla każdego pliku


                try {
                    linie.add(String.valueOf(Files.readAllLines(path, Charset.forName("Cp1250"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }


            });


                Files.write(Paths.get(resultFileName),linie ,StandardCharsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
