import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VerkkokauppaIO {
    public void kirjoitaAsiakkaat(String tiedostonimi, ArrayList<Asiakas> asiakkaat) throws IOException {
        try (PrintWriter kirjoittaja = new PrintWriter(new FileWriter(tiedostonimi))) {
            for (Asiakas asiakas : asiakkaat) {
                if (asiakas instanceof KantaAsiakas) {
                    KantaAsiakas kantaasiakas = (KantaAsiakas) asiakas;
                    kirjoittaja.println(kantaasiakas.getNimi() + ";" + kantaasiakas.getOsoite() + ";" + kantaasiakas.getAlennusprosentti());
                } else {
                    kirjoittaja.println(asiakas.getNimi() + ";" + asiakas.getOsoite());
                }
            }
        }
    }

    public ArrayList<Asiakas> lueAsiakkaat(String tiedostonimi) throws IOException {
        ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();
        try (Scanner lukija = new Scanner(new File(tiedostonimi))) {
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                String[] tiedot = rivi.split(";");
                if (tiedot.length == 3) {
                    String nimi = tiedot[0];
                    String osoite = tiedot[1];
                    double alennusProsentit = Double.parseDouble(tiedot[2]);
                    asiakkaat.add(new KantaAsiakas(nimi, osoite, alennusProsentit));
                } else if (tiedot.length == 2) {
                    String nimi = tiedot[0];
                    String osoite = tiedot[1];
                    asiakkaat.add(new Asiakas(nimi, osoite));
                }
            }
        }
        return asiakkaat;
    }
}