import java.util.List;

public class Firma {
    private String nazwa;
    private String szef;
    private Double wynagrodzenieSzefa;
    private List<Pracownik> pracownicy;

    public String getNazwa() {
        return nazwa;
    }

    public String getSzef() {
        return szef;
    }

    public Double getWynagrodzenieSzefa() {
        return wynagrodzenieSzefa;
    }

    public List<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public Firma(String nazwa, String szef, Double wynagrodzenieSzefa, List<Pracownik> pracownicy) {
        this.nazwa = nazwa;
        this.szef = szef;
        this.wynagrodzenieSzefa = wynagrodzenieSzefa;
        this.pracownicy = pracownicy;
    }



}
