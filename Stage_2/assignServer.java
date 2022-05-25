import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.net.*;

public class assignServer {

    static DataOutputStream dout;
    static Map<String, Integer> serverAmount = new HashMap<String, Integer>();
    static Map<String, Integer> curPos = new HashMap<String, Integer>();
    JobState[] servers;

    public assignServer(JobState[] js){
        servers = js;
    }

    public void findServerAmount(){
        String Current = servers[0].serverType;
        Integer amount = 0;
        for(int i = 0; i < servers.length; i++){
                if(Current.equals(servers[i].serverType)){
                    amount++;
                }else{
                    addToSearched(Current, amount);
                    Current = servers[i].serverType;
                    amount = 0;
                }
        }
    }

        //add the amount of servers to a hashmap 
    public void addToSearched(String s, Integer i){
        serverAmount.put(s, i);
    }
    
    public int alreadySearched(String s){   
        if(serverAmount.get(s) == null)
            return 0;
        return serverAmount.get(s);
    } 

    public int currentCount(String s){
        return 0;
    }

    public String WhichServer(String s){

        if(serverAmount.get(s) == null){
            curPos.put(s, 1);
        }
        int cur = curPos.get(s);
        curPos.replace(s, cur, cur++);
        return servers[0].serverType;
    }
    
    void MSG_SCHD(int i){
        try{
            dout.write(("SCHD "+ i + " "+  WhichServer(servers[0].serverType) + " " + curPos.get(servers[0].serverType) +"\n").getBytes());
            dout.flush();
        }catch(Exception e){
         System.out.println(e);
         }
     }


}


/**
 * 
 *             if(!((dnsJobs[0].state).equals("inactive")) || !((dnsJobs[0].state).equals("idle"))){
               serverCount++;
            }

           int count = 0;
            if(dnsJobs[serverCount].alreadySearched(dnsJobs[serverCount].serverType) == 0){
               for(int i= 0; i <data.nRecs; i++){
                  if((dnsJobs[i].state).equals(dnsJobs[i+1].state)){
                     count++;
                  }
               }
            }
 */