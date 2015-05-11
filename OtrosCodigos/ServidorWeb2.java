import java.net.*;
import java.io.*;
public class ServidorWeb2 {
    public static void main(String args[])
    {
        ServerSocket s2;
        System.out.println("Servidor web 2 iniciado en el puerto 5100");
        try {
             // Crea el socket del servidor inicial, escuchara en ese puerto
             s2 = new ServerSocket(5100);
        } catch ( Exception e ) {
          	System.out.println("Error: " + e );
           	return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion del servidor 1 para enviar y recibir
            	// se acepta la conexión
            	Socket ser_publico= new Socket ("cags.com.mx", 5100);// Se hace la conexión al servidor
                System.out.println(" Entro aquí 1");
                Socket cli_servidor1 = s2.accept();
                System.out.println("Conexión, enviando y recibiendo datos.");
                System.out.println(" Entro aquí");
                PrintWriter salida_servidor1 = new PrintWriter(ser_publico.getOutputStream(),true);
                //Para recuperar la respuesta del server
                DataInputStream entrada_servidor1 = new DataInputStream(ser_publico.getInputStream());
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
                BufferedReader en_servidor1 = new BufferedReader( new InputStreamReader(cli_servidor1.getInputStream()) );
                //PrintWriter sal_servidor1 = new PrintWriter(cli_servidor1.getOutputStream());
                //Para enviar respuestas al cliente
   				DataOutputStream sal_servidor1 = new DataOutputStream(cli_servidor1.getOutputStream());
   				String linea, cadenaPag, otra;
                linea = ".";
                do {         
                	linea = en_servidor1.readLine();
                	otra = linea.substring(7, linea.length());
                    System.out.println("El servidor envía: " + linea);
                    System.out.println("El servidor envía............");
                    System.out.println("uno dos" + otra);
                    //sal_servidor1.writeUTF("Hola mundo :) desde sockets.");
                    try { 
                    	URL url = new URL(linea);//Convertimos en url lo que nos mando el server público
                	    URLConnection uc = url.openConnection();
                	    uc.connect();
                	    //Creamos el objeto con el que vamos a leer
                	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                	    String inputLine = "";
                	    String contenido = "";
                	      
                	    while ((inputLine = in.readLine()) != null) {
                	        contenido += inputLine + "\n";
                	    }
                	    sal_servidor1.writeUTF(contenido);
                	    in.close();
                    } catch ( Exception e ) {
                            System.out.println("Error: " + e );
                    }                    
                }while (linea.length()!=0);    
                if (linea.trim().equals(null))
                    return;
                en_servidor1.close();// Se cierra la conexión remota
                cli_servidor1.close();
                sal_servidor1.close();// 
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }//While
    }
}