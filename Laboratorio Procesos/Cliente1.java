/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roci
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class Cliente1
{
   public static void main(String args[]) throws Exception
   {
       
       ArrayList<String> listaIPs = new ArrayList<String>();
       for (int i = 1; i > 0 ; i--){
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
            byte[] sendData = new byte[50];
            byte[] receiveData = new byte[50];
            String sentence = "Prueba";//inFromUser.readLine() ; //+ java.net.InetAddress.getLocalHost().getCanonicalHostName();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            System.out.print("send"  );                
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            System.out.print("receive"  );                
            String modifiedSentence = new String(receivePacket.getData());
           
            clientSocket.close();
            //String admin = System.getenv("windir") + "\\system32\\" + "tasklist.exe";
            if (!listaIPs.contains(modifiedSentence)){
                listaIPs.add(modifiedSentence);
            }
            System.out.print("antes"  );                
            
        }
        System.out.print("termina de construir tabla 0"  );                
                /*menuItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){}
                });
                frame.addMouseListener(new MouseAdapter(){
                public void mouseReleased(MouseEvent Me){
                //if(Me.isPopupTrigger()){
                   // Pmenu.show(Me.getComponent(), Me.getX(), Me.getY());
                 //}
                }
                }); 
        */
         Tabla frame = new Tabla(listaIPs);
	 frame.pack();
         System.out.print("termina de construir tabla 2"  );                
	 frame.setVisible(true); 
         
 	Iterator<String> nombreIterator = listaIPs.iterator();
	while(nombreIterator.hasNext()){
              String elemento = nombreIterator.next();
              System.out.print("El servicio esta activo en: " + elemento+"\n"  );
	}
        
     // clientSocket.close();
        
        //nuevo DSC
   }
} 
