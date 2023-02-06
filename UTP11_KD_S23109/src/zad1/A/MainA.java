package zad1.A;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainA {

    public static void main(String[] args) {
        System.out.println("Podpkt A");
        new Thread(()->{
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Koniec programu");
            System.exit(1);

        }).start();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        BufforA buffor = new BufforA(10);
        ProducerA producerA = new ProducerA(buffor);
        ConsumerA consumerA = new ConsumerA(buffor);

        //start buff contains
        buffor.print_all_in_buffor();
        executor.execute(producerA);
        executor.execute(consumerA);


    }

}
