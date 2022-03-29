public class JobnSplit{

    //types of strings

    String Type = new String();
    int submitTime = 0; 
    int jobID = 0;
    int estRuntime = 0;
    int core = 0;
    int memory = 0;
    int disk = 0; 

    public JobnSplit(String s){
             
        //split string
         String[] sBreak = new String[10];
         sBreak = s.split(" ");

         Type = sBreak[0];
         submitTime = changeSTR(sBreak[1]);
         jobID = changeSTR(sBreak[2]);
         estRuntime = changeSTR(sBreak[3]);
         core = changeSTR(sBreak[4]);
         memory = changeSTR(sBreak[5]);
         disk = changeSTR(sBreak[6]);
    }

    private int changeSTR(String str){
        return Integer.parseInt(str);
    }

    //get constructors 

    public int getsubmitTime(){
        return submitTime;
    }

    public int getjobID(){
        return jobID;
    }
    public int getestRuntime(){
        return estRuntime;
    }
    public int getcore(){
        return core;
    }
    public int getmemory(){
        return memory;
    }
    public int getdisk(){
        return disk;
    }



}