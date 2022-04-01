
import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;

public class MyClient {

   static DataOutputStream dout;
   static BufferedReader br; 

   
   public static void main(String[] args) {

      String str = new String();
     
      try{	
         Socket s=new Socket("127.0.0.1",50000);
         dout = new DataOutputStream(s.getOutputStream());
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // DataInputStream din = new DataInputStream(s.getInputStream());
             
         dout.write(("HELO\n").getBytes());
         dout.flush();
 
         str = br.readLine();
         PP(str);

         dout.write(("AUTH Sarthak\n").getBytes());
         dout.flush();
  
         str = br.readLine();
         PP(str);

         dout.write(("REDY\n").getBytes());
         dout.flush();

         str = br.readLine();
         PP(str);

         JobnSplit job;
         job = new JobnSplit(str);

         int k = job.core;
         dout.write(("GETS Capable "+ k + " " + job.getmemory()+ " " + job.getdisk() + " " +"\n").getBytes());
         dout.flush();

         str = br.readLine();
         PP(str);
         DataSplit data = new DataSplit(str);


         dout.write(("OK\n").getBytes());
         dout.flush();


         //Collects all the jobs that are avaibable
        JobState[] dnsJobs = new JobState[data.nRecs+1];

         for(int i = 0; i < data.nRecs; i++){ 
            str = br.readLine(); 
            dnsJobs[i] = new JobState(str);
            System.out.println(str);
         }  

         dout.write(("OK\n").getBytes());
         dout.flush();

         str = br.readLine();
         PP(str);

       // Search algorithm to find the largest
      
       /*
       try to find the server with the largest core
       then store the the serverID and the core amount 
       And the amount of servers that are there then keep
       sending jobs until the server count is meet then send
       them back to 0 and countiue untill it doesnt have more 
       jobs. 

       search algorithm 
       1. Find the largest core
         When they both are the same then choose the first one 
            also get the server type
            
       2. Then with another loop find the amount of servers with that type
        

       it will be checked with java 1.8
       next week demo
       javac -source 1.8 _target 1.8 *.java
      */ 

      
int big = 0;
int ao = 0;
String bigID = new String();

for(int i = 1; i < data.nRecs;i++){
   for(int x = 0; x < data.nRecs; x++){
      if(dnsJobs[i].core == dnsJobs[x].core){
         if(big == dnsJobs[i].core){ao++;}
      }else {//if(dnsJobs[i].core > dnsJobs[x].core){
         big = dnsJobs[i].core;
         bigID = dnsJobs[i].serverType;
         ao = 0;
      }
   }
}

         System.out.println(bigID + " "+ ao + " " + big );
         for(int i = 0; i < ao; i++){
            dout.write(("SCHD "+ job.jobID + " "+ bigID + " " + i +"\n").getBytes());
            dout.flush();

         }

         str = br.readLine();
         PP(str);

         dout.close();
         s.close();   
      
      }catch(Exception e){
         System.out.println(e);
      }

   }  
  
   static void PP(String s){
      System.out.println("Computer Says: " + s);
   }

   static void MSG_ok(){
      dout.write(("OK\n").getBytes());
      dout.flush();
   }

}



/*

int big = 0;
int ao = 0;
String bigID = new String();
Boolean biggest = false;


for(int i = 1; i < data.nRecs;i++){
   for(int x = 0; x < data.nRecs; x++){
      if(dnsJobs[i].core == dnsJobs[x].core){
         if((dnsJobs[i].serverType).equals(dnsJobs[x].serverType)){
            ao++;
         }else{
            ao = 0;
         }
      }else if(dnsJobs[i].core < dnsJobs[x].core){
          bigID = dnsJobs[x].serverType;
          biggest = true;
          big = dnsJobs[x].core;  
      }else{
         ao = 0;
      }
   }
   if(biggest)break;

}
*/





   //dout.write(("QUIT\n").getBytes());


//while(!str.equals("BYE\n")){

//}

/**
 * 
 *  dout.writeUTF("HELO");
    dout.flush();
    str2 = din.readUTF();
    System.out.println("Server says: "+ str2);
    //str = "BYE";
    dout.writeUTF("BYE");
    dout.flush();
    str2 = din.readUTF();
    System.out.println("Server says: "+ str2);
 */

/**
 *  // str = "HELO";
    dout.write(("HELO/n").getBytes());
    dout.flush();
    str2 = br.readLine();
    System.out.println("Server says: "+ str2);
    //str = "BYE";
    dout.write(("BYE/n").getBytes());
    dout.flush();
    str2 = br.readLine();
    System.out.println("Server says: "+ str2);
 */

/**
 * 
 * while(!str.equals("BYE")){
    str = br.readLine();
    dout.writeUTF(str);
    dout.flush();
    str2 = din.readUTF();
    System.out.println("Server says: "+ str2);
}
 * 
 */


//dout.writeUTF("Hello Server");
//dout.flush();



   // dout.write(("REDY\n").getBytes());
   // dout.flush();

   // str = br.readLine();
   // //System.out.println(str + "\n");
   
   // dout.write(("REDY\n").getBytes());
   // dout.flush();
   

   
   //dout.write(("GETS Capable\n" + ).getBytes());
   //dout.flush();

   
   //System.out.println(str2+ "\n");