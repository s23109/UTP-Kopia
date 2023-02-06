package zad1.A;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufforA {
    int buffor_size;
    Queue<Integer> lista_liczb;

    final Lock lock = new ReentrantLock();
    final Condition not_full = lock.newCondition();
    final Condition not_empty = lock.newCondition();


    public BufforA(int buffor_size) {

        this.buffor_size = buffor_size;
        lista_liczb= new LinkedList<>();
        System.out.println("Buffor A uzywany");
    }

    public void print_all_in_buffor(){
        System.out.print("Liczby w bufforze: ");
        lista_liczb.stream().forEach(a->{
            System.out.print(a+" , ");
        });
        System.out.println("\nZajete : " + lista_liczb.size() + " / " + buffor_size);
    }


    public void put (int liczba){
        lock.lock();

        try {

            while (lista_liczb.size()==buffor_size){
                System.out.println("Buffor pelen , czekam na zwolnienie miejsca");
                not_full.await();
            }


            System.out.println("Producer putting " + liczba + " in");
            //czekaj na to że nie full , await
            // signal - zmien że nie jest już pełny
            lista_liczb.add(liczba);
            print_all_in_buffor();
            not_empty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

    public int get(){
        lock.lock();

        try {

            while (lista_liczb.size()==0){
                System.out.println("Bufor pusty, czekam na dodanie elementu ");
                not_empty.await();
            }



            int liczba;
            liczba=lista_liczb.remove();
            System.out.println("Consumer getting " + liczba + " from buffor");
            print_all_in_buffor();
            not_full.signal();
            return liczba;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    return 0;
    }


}
