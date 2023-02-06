package zad1.B;

public class ProducerB implements Runnable{
    BufforB bufforB ;

    public ProducerB(BufforB bufforB) {
        this.bufforB = bufforB;
    }

    @Override
    public void run() {
        int random_number=0;
        while (true){
            random_number= (int) (Math.random()*100);
            System.out.println("Producer wants to put " + random_number + " into buffor");
            bufforB.put(random_number);

            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
