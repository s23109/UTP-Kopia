package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T>{

    T wartosc;

    public Maybe(T wartosc) {
        this.wartosc = wartosc;
    }

    public static <S>Maybe<S> of (S wartosc){return new Maybe<>(wartosc);}

    public void ifPresent (Consumer cons){
        if (this.wartosc!=null){
            cons.accept(wartosc);
        }
    }

    public <S> Maybe<S> map (Function<T,S> func){
        if (wartosc!=null) {
            T doret = (T) func.apply(wartosc);
            return new Maybe(doret);
        }
        else return new Maybe<>(null);
    }

    public T get(){
        if (wartosc!=null){
            return this.wartosc;
        }
        else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent(){

        if (wartosc!=null) return true;
        else return false;

    }

    public T orElse (T defVal){
        if (wartosc!=null) return this.wartosc;
        else return defVal;
    }

    public Maybe<T> filter (Predicate<T> pred){
    if (this.wartosc==null || pred.test(this.wartosc)){
        return this;
    }
    else  return new Maybe<>(null);
    }

    @Override
    public String toString() {
        if (wartosc!= null){
          return   "Maybe has value "+wartosc;
        }
        return "Maybe is empty";
    }
}
