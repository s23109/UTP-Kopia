/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {
    private List<T> lista;

    public ListCreator (List<T> list){
        this.lista=list;
    }

    static public <T> ListCreator<T> collectFrom(List<T> lista) {
        return new ListCreator<>(lista);
    }

    public ListCreator<T> when(Selector<T> sel) {
     List <T> nowa = new ArrayList<>();

     for (T i: lista){
         if (sel.select(i)) {
             nowa.add(i);
         }

     }

     this.lista=nowa;
     return this;

    }

    public <S> List<S> mapEvery(Mapper<T,S> map) {
        List<S> zmapowane = new ArrayList<S>();

        for (T i : this.lista) {
        zmapowane.add(map.map(i));

        }
        return zmapowane;
    }
}