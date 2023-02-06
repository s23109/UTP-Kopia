package zad1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class XList<T> extends ArrayList<T> {

    //konst
    public XList(T... elementy){
        Collections.addAll(this,elementy);
    }

    public XList(Collection<T> kolekcja){
        super(kolekcja);
    }



    // /konst

    // of

    public static <U> XList <U> of (U... elementy){
        return new XList<U>(elementy);
    }

    public static <U> XList <U> of (Collection<U> kolekcja){
        return new XList<U>(kolekcja);
    }

    // /of

    // charsof + tokensof
    public static XList<String> charsOf(String s){
        List<String> litery = new ArrayList<>();

        for (char i: s.toCharArray()
             ) {
            litery.add(String.valueOf(i));
        }


        return XList.of(litery);
    }

    public static XList<String> tokensOf(String s,String regex){
    return XList.of(s.split(regex));
    }

    public static XList<String> tokensOf(String s){
        return XList.of(s.split(" "));
    }

    // /charsof + tokensof

    // union
    public XList<T> union(Collection<T> kolekcja){
        XList xList=new XList(this);
        xList.addAll(XList.of(kolekcja));
        return xList;
    }

    public XList<T> union(T... elem){
        return this.union(XList.of(elem));
    }

    // /union

    //diff
    public XList<T> diff(Collection kolekcja){
        XList<T> xList = new XList<T>();

        for (T e:this
             ) {
            if (!kolekcja.contains(e)){
                xList.add(e);
            }

        }
        return xList;
    }

    public XList<T> diff (T... elem){
        return this.diff(XList.of(elem));
    }
    // / diff



    //unique

    public boolean unik_ele(XList xList, Object o){
        int ile=0;
        for (int i=0;i<xList.size();i++){
            if (xList.get(i)==o ){
                ile+=1;
            }
        }
        //zmieniona dla unique , oryginał ile==1
        if (ile==0) return true;
        return false;
    }

    public XList<T> uniquev2 (){
        XList xList = new XList();
        for (T e:this
             ) {
            if (unik_ele(this,e)){
                xList.add(e);
            }
        }
        return xList;
    }

    public XList<T> unique(){
        XList xList = new XList();

        for (T e:this
             ) {
            if (unik_ele(xList,e))
                xList.add(e);

        }
        return xList;
    }
    // / unique

    // combine

    //temp bo wtf


    public XList<XList<String>> combine() {
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }


    // nie działa
/*
    public String slowo(XList a){
        String pom="",pom2="";
        for (Object e:a
             ) {
            pom+=e;
        }

        String nie="[ ]{} ,./t";
        boolean pomo=false;
        for (int i=0;i<pom.length();i++){
             for (int j=0;j<nie.length();j++){
                 if (nie.charAt(j)==pom.charAt(i)) pomo=true;
             }

             if (pomo){
                 pom2+=pom.charAt(i);
             }
             pomo=false;
        }

        return pom2;
    }

    public XList<T> do_combine (int[] pozycje){
        XList slowo = new XList();
        // slowo.add((XList.of(this.get(wyraz))).get(pozycje[wyraz]));

        for (int i=0;i<pozycje.length;i++){
            slowo.add(XList.of(this.get(i)).get(pozycje[i]));
        }

        return slowo;
    }

    public XList<XList<T>> combine(){
        XList<XList<T>> xList = new XList();
        int pom_ile_komb=1;
        int [] iteracje = new int[this.size()];
        int [] dlugosci = new int[this.size()];
        String pom;
        for (int i=0;i<this.size();i++){
            pom=slowo(XList.of( this.get(i)));
            pom_ile_komb*=pom.length();
            dlugosci[i]=pom.length();;
            iteracje[i]=0;
            System.out.println(pom.length());
        }
        System.out.println("ile komb "+pom_ile_komb);

        int iterator=0;

        while (iterator<pom_ile_komb){

            xList.add(this.do_combine(iteracje));

            //powiększ pierwszy zawsze
            iteracje[0]++;

            for (int i=0;i<(this.size()-1);i++){
                if (iteracje[i]==dlugosci[i]){
                    //wywala dlugosc slowa
                    iteracje[i+1]++;
                    iteracje[i]=0;
                 //   System.out.println(iteracje[i]);
                }
            }
           // System.out.println("asd");
            iterator++;
        }



        return  xList;
    }

*/



    // / combine

    //collect

    public <U> XList<U> collect(Function<T,U> fun){
        XList<U> xList = new XList<U>();

        for (T e:this
             ) {
            xList.add(fun.apply(e));
        }
        return xList;
    }

    // / collect

    // join

    public String join(String sep){
     return this.stream().map(Object::toString).collect(Collectors.joining(sep));
    }

    public String join(){
        return join("");
    }

    // / join

    // forEachWithIndex

    public void forEachWithIndex(BiConsumer<T,Integer> fun){

        for (int i=0;i<this.size();i++){

            fun.accept(this.get(i),i);


        }

    }

    // / forEachWithIndex
}
