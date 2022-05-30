import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class assignServer {

    /**
     * 
     * This class decides what sever to put the job in
     * 
     */
    static DataOutputStream dout;
    static Map<String, Integer> serverAmount = new HashMap<String, Integer>();
    static Map<String, Integer> curPos = new HashMap<String, Integer>();
    JobState[] servers;

    public assignServer(JobState[] js){
        servers = js;
        if(alreadySearched(servers[0].serverType) == 0){
            findServerAmount(servers[0].serverType);
        }
    }

    //checks the amount of server type there are
    public void findServerAmount(String s){ 
        Integer amount = 0;
        for(int i = 0; i < servers.length-1; i++){
            if(s.equals(servers[i].serverType)){
                amount++;
            }else{
                break;
            }      
        }
        addToSearched(s, amount);
    }

        //add the amount of servers to a hashmap 
    public void addToSearched(String s, Integer i){
        serverAmount.put(s, i);
    }
    
    //checks if the server type has been searched
    public int alreadySearched(String s){   
        if(serverAmount.get(s) == null)
            return 0;
        return serverAmount.get(s);
    } 

    //the conmpare function
    public String WhichServer(String s){

        if(serverAmount.get(s) == null){//add value
            findServerAmount(s);
        }
        if(curPos.get(s) == null){//add value
            curPos.put(s, -1);
        }

        int curServerMax = alreadySearched(s);
        int curPosition = curPos.get(s);
        int newPos = 0;
      
        if(curPosition >= curServerMax ){//reset the curPosition
            curPos.replace(s, curPosition, newPos);
        }else{ //add one to curPOsiton 
            newPos = curPosition + 1;    
            curPos.replace(s, curPosition, newPos);
        }

        return (" " + s + " " + newPos + "\n");
    }

    //this function sends back the server with the command SCHD
    String MSG_SCHD(int i){   
        return ("SCHD " + i + WhichServer(servers[0].serverType));
    }
}
