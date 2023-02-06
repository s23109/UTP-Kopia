package zad1;

public class StringTask implements Runnable{

    String input;
    volatile String output="";
    int amount ;
    volatile int pom = 0 ;
    Thread zadanie ;
    boolean was_interuppted = false;

    public StringTask(String input, int amount) {
        this.input = input;
        this.amount = amount;

        zadanie = new Thread(()->{
            run();
        });
    }

    @Override
    public void run() {

        while (pom != amount && !zadanie.isInterrupted()){
            output = new String(output + input);
            pom++;
        }

    }

    public String getResult(){
       return output;
    }

    public TaskState getState(){
        //zwracającą stan zadania
        if (zadanie.isAlive()) return TaskState.RUNNING;    //jak dziala
        if (!zadanie.isAlive() && was_interuppted) return TaskState.ABORTED;
        if (!zadanie.isAlive() && getResult().length() == amount) return  TaskState.READY;
        return  TaskState.CREATED;
    }

    public void start(){
        //uruchamiającą zadanie w odrębnym watku
        zadanie.start();
    }

    public void abort(){
        //przerywającą wykonanie kodzu zadania i działanie watku
        zadanie.interrupt();
        was_interuppted = true;
    }

    public boolean isDone(){
        //zwracająca true, jeśli wykonanie zadania się zakończyło normalnie lub przez przerwanie, false w przeciwnym razie
        if (zadanie.isAlive()) return  false; // jak jeszcze dziala to nie
        if (!zadanie.isAlive() && was_interuppted) return true;  // jak zostal przerwany to true
        if (!zadanie.isAlive() && getResult().length() == amount) return true;

        return false;
    }


}
