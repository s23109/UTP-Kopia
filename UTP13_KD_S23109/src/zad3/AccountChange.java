package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {
    //inform if balance <0
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ((double)evt.getNewValue()<0){
            System.out.println("New Value below 0! :" + evt.getNewValue());
        }
    }
}
