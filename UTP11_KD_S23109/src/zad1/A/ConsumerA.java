package zad1.A;

public class ConsumerA implements Runnable{

    BufforA bufforA ;

    public ConsumerA(BufforA bufforA) {
        this.bufforA = bufforA;
        System.out.println("Consumer utworzony");
    }



    @Override
    public void run() {
    int element;
        while (true){

            System.out.println("Consumer wants new element");
            element=bufforA.get();
            System.out.println("Consumer got new element: "+ element + " , processing");

            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }



}
