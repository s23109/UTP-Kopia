package zad1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Futil {

    public static void processDir(String dirname, String filename){
        try {
            Stream<String> stream = Files.walk(Paths.get(dirname))
                    .filter(Files::isRegularFile)
                    .flatMap(file -> {
                        //for each file
                        try {
                            return Files.lines(file, Charset.forName("Cp1250"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                            });

       Files.write(Paths.get(filename), (Iterable<String>) stream::iterator , StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}
