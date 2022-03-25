import java.io.*;
import java.net.*;

public class MyServer {
public static void main(String[] args){
try{
ServerSocket ss=new ServerSocket(6666);
Socket s=ss.accept();//establishes connection 

DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout = new DataOutputStream(s.getOutputStream());
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

String str = "";
String str2 = "";

while(!str.equals("BYE")){
    str = din.readUTF();
    System.out.println("Clint Says: " + str);
    str2 = "G'DAY";
    dout.writeUTF(str2);
    dout.flush();
    str = din.readUTF();
    System.out.println("Clint Says: " + str);
    str2 = "BYE";
    dout.writeUTF(str2);
    dout.flush();
}

din.close();
s.close();
ss.close();

/**
 * while(!str.equals("BYE")){
    str = din.readUTF();
    System.out.println("Clint Says: " + str);
    str2 = br.readLine();
    dout.writeUTF(str2);
    dout.flush();
}
 */

//String	str=(String)dis.readUTF();
//System.out.println("message= "+str);

}catch(Exception e){System.out.println(e);}
}
}
