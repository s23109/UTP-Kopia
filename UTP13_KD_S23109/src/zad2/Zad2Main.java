package zad2;


import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Zad2Main {
    public static void main(String[] args) {
        List<String> klasy = new ArrayList<>();
     System.out.println("Folder with classes: "+System.getProperty("user.dir")+"\\src\\zad2\\Klasy\n");

     try {
            Class c = Class.forName("Tir");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        klasy=Scan_for_classes.skanuj();
        int ile_klas = klasy.size();
        System.out.println("Wykryto "+ile_klas+" klas/y:");


        for (String a:klasy
             ) {
            System.out.println(a);
        }
        System.out.println("\nBeginning:");

        for (String klasa: klasy
             ) {
            try {
                Class uniwersal = Class.forName(klasa);
                Class nadklasa_uniwersal=uniwersal.getSuperclass();
                System.out.println("=============================================\nCurrent tested class: ,,"+uniwersal.getName()+"''");

                //a)
                System.out.print("Nadklasy klasy "+uniwersal.getSimpleName()+" : ");
                while (true)
                {
                    System.out.print(nadklasa_uniwersal.getSimpleName());
                    if (nadklasa_uniwersal.getSimpleName().equals("Object")) break;
                    else System.out.print(" -> ");
                    nadklasa_uniwersal=nadklasa_uniwersal.getSuperclass();

                }
                //b)
                System.out.println("\nKonstruktory:");
                Constructor<?>[] konstruktory =  uniwersal.getDeclaredConstructors();
                //konst w tej klasie więc declared, min 1 parametr
                for (Constructor constr :konstruktory
                     ) {
                    // ile parametrów
                    // System.out.println(constr.getParameterCount());
                    //  System.out.print(a);
                    if (constr.getParameterCount()>=1){
                        //ma min 1 par
                        System.out.println(constr.toString());
                    }

                    //System.out.println(constr.getTypeParameters()+" "+constr.getName());
                }
                //c)
                System.out.println("Nieprywatne metody:");
                //wszystkich nie-prywatnych metodach zadeklarowanych w tej klasie
                Method[] methods = uniwersal.getDeclaredMethods();
                for (Method metoda: methods
                     ) {

                    if (!metoda.toString().split(" ")[0].equals("private")){
                        System.out.println(metoda.toString());
                    }

                }
                //d)
                System.out.println("Publiczne pola zadeklarowane w tej klasie:");
                Field[] obiekty =  uniwersal.getDeclaredFields();

                for (Field obiekt: obiekty
                     ) {
                    if (obiekt.toString().split(" ")[0].equals("public")){
                        System.out.println(obiekt.toString());
                    }
                }
                //e)
                System.out.println("Pola z bezpośredniej nadklasy które są tu dostępne:");
                nadklasa_uniwersal=uniwersal.getSuperclass();
                Field[] nadklasa_obiekty = nadklasa_uniwersal.getDeclaredFields(); //bez dziedziczonych
                obiekty = uniwersal.getFields(); // ma dziedziczone

                for (Field obiekt :obiekty
                     ) {
                    if (Arrays.asList(nadklasa_obiekty).contains(obiekt)){
                        System.out.println(obiekt.toString());
                    }
                }


                System.out.println("Testing for class: ,,"+uniwersal.getName()+"'' has ended");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        System.out.println("=============================================");
        int ktora_wylosowana = (int) (Math.random()*(ile_klas));
        System.out.println("Klasa wylosowana: "+ ktora_wylosowana + " : "+klasy.get(ktora_wylosowana));
        try {
            Class klasa = Class.forName(klasy.get(ktora_wylosowana));
            Constructor constructor = klasa.getConstructor(String.class);
            Object object = constructor.newInstance("Sth");
            Method method = klasa.getMethod("wypisz",String.class);
            Object fn = method.invoke(object,"test");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
