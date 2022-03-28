
import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;

public class MyClient {
   public static void main(String[] args) {

      String str = new String();

      try{	
         Socket s=new Socket("127.0.0.1",50000);

         //DataInputStream din = new DataInputStream(s.getInputStream());
         DataOutputStream dout=new DataOutputStream(s.getOutputStream());
         BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    
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

         dout.write(("GETS Capable"+ "\n").getBytes());
         dout.flush();

         dout.close();
         s.close();   
      
      }catch(Exception e){
         System.out.println(e);
      }

   }  
  
   static void PP(String s){
      System.out.println("Computer Says: " + s);
   }

}



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