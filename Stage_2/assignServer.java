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
        print("whichserver " + s);
        if(serverAmount.get(s) == null){
            curPos.put(s, 1);
        }
        int cur = curPos.get(s);
        curPos.replace(s, cur, cur++);
        return servers[0].serverType;
    }
    
    static void print(Object o){
        System.out.println(o);
     }

    String MSG_SCHD(int i){   
        //
        return ("SCHD "+ i + " "+  servers[0].serverType + " " + 1 + "\n") ; //("SCHD "+ i + " "+  servers[0].serverType + " " + 1 +"\n");       
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