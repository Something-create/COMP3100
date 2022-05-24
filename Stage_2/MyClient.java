import java.io.*;
import java.net.*;

public class MyClient{
   
   static DataOutputStream dout;
   static BufferedReader br; 
   static String str = new String();


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

     static void HandShake(){
      try{
         
         MSG_HELO();
         str = br.readLine();  
         MSG_AUTH();
         str = br.readLine();
      
      }catch(IOException e){
      
         System.out.println(e);
      
      }
     }

   public static void main(String[] args) {

      int serverCount = 0;
      int AmountServer = 0;
      int CurrentServer = 0;

      try{

         Socket s = new Socket("127.0.0.1",50000);
         dout = new DataOutputStream(s.getOutputStream());
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //Start commincation with server with 'Helo' then Authencate the user
         HandShake();

        String CHECKEND = new String();

         while(!(CHECKEND.equals("NONE"))){

           
            MSG_REDY();
               str = br.readLine();

               //The Class JobnSplit breaks apart the messages Jobn, JCPL, NONE 
               //This class takes care of the jobs that require to be completed, is completed or there is no jobs.  
            JobnSplit job; 
            job = new JobnSplit(str);

               //when JCPL -> redy then JOBN
            while(job.is_JCPL()){
               MSG_REDY();
                  str = br.readLine();
                  job = new JobnSplit(str);  
            }

            if(job.is_NONE()){
               break;
            }

            MSG_GETS_C(job);
               str = br.readLine();
                  //The class DataSplit breaks apart the message DATA message that is sent by the server *** DATA nRecs recLen
               DataSplit data;
               data = new DataSplit(str);

            MSG_OK();

               //The class jobState Records all the server that are able to be used 
            JobState[] dnsJobs = new JobState[data.nRecs+1];
            for(int i = 0; i < data.nRecs; i++){ 
               str = br.readLine(); 
               dnsJobs[i] = new JobState(str);
                  //System.out.println(str);
            }  

// **** place here to allocate which server it should go to 

            MSG_OK();
                str = br.readLine();

     
            if(!((dnsJobs[CurrentServer].state).equals("inactive")) || !((dnsJobs[CurrentServer].state).equals("idle"))){
               serverCount++;
            }

            dout.write(("SCHD "+ job.jobID + " "+ dnsJobs[CurrentServer].serverType + " " + serverCount +"\n").getBytes());
            dout.flush();
          
            serverCount++;
            str = br.readLine();

            MSG_OK();
            str = br.readLine();

            if(serverCount > AmountServer ){
              serverCount = 0;
            }

           CHECKEND = br.readLine();
        }

         MSG_QUIT();
         str = br.readLine();

         dout.close();
         s.close();   

      }catch(Exception e){
        System.out.println(e);
      }

   }

}
/**
 * ***** DELETE BEFORE SUBMITTING **************
 *  MY NOTES:
 * 
 * - ADD COMMENTS ON WHAT THE OTHER CLASSES ARE DOING
 * - record server amount
 * 
 */