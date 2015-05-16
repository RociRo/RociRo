/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roci
 */
import java.io.*;
import java.net.*;

class Servicio1
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[7168];
			byte[] sendData1 = new byte[512];
   
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = /*java.net.*/InetAddress.getLocalHost().getHostAddress();
		          System.out.println("envio: " + capitalizedSentence);
                  sendData1 = capitalizedSentence.getBytes();
                  
                  String str_proceso = null;
	          String admin = System.getenv("windir") + "\\system32\\" + "tasklist /svc";
                  //byte [] bufer = admin.getBytes();
	          Process proceso = Runtime.getRuntime().exec(admin);
	          BufferedReader input = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			  ProcessBuilder pb = new ProcessBuilder("tasklist", "/FO", "CSV", "/FI", "SESSIONNAME ne Services", "/NH");
	          while((str_proceso = input.readLine()) != null){
                  //byte [] bufer = str_proceso.getBytes();
                  System.out.println("envio: " + str_proceso);
				  System.out.println("envío 1: " + str_proceso[0]);
				  System.out.println("envío 1: " + str_proceso[1]);				  
                  //sendData  = str_proceso.getBytes();
                  }
		   //System.out.println(str_proceso);
	         //}
             
            
            
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
				  DatagramPacket sendPacket1 = new DatagramPacket(sendData1, sendData1.length, IPAddress, port);
                  serverSocket.send(sendPacket1);
       
             }
      }
}
