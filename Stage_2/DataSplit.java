public class DataSplit {
    
    String Type = new String();
    int nRecs;
    int RecLen;

    public DataSplit(String str){
        String[] Sbreak = new String[4];
        
        Sbreak = str.split(" ");

        Type = Sbreak[0];
        nRecs = changeSTR(Sbreak[1]);
        RecLen = changeSTR(Sbreak[2]);

    }


    private int changeSTR(String str){
        return Integer.parseInt(str);
    }

    public int getnRecs(){
        return nRecs;
    }

    public int getRecLen(){
        return RecLen;
    }


}
