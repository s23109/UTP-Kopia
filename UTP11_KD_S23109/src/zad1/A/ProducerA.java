package zad1.A;

public class ProducerA implements Runnable{

    BufforA bufforA ;




    public ProducerA(Object buffor) {

            this.bufforA= (BufforA) buffor;
            System.out.println("Producer utworzony");


    }

    @Override
    public void run() {
        int random_number=0;
        while (true){
            random_number= (int) (Math.random()*100);
            System.out.println("Producer wants to put " + random_number + " into buffor");
            bufforA.put(random_number);

            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
