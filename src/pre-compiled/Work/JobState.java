public class JobState implements Comparable<JobState> {
    
    String serverType = new String();
    int serverID;
    String state = new String();
    int curStartTime;
    int core;
    int memory;
    int disk;
    int wJobs;
    int rJobs;

    

    public JobState(String str){

        String[] sBreak = new String[10];

        sBreak = str.split(" ");

        serverType = sBreak[0];
        serverID = changeSTR(sBreak[1]);
        state = sBreak[2];
        curStartTime = changeSTR(sBreak[3]);
        core = changeSTR(sBreak[4]);
        memory = changeSTR(sBreak[5]);
        disk = changeSTR(sBreak[6]);
        wJobs = changeSTR(sBreak[7]);
        rJobs = changeSTR(sBreak[8]);  
    }

    public String getserverType(){
        return serverType;
    }

    public int getserverID(){
        return serverID;
    }

    public String getstate(){
        return state;
    }

    public int getcurStartTime(){
        return curStartTime;
    }
    
    public int getcore(){
        return core;
    }

    public int getcmemory(){
        return memory;
    }

    public int getdisk(){
        return disk;
    }

    public int getWjobs(){
        return wJobs;
    }

    public int getrJobs(){
        return rJobs;
    }

    @Override
    public int compareTo(JobState js){
        if(this.core == js.core){
            return 0;
        }else if(this.core < js.core){
            return 1;
        }else{
            return 0;
        }
    }





    private int changeSTR(String str){
        return Integer.parseInt(str);
    }

}

