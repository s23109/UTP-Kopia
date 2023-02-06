/**
 *
 *  @author Kachniarz Dawid S23109
 *
 */

package zad1;


public class Purchase {
    private String id;
    private String nazwisko;
    private String imie;
    private String produkt;
    private double ilosc;
    private double cena;
    private String calosc;


    public Purchase (String linia){
        //id_klienta; nazwisko i imię; nazwa_towaru;cena;zakupiona_ilość
        String[] podziel=linia.split(";");
        this.calosc=linia;
        this.id=podziel[0];

        String[] imienazw = podziel[1].split(" ");
        this.nazwisko=imienazw[0];
        this.imie=imienazw[1];

        this.produkt=podziel[2];
        this.cena=Double.parseDouble(podziel[3]);
        this.ilosc=Double.parseDouble(podziel[4]);

    }

    public double koszt_calosci(){
        return cena*ilosc;
    }




    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getCalosc(boolean czy_z_kosztem) {
        if (!czy_z_kosztem) return calosc;
        return (calosc+" (koszt: "+koszt_calosci()+")");

    }

    public String getCalosc() {
       return getCalosc(false);
    }

    public void setCalosc(String calosc) {
        this.calosc = calosc;
    }



}
