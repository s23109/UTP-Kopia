package zad1;

import java.util.concurrent.Callable;

public class Task implements Callable {

    private int sumLimit;
    private int currSum;
    private UI ui;
    private String task_name;
    private Item item;


    public Task(int sumLimit, UI ui, String task_name) {
        this.sumLimit = sumLimit;
        this.currSum = 0;
        this.ui = ui;
        this.task_name = task_name;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String call() throws Exception {
        while(currSum <= sumLimit){
            int rand = (int) (Math.random() * 100);
            currSum += rand;

            ui.appendText("\n" + task_name + " (limit = " + sumLimit + "): " + rand + ", sum = " + currSum);

            Thread.sleep((long) (1500*Math.random()));
        }

        item.st = Item.Status.finished;

        return "done";
    }
}
