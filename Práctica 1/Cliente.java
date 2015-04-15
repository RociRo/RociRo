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

public class Cliente{
    public static void main(String[] args) {
      String servidor = "127.0.0.1";//Se indica la dirección IP del servidor
      String palabra;//Para almacenar lo que ha digitado el usuario
      int puerto = 5000;//Puerto para la conexión
      System.out.println(".........................");
      try{
        Socket socket= new Socket (servidor,puerto);// Se hace la conexión al servidor
        //Lo que se obtenga del teclado
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        //para enviar el server
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        //Para recuperar la respuesta del server
	DataInputStream	datoEntrada = new DataInputStream(socket.getInputStream());

        do{
            System.out.print("Teclea una palabra: ");
            palabra = in.readLine();//Almaceno en la variable
            String cadena = "";
            out.println(palabra);//Escribo al server
            if ((palabra.trim().toUpperCase()).equals("NAME")){
                cadena = "Nombre de la máquina";
            } else if ((palabra.trim().toUpperCase()).equals("MAC")){
                cadena = "MAC de la máquina";                    
            } else if ((palabra.trim().toUpperCase()).equals("UNIDAD")){
                cadena = "Unidades disponibles de la máquina";                                                        
            } else if ((palabra.trim().toUpperCase()).equals("DATETIME")){
                cadena = "Fecha y hora de la máquina";
            } else if ((palabra.trim().toUpperCase()).equals("USERNAME")){
                cadena = "Nombre de usuario";
            }
            System.out.println(cadena+" = "+datoEntrada.readUTF());            
   	}while(!(palabra.trim().equals("SALIR")));//palabra.length()!=0);//hasta que no sea diferente del 0        
	socket.close();//Cierro objetos
	out.close();
	in.close();
	datoEntrada.close();
      } catch (IOException e) {//Error
       	        System.out.println("Error en conexión!!!");
      }
      
    }
}
