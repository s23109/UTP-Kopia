package zad1;

import java.awt.*;
import java.util.concurrent.Future;

public class Item {


    public Future fut;
    public Task task;
    public Button button;
    public String name;
    public Status st = Status.notStarted;



    enum Status{
        notStarted,
        running,
        suspended,
        canceled,
        finished,
        killed
    }
}



