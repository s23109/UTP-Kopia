package zad3;


import java.beans.*;

public class Account {

    double kasa;
    private VetoableChangeSupport veto = new VetoableChangeSupport(this);
    private PropertyChangeSupport chg = new PropertyChangeSupport(this);

    public Account(int  kasa) {
        this.kasa = kasa;
    }

    public Account() {
       new Account(0);
    }


    public void deposit(double amount) {
        double old_amount = this.kasa;
        this.kasa+=amount;
        chg.firePropertyChange("kasa",old_amount,kasa);

    }

    public void withdraw(double amount) throws PropertyVetoException {
        double old_amount = this.kasa;

        veto.fireVetoableChange("kasa",old_amount,kasa-amount);
        this.kasa-=amount;
        chg.firePropertyChange("kasa",old_amount,kasa);
    }

    public void transfer(Account target, double amount) throws PropertyVetoException {
        this.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(kasa);
    }

    public void addVetoableChangeListener(VetoableChangeListener l) {
        veto.addVetoableChangeListener(l);
    }

    public void removeVetoableChangeListener(VetoableChangeListener l) {
        veto.removeVetoableChangeListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        chg.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        chg.removePropertyChangeListener(l);
    }

}
