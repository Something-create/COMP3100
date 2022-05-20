import java.io.*;
import java.net.*;

public class MyClient{
   
   static DataOutputStream dout;
   static BufferedReader br; 

   static void MSG_OK(){
      try{
           dout.write(("OK\n").getBytes());
           dout.flush();
        }catch(Exception e){
           System.out.println(e);
        }
     }
  
     static void MSG_HELO(){
        try{
           dout.write(("HELO\n").getBytes());
           dout.flush();
        }catch(Exception e){
           System.out.println(e);
        }
     }
  
     static void MSG_AUTH(){
        try{
           dout.write(("AUTH Sarthak\n").getBytes());
           dout.flush();
        }catch(Exception e){
           System.out.println(e);
        }
     }
     static void MSG_REDY(){
        try{
           dout.write(("REDY\n").getBytes());
           dout.flush();
        }catch(Exception e){
           System.out.println(e);
        }
     }
  
     static void MSG_GETS_C(JobnSplit job){
        try{ 
           int k = job.getcore();
           dout.write(("GETS Capable "+ k + " " + job.getmemory()+ " " + job.getdisk() + " " +"\n").getBytes());
           dout.flush();
        }catch(Exception e){
           System.out.println(e);
        }
     }

     static void MSG_QUIT(){
        try{
         dout.write(("QUIT\n").getBytes());
         dout.flush();
        }catch(Exception e){
         System.out.println(e);
         }
     }

     static void PP(String s){
     // System.out.println("Computer Says: " + s);
     }   


   public static void main(String[] args) {

      String str = new String();
      int serverCount = 0;
      int big = 0;
      int AmountServer = 0;
      String bigID = new String();
      Boolean CheckLargestServer = false;

      try{

         Socket s = new Socket("127.0.0.1",50000);
         dout = new DataOutputStream(s.getOutputStream());
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         MSG_HELO();
            str = br.readLine();
            PP(str);
         
         MSG_AUTH();
            str = br.readLine();
            PP(str);
         
        String CHECKEND = new String();

         while(!(CHECKEND.equals("NONE"))){
//when JCPL -> redy then JOBN
            MSG_REDY();
               str = br.readLine();
               PP(str);  

            JobnSplit job; 
            job = new JobnSplit(str);

            while(job.is_JCPL()){
               MSG_REDY();
                  str = br.readLine();
                  PP(str);
                  job = new JobnSplit(str);  
            }

            if(job.is_NONE()){
               break;
            }

            MSG_GETS_C(job);
               str = br.readLine();
               PP(str); 
            DataSplit data;
            data = new DataSplit(str);

            MSG_OK();

            JobState[] dnsJobs = new JobState[data.nRecs+1];
            for(int i = 0; i < data.nRecs; i++){ 
               str = br.readLine(); 
               dnsJobs[i] = new JobState(str);
               //System.out.println(str);
            }  

            if(!CheckLargestServer){
            CheckLargestServer = true;
            for(int i = 0; i < data.nRecs;i++){
                  for(int x = 0; x < data.nRecs; x++){
                     if(dnsJobs[i].core == dnsJobs[x].core){
                        if(big == dnsJobs[i].core){AmountServer++;}
                     }else {
                        big = dnsJobs[i].core;
                        bigID = dnsJobs[i].serverType;
                        AmountServer = 0;
                     }
                  }
              }
            //System.out.println("**Server Type- "+bigID + " **Amount of Servers-  "+ AmountServer + " **Amount of cores- " + big );

         }


            MSG_OK();
                str = br.readLine();
                PP(str); 

            dout.write(("SCHD "+ job.jobID + " "+ bigID + " " + serverCount +"\n").getBytes());
            dout.flush();
            serverCount++;
                str = br.readLine();
                PP(str); 

            MSG_OK();
                str = br.readLine();
                PP(str); 

            if(serverCount > AmountServer ){
              serverCount = 0;
            }
           CHECKEND = br.readLine();
        }

         MSG_QUIT();
            str = br.readLine();
            PP(str);

         dout.close();
         s.close();   

      }catch(Exception e){
        System.out.println(e);
      }

   }

}