package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class AccountLimitator implements VetoableChangeListener {
    //forbid if balance after >limit
    double limit;

    public AccountLimitator(int limit) {
        this.limit = limit;
    }


    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ((double)evt.getNewValue()<limit){
            throw new PropertyVetoException("Unacceptable value change:"+evt.getNewValue(),evt);
        }
    }
}
