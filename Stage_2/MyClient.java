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
      int big = 0;
      int AmountServer = 0;
      Boolean CheckLargestServer = false;

      try{

         Socket s = new Socket("127.0.0.1",50000);
         dout = new DataOutputStream(s.getOutputStream());
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         //Start commincation with server with 'Helo' then Authencate the user
         HandShake();

        String CHECKEND = new String();

         while(!(CHECKEND.equals("NONE"))){

            //when JCPL -> redy then JOBN
            MSG_REDY();
               str = br.readLine();

            JobnSplit job; 
            job = new JobnSplit(str);

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
               DataSplit data;
               data = new DataSplit(str);

            MSG_OK();

            JobState[] dnsJobs = new JobState[data.nRecs+1];
            for(int i = 0; i < data.nRecs; i++){ 
               str = br.readLine(); 
               dnsJobs[i] = new JobState(str);
               //System.out.println(str);
            }  

// **** place here to allocate which server it should go to 

            MSG_OK();
                str = br.readLine();

            dout.write(("SCHD "+ job.jobID + " "+ dnsJobs[0].serverType + " " + serverCount +"\n").getBytes());
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
 * 
 * 
 */