package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator<T> {

    private List<T> lista;
    public ListCreator(List<T> list) { this.lista=list;}

    static public <T> ListCreator<T> collectFrom (List<T> list){
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Function<T,Boolean> sel){

        List<T> nowa = new ArrayList<>();

        for (T i : lista){
            if (sel.apply(i)){
                nowa.add(i);
            }
        }

        this.lista=nowa;
        return this;
    }

    public <S> List<S> mapEvery ( Function<T,S> map){

        List<S> zmapowane = new ArrayList<S>();

        for (T i : this.lista){
            zmapowane.add(map.apply(i));
        }

        return zmapowane;

    }

}
