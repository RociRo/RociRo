/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roci
 */
import java.net.*;
import java.io.*;
public class ServidorWeb2 {
    public static void main(String args[])
    {
        
        while ( true ) {
            try {
                //Se abre una conexión al servidor público
            	//Socket ser_publico= new Socket ("cags.com.mx", 5100);// Se hace la conexión al servidor
                Socket ser_publico= new Socket ("127.0.0.1", 5100);// Se hace la conexión al servidor
                if (ser_publico.isConnected()) {
                    System.out.println("Conexion, enviando y recibiendo datos.");
                    System.out.println(" Entro aqui");
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
                    //Para enviar al servidor público
                    PrintWriter sal_servidor1 = new PrintWriter(ser_publico.getOutputStream(),true);
                     //Para recuperar la respuesta del servidor público
                     DataInputStream en_servidor1 = new DataInputStream(ser_publico.getInputStream());
                     
                    String host = en_servidor1.readUTF();//Se lee lo que envía el servidor público
                    try { 
                        URL url = new URL(host);//Convertimos en url lo que nos mando el server publico
                        URLConnection uc = url.openConnection();//Se abre la conexión
                        uc.connect();
                        //Creamos el objeto con el que vamos a leer
                        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                        String cadEntrada = "";
                        while ((cadEntrada = in.readLine()) != null) {//Mientras haya algo que leer
                            sal_servidor1.println(cadEntrada + "\n");
                            System.out.println( cadEntrada );
                        }
                        System.out.println( "salio del ciclo while  ");
                        in.close();
                    } catch ( Exception e ) {
                        System.out.println("Error: " + e );
                    }        
                }
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }//While
    }
}
