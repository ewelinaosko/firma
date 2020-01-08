import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static Path path = Paths.get("firma.txt");

    private static Firma stworzFirme() {
        List<Pracownik> pracownicy = new ArrayList<>();

        pracownicy.add(new Pracownik("Krzysztof", "Nowak", 123000D));
        pracownicy.add(new Pracownik("Wanda", "Kowalska", 23000D));
        pracownicy.add(new Pracownik("Adam", "Malysz", 323000D));

        return new Firma("Osram", "Msciwoj Kowalski", 99999990D, pracownicy);

    }

    public static void zapiszDoPliku() throws IOException {

        Firma firma = stworzFirme();

        Files.deleteIfExists(path);
        Files.createFile(path);
        String data = String.format("Firma %s%nSzef: %s; %s;%nPracownicy: %s%n",
                firma.getNazwa(), firma.getSzef(), firma.getWynagrodzenieSzefa(), firma.getPracownicy().size());
        Files.write(path, data.getBytes());
        for (Pracownik member : firma.getPracownicy()) {
            String dataPracownik = String.format("%s %s; %s;%n", member.getImie(), member.getNazwisko(), member.getWynagrodzenie());
            Files.write(path, dataPracownik.getBytes(), StandardOpenOption.APPEND);
        }

    }

    public static void odczytajZPliku() {

        try {
            byte[] dataReaded = Files.readAllBytes(path);
            String dataString = new String(dataReaded);
            //  System.out.println(dataString);
            String nazwaZPliku = dataString.split("\\s")[1];
            String szefZPliku = (dataString.split("\\s")[4] + " " + dataString.split("\\s")[5]).split(";")[0];
            String wynagrodzenieSzefaZPliku = dataString.split(";")[1].split("\\s")[1];

            List<String> pracownicyZPlikuStringLine = new ArrayList<>(Arrays.asList(dataString.split("\\n"))); //List.of(dataString.split("\\s"));

            List<Pracownik> pracownicyZPliku = new ArrayList<>();

            int linesCounter = 0;
            for (String element : pracownicyZPlikuStringLine) {
                linesCounter++;
             //   System.out.println(element);
                if (linesCounter > 3) {
                    String noweImie = element.split(" ")[0];
                    //System.out.println("!"+noweImie);
                    String noweNazwisko = element.split(" ")[1].split(";")[0];
                    //System.out.println("!"+noweNazwisko);
                    String noweWynagrodzenie = element.split(" ")[2].split(";")[0];
                   // System.out.println("!"+noweWynagrodzenie);
                    Pracownik nowyPracownik = new Pracownik(noweImie, noweNazwisko, Double.valueOf(noweWynagrodzenie));
                    pracownicyZPliku.add(nowyPracownik);
                }

            }

             Firma nowaFirma = new Firma(nazwaZPliku, szefZPliku, Double.valueOf(wynagrodzenieSzefaZPliku), pracownicyZPliku);
            System.out.printf("--- Sprawdzenie: ---%n%s%n%s%n%s%n%s%n", nowaFirma.getNazwa(), nowaFirma.getSzef(),nowaFirma.getWynagrodzenieSzefa(), nowaFirma.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        zapiszDoPliku();
        odczytajZPliku();

    }
}

/*
Zadanie 2. Firma

Etap i) Stwórz:
•klasę Pracownik zawierającą imię/nazwisko oraz wynagrodzenie
•klasę Firma zawierającą nazwę, szefa oraz listę pracowników

Etap ii)
Stwórz metodą zapisującą Firmę do pliku w następującym formacie:
Firma <Nazwa>
Szef: <Imię Nazwisko>; <Wynagrodzenie>;
Pracownicy: <Liczba>
<Imię Nazwisko>; <Wynagrodzenie>;
<Imię Nazwisko>; <Wynagrodzenie>;
<Imię Nazwisko>; <Wynagrodzenie>;
...

Etap iii)
Stwórz metodę odczytującą Firmę z pliku w formacie określonym w etapie ii).
stworzenie obiektu klasy Firma na podstawie tekstu wczytanego z pliku
 */