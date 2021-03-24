public class Part1 {
    public String findSimpleGene(String dna)
    {
        int atgIndex=dna.indexOf("ATG");
        String s = "";
        if(atgIndex==-1)
            return s;
        int taaIndex=dna.indexOf("TAA");
        if(taaIndex==-1)
            return s;
        String subString=dna.substring(atgIndex,taaIndex+3);
        if(subString.length()%3==0)
            return subString;
        return s;

    }
    public void testSimpleGene()
    {
        String [] dnaList={"AFSRTAAUHEF","ATGSDFEAF","EUFSYSIFSHEFU","ATGFDERTAAEAF","ATGFSFGRTTAA"};
        for(String i : dnaList)
            System.out.println(findSimpleGene(i));
    }
    public static void main(String[] args)
    {
        Part1 p=new Part1();
        p.testSimpleGene();
    }
}
