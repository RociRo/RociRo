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
public class ServidorWeb {

    public static void main(String args[])
    {
        ServerSocket s,s2;
        System.out.println("Servidor web 1 iniciado en el puerto 5000");
        try {
             // Creando sockets
             s = new ServerSocket(5000);//Este socket responde al navegador
             s2 = new ServerSocket(5100);//Este socket responde al servidor web2
        } catch ( Exception e ) {
            System.out.println("Error: " + e );
            return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion
                Socket cli_navegador = s.accept();//acepta la conexion del navegador
                // se acepta la conexion
                Socket cli_servidorUV = s2.accept();//acepta la conexion del navegador
                System.out.println("Conexion, enviando datos.");
                //Lee la peticion del navegador
                BufferedReader en_navegador = new BufferedReader( new InputStreamReader(cli_navegador.getInputStream()) );
                //Para mandarle respuesta al navegador
                PrintWriter sal_navegador = new PrintWriter(cli_navegador.getOutputStream());
                //Lee la petición del servidor Web 2
                BufferedReader en_sevidor_UV = new BufferedReader( new InputStreamReader(cli_servidorUV.getInputStream()) );
                //Escribe al servidor 2
                DataOutputStream sal_servidor_UV = new DataOutputStream(cli_servidorUV.getOutputStream());
                //Declaración de variablea auxiliares    
                String linea=".";
                String host = "";
                String cadena = ".";
                do {
                    linea = en_navegador.readLine();//Recupera lo que solicitó el navegador
                    System.out.println("El navegador envia: " + linea);
                    if  (linea.length()>7) {//Solo va a entrar aquí cuando haya peticiones GET
                    	if ((linea.substring(0, 3)).equals("GET") ) {//Cuando sea una petición GET
                             host = linea.substring(4, linea.length()-9);//S2 obtiene la parte del recurso
                             System.out.println("Entro al if del GET ::: ::: ::: " + host );
                             sal_servidor_UV.writeUTF(host);//Se manda a escribir al servidor de la UV el host
                             do{//Se verifica lo que nos responde
                         	    cadena = en_sevidor_UV.readLine();//Se lee lo que el servidor de la UV responde
                        	    System.out.println("Escribio esto: " + cadena);
                           	    sal_navegador.println(cadena);//Entonces se manda a escribir al navegador
                                    //cadena= null;
                                    //linea = "";
                                    cli_navegador.close();// Se cierra la conexion del navegador
                        	    System.out.println("Escribio esto 2 ");
                             }while(cadena != null);//Mientras haya algo que leer
                    	}
                    }
                    
                }while ( linea.length() != 0 );//Mientras haya algo que leer del navegador
                cli_navegador.close();// Se cierra la conexion del navegador
                en_navegador.close();// Se cierra la conexion remota
                sal_navegador.close();// Se cierra la conexion remota
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }//Cierra el While
    }
}