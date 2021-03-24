public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb)
    {
        int index=stringb.indexOf(stringa);
        if(index==-1)
            return false;
        index=stringb.indexOf(stringa,index+1);
        if(index==-1)
            return false;
        return true;
    }
    public String lastPart(String stringa, String stringb)
    {
        int index=stringb.indexOf(stringa);
        if(index==-1)
            return stringb;
        else
            return stringb.substring(stringb.indexOf(stringa)+stringa.length());
    }
    public void testing()
    {
        System.out.println(twoOccurrences("hi","hi how are you> hi"));
        System.out.println(twoOccurrences("nice","not nice"));
        System.out.println(lastPart("hi","hihello"));
    }
    public static void main(String[] args)
    {
        Part3 p = new Part3();
        p.testing();
    }
}

