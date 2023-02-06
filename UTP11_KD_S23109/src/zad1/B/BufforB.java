package zad1.B;


import java.util.concurrent.ArrayBlockingQueue;

public class BufforB  {
    int buff_size;
    ArrayBlockingQueue<Integer> lista_liczb;
    public BufforB(int buff_size) {
        this.buff_size = buff_size;
        System.out.println("Buffor B uzywany");
        lista_liczb=new ArrayBlockingQueue<>(buff_size,true);

    }

    public void print_all_in_buffor() {
        System.out.print("Liczby w bufforze: ");

        lista_liczb.stream().forEach(a->{
            System.out.print(a+" , ");
        });
        System.out.println("\nZajete : " + lista_liczb.size() + " / " + buff_size);

    }

    public void put(int liczba){



        try {
            lista_liczb.put(liczba);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Status after put:");
        print_all_in_buffor();

    }


    public int get() {
        System.out.println("Status before take:");
        print_all_in_buffor();
        try {
            return  lista_liczb.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
