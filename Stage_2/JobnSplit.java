
public class JobnSplit{

    //This class breaks down the job type message from the server
    
    //types of outputs from sever
    String Type = new String();

    //When server returns jobn
    int submitTime = 0; 
    int jobID = 0;
    int estRuntime = 0;
    int core = 0;
    int memory = 0;
    int disk = 0; 

    //when server return JCPL 
    int endTime = 0;
    //jobID
    String ServerType = new String();
    int ServerID = 0;


    public JobnSplit(String s){
             
        //split string
         String[] sBreak = new String[10];
         sBreak = s.split(" ");
        

         Type = sBreak[0];

         if(is_JCPL()){
            endTime = changeSTR(sBreak[1]);
            jobID = changeSTR(sBreak[2]);
            ServerType = sBreak[3];
            ServerID = changeSTR(sBreak[4]);
         }else if(is_NONE()){
         }else{
            submitTime = changeSTR(sBreak[1]);
            jobID = changeSTR(sBreak[2]);
            estRuntime = changeSTR(sBreak[3]);
            core = changeSTR(sBreak[4]);
            memory = changeSTR(sBreak[5]);
            disk = changeSTR(sBreak[6]);
         }
    }

    private int changeSTR(String str){
        return Integer.parseInt(str);
    }

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

    //checks if it is a JCPL message
    public boolean is_JCPL(){
        if(Type.equals("JCPL"))
            return true;
        return false;
    }

    //Checks if it is a none message
    public boolean is_NONE(){
        if(Type.equals("NONE")){
            return true;
        }
        return false;

    }

}