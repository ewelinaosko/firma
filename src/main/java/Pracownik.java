public class Pracownik {

    private String imie;
    private String nazwisko;
    private Double wynagrodzenie;

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public Pracownik(String imie, String nazwisko, Double wynagrodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wynagrodzenie = wynagrodzenie;
    }
}
