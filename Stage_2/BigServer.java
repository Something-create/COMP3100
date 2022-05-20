public class BigServer {
    
    int bigNumb;
    JobState[] jobS;
    JobState[] newthing;
    int NumJobs, amt;

    public BigServer(JobState[] js, int amount){
        jobS = js;
        NumJobs = amount; 
        newthing = new JobState[amount];

    }

    public JobState[] getBig(){
        for(int i = 0; i < NumJobs; i++){
            
            if(jobS[i].core == jobS[i+1].core){
                
            }else if(jobS[i].core < jobS[i+1].core){
               
            }else{
                
            }
        }

        return null;
    }


   // private int JCompare(int i, int in){

 //  }

}
