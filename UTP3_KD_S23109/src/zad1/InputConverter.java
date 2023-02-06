package zad1;

import java.util.function.Function;

public class InputConverter<T> {

 T poczatek;

    public InputConverter(T poczatek) {
        this.poczatek = poczatek;
    }
        // ... jako nieznana ilość arg - przykład Arrays.asList
    public <T> T convertBy(Function... funkcje){
        Object wej=poczatek;
        Object wyj=null;

        for (Function i: funkcje)
              {
            wyj=i.apply(wej);
            wej=wyj;
        }
        return (T) wyj;
    };

}
