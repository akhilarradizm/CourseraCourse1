import java.util.Locale;

public class Part2 {
    public String findSimpleGene(String dna,int startCodon, int stopCodon) {
        dna=dna.substring(startCodon,stopCodon+1);
        int atgIndex = dna.toUpperCase().indexOf("ATG");
        String s = "";
        if (atgIndex == -1)
            return s;
        int taaIndex = dna.toUpperCase().indexOf("TAA");
        if (taaIndex == -1)
            return s;
        String subString = dna.substring(atgIndex, taaIndex + 3);
        if (subString.length() % 3 == 0)
            return subString;
        return s;

    }

    public void testSimpleGene() {
        String[] dnaList = {"AFSRTAAUHEF", "ATGSDFEAF", "atgctataa", "ATGFDERTAAEAF", "ATGFSFGRTTAA"};
        for (String i : dnaList)
            System.out.println(findSimpleGene(i,0,i.length()-1));
    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}
