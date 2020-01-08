import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
            System.out.println(new String(dataReaded));
        }
        catch (IOException e) {
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
 */