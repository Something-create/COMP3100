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
        if(alreadySearched(servers[0].serverType) == 0){
            findServerAmount(servers[0].serverType);
        }
    }

    public void findServerAmount(String s){ 
        print("Current " + s);
        Integer amount = 0;
        for(int i = 0; i < servers.length-1; i++){
            if(s.equals(servers[i].serverType)){
                print("Current " + s);
                amount++;
            }else{
                break;
            }      
        }
        print("amount of " + s + "IS "+ amount);
        addToSearched(s, amount);
    }

        //add the amount of servers to a hashmap 
    public void addToSearched(String s, Integer i){
        print("addtoosearch " + s);
        serverAmount.put(s, i);
    }
    
    public int alreadySearched(String s){   
        print("AlreadySearched " + s);
        if(serverAmount.get(s) == null)
            return 0;
        return serverAmount.get(s);
    } 

    public int currentCount(String s){
        return 0;
    }

    public String WhichServer(String s){
        int curServerMax = alreadySearched(s);
        int curPosition = curPos.get(s);
        int newPos = 0;

        if(alreadySearched(s) == 0){//add value
            findServerAmount(s);
        }
        if(curPos.get(s) == null){//add value
            curPos.put(s, -1);
        }
      
        if(curPosition == curServerMax -1){//reset the curPosition
            curPos.replace(s, curPosition, newPos);
        }else{ //add one to curPOsiton 
            newPos = curPosition + 1;    
            curPos.replace(s, curPosition, newPos);
        }
        print("whichserver " + s);
        return " " + s + " " + newPos;
    }
    
    static void print(Object o){
        System.out.println(o);
     }

    String MSG_SCHD(int i){   
        //
        return ("SCHD " + WhichServer(servers[0].serverType));
      //  + i + " "+  servers[0].serverType + " " + 1 + "\n") ; ("SCHD "+ i + " "+  servers[0].serverType + " " + 1 +"\n");       
    }

    static void printMAP(){
        serverAmount.forEach((key,value)-> {
            print("value within serverAmount of "+ key + " IS " + value);
        });
    }
}

/**
 *         serverAmount.forEach((key,value)-> {
            print("value within serverAmount of "+ key + " IS " + value);
        });
    Problem with which server
 */