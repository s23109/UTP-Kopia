package zad1.B;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainB {

    public static void main(String[] args) {
      System.out.println("Podpkt B");
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
        BufforB buffor = new BufforB(10);
        ProducerB producerB = new ProducerB(buffor);
        ConsumerB consumerB = new ConsumerB(buffor);

        //start buff contains
        buffor.print_all_in_buffor();
        executor.execute(producerB);
        executor.execute(consumerB);
    }

}
