package zad1.B;

public class ConsumerB implements Runnable {
    BufforB bufforB;

    public ConsumerB(BufforB bufforB) {
        this.bufforB = bufforB;
    }


    @Override
    public void run() {
        int element;
        while (true){

            System.out.println("Consumer wants new element");
            element=bufforB.get();
            System.out.println("Consumer got new element: "+ element + " , processing");



            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
