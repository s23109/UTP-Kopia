package zad1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Test {

    private static int numOfTasks = 5;
    private static List<Item> tasks;
    private static ExecutorService executor;
    private static UI ui;

    private Object flag = new Object();

    public static void main(String[] args) {
        Test test = new Test();
    }

    public Test() {
        ui = new UI(this);
        tasks = new ArrayList<>();
        executor = Executors.newFixedThreadPool(numOfTasks+1);

        Runnable runnableTask = () -> {
            while (true){

                synchronized (this) {
                    Iterator<Item> i = tasks.iterator();
                    while (i.hasNext()) {
                        Item item = i.next();

                        if (item.st == Item.Status.finished) {
                            item.st = Item.Status.killed;
                            ui.appendText("\n" + item.name + " has just finished");
                            item.button.setEnabled(false);
                            item.button.setLabel(item.name + " Done");

                            new Thread(() -> {
                                try {
                                    Thread.sleep(5000);
                                    ui.removeComp(item.button);
                                    synchronized (this){
                                        tasks.remove(item);
                                    }
                                    System.out.println();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    }
                }
            }
        };
        executor.execute(runnableTask);
    }

    public void createNewTask(Button s) {

        Item tmp = new Item();
        tmp.name = "T"+ (countTasks()+1);

        tmp.button = s;
        tmp.button.setLabel(tmp.name);
        tmp.button.addActionListener(a -> {

            switch (tmp.st){
                case notStarted, suspended -> {
                    tmp.fut = startTask(tmp.task);
                    tmp.button.setLabel(tmp.name + " Suspend");
                    tmp.st = Item.Status.running;
                }
                case running -> {
                    suspendTask(tmp.fut);
                    tmp.button.setLabel(tmp.name + " Continue");
                    tmp.st = Item.Status.suspended;
                    ui.appendText("\n" + tmp.name + " suspended");
                }
            }
            ui.repaint();
        });

        tmp.task = (new Task( 10 * (int) (Math.random()*100), ui, tmp.name));
        tmp.task.setItem(tmp);

        synchronized (this){
            tasks.add(tmp);
        }

    }

    public Future startTask(Task s){
        return executor.submit(s);
    }

    public void suspendTask(Future s){
        try {
            s.cancel(true);
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void cancel_specific(Item item){

        synchronized (this){
            try{
                item.fut.cancel(true);
            }
            catch (Exception e){
                //
            }
            item.st = Item.Status.canceled;
            item.button.setEnabled(false);
            item.button.setLabel(item.name + " cancelled");
            ui.repaint();
            ui.appendText("\n" + item.name + " cancelled");
        }

    }

    public void cancelAll(){
        synchronized (this){

            for (Item item: tasks) {
                    if (item!=null){

                        try {
                            item.fut.cancel(true);
                        }
                        catch (Exception e ){
                        //
                        }
                        finally {
                            item.st = Item.Status.canceled;
                            item.button.setEnabled(false);
                            item.button.setLabel(item.name + " cancelled");
                            ui.repaint();
                            ui.appendText("\n" + item.name + " cancelled");
                        }

            }
            }

            ui.appendText("\nProgram Stopped!");
        }
    }



    public int countTasks(){
        synchronized (this){
            return tasks.size();
        }
    }
    public int maxTasks(){
        return numOfTasks;
    }
}
