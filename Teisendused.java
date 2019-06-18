// DNA transleerimise koodonite tabel
// https://en.wikipedia.org/w/index.php?title=DNA_codon_table&oldid=885476798

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Teisendused
{
    public boolean kas_nukleotiide_on_muudetud = false;
    public List<String> koodonid;

    public String uus_tekst = "";

    Teisendused()
    {
        koodonid = new ArrayList<>();
    }

    List<String> anna_koodonid()
    {
        if( kas_nukleotiide_on_muudetud ) tekita_koodonid();
        return this.koodonid;
    }

    List<String> tekita_koodonid()
    {
        this.koodonid.clear();
        for(int i=0; i < this.uus_tekst.length() - 2; i+=3)
            this.koodonid.add(uus_tekst.substring(i + 0, i + 3));

        this.kas_nukleotiide_on_muudetud = false;
        return this.koodonid;
    }

    Stream anna_aminohapped() { return this.anna_koodonid().stream()
                                            .map(x -> x.toUpperCase())
                                            .map(x -> teisenda_aminohappeks(x));}
    public String transleeri()
    {
        StringBuilder str = new StringBuilder();

        anna_aminohapped().forEach(x -> str.append(x));

        return str.toString();
    }

    public String teisenda_valemiteks()
    {
        StringBuilder str = new StringBuilder();

        anna_aminohapped().forEach(x -> str.append( teisenda_valemiks(x.toString().charAt(0)) + "\n" ));

        return str.toString();
    }

    public String anna_statistika()
    {
        Map<Character, Long> sagedused = this.anna_koodonid().stream()
        .map(x -> x.toUpperCase())
        .map(x -> teisenda_aminohappeks(x))
        .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        sagedused.remove('\n');
        Stream < Map.Entry < Character,Long > > sorteeritud;
        
        sorteeritud = sagedused
        .entrySet()
        .stream()
        .sorted(Map.Entry
        .comparingByValue(  Comparator.reverseOrder()  ));

        StringBuilder str = new StringBuilder();
        sorteeritud.forEach(x -> str.append( x.toString() + "\n" ));

        return str.toString();
    }

// DNA transleerimise koodonite tabel
// https://en.wikipedia.org/w/index.php?title=DNA_codon_table&oldid=885476798

    public static char teisenda_aminohappeks(String koodon)
    {
        switch (koodon)
        {
            case "GCT": case "GCC": case "GCA": case "GCG":
            return 'A';
            case "CGT": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG":
            return 'R';
            case "AAT": case "AAC":
            return 'N';
            case "GAT": case "GAC":
            return 'D';
            case "TGT": case "TGC":
            return 'C';
            case "CAA": case "CAG":
            return 'Q';
            case "GAA": case "GAG":
            return 'E';
            case "GGT": case "GGC": case "GGA": case "GGG":
            return 'G';
            case "CAT": case "CAC":
            return 'H';
            case "ATT": case "ATC": case "ATA":
            return 'I';
            case "TTA": case "TTG": case "CTT": case "CTC": case "CTA": case "CTG":
            return 'L';
            case "AAA": case "AAG":
            return 'K';
            case "ATG":
                return 'M';
            case "TTT": case "TTC":
            return 'F';
            case "CCT": case "CCC": case "CCA": case "CCG":
            return 'P';
            case "TCT": case "TCC": case "TCA": case "TCG": case "AGT": case "AGC":
            return 'S';
            case "ACT": case "ACC": case "ACA": case "ACG":
            return 'T';
            case "TGG":
                return 'W';
            case "TAT": case "TAC":
            return 'Y';
            case "GTT": case "GTC": case "GTA": case "GTG":
            return 'V';
            case "TAA": case "TGA": case "TAG": // Stoppkoodonid.
            return '\n';

            default : return '?';
        }
    }
    public static String teisenda_valemiks(char aminohape)
    {
        switch (aminohape)
        {
            case 'A':
                return "CH3-CH(NH2)-COOH";
            case 'R':
                return "HN=C(NH2)-NH-(CH2)3-CH(NH2)-COOH";
            case 'N':
                return "H2N-CO-CH2-CH(NH2)-COOH";
            case 'D':
                return "HOOC-CH2-CH(NH2)-COOH";
            case 'C':
                return "HS-CH2-CH(NH2)-COOH";
            case 'Q':
                return "H2N-CO-(CH2)2-CH(NH2)-COOH";
            case 'E':
                return "HOOC-(CH2)2-CH(NH2)-COOH";
            case 'G':
                return "NH2-CH2-COOH";
            case 'H':
                return "NH-CH=N-CH=C-CH2-CH(NH2)-COOH";
            case 'I':
                return "CH3-CH2-CH(CH3)-CH(NH2)-COOH";
            case 'L':
                return "(CH3)2-CH-CH2-CH(NH2)-COOH";
            case 'K':
                return "H2N-(CH2)4-CH(NH2)-COOH";
            case 'M':
                return "CH3-S-(CH2)2-CH(NH2)-COOH";
            case 'F':
                return "Ph-CH2-CH(NH2)-COOH";
            case 'P':
                return "NH-(CH2)3-CH-COOH";
            case 'S':
                return "HO-CH2-CH(NH2)-COOH";
            case 'T':
                return "CH3-CH(OH)-CH(NH2)-COOH";
            case 'W':
                return "Ph-NH-CH=C-CH2-CH(NH2)-COOH";
            case 'Y':
                return "HO-Ph-CH2-CH(NH2)-COOH";
            case 'V':
                return "(CH3)2-CH-CH(NH2)-COOH";
            case '\n':
                //return "\n";
                return "";

            default:
                return "???";
        }
    }
}
