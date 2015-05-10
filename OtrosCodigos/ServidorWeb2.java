import java.net.*;
import java.io.*;
public class ServidorWeb2 {
    public static void main(String args[])
    {
            ServerSocket s2;
            System.out.println("Servidor web 2 iniciado en el puerto 5100");
            try {
                // Crea el socket del servidor inicial
                s2 = new ServerSocket(5100);
            } catch ( Exception e ) {
            System.out.println("Error: " + e );
            return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion del servidor 1
                Socket cli_servidor1 = s2.accept();
                String cadenaPagina;
                // se acepta la conexion
                System.out.println("Conexion, enviando datos.");
                BufferedReader en_servidor1 = new BufferedReader( new InputStreamReader(cli_servidor1.getInputStream()) );
                PrintWriter sal_servidor1 = new PrintWriter(cli_servidor1.getOutputStream());
                //Para enviar respuestas al cliente
   		//DataOutputStream sal_servidor1 = new DataOutputStream(cli_servidor1.getOutputStream());
                // leer los datos enviados,
                // para de leer hasta que lee el fin de linea, es decir la linea en blanco
                // la linea en blanco es la señal de fin de las cabeceras HTTP
                String linea;
                linea = ".";
                //System.out.println(" línea es: " + linea);
				//if ((linea = en_servidor1.readLine()) != null)
				  //  System.out.println(" línea es null: " + linea);   
                //while ((linea = en_servidor1.readLine()) != null) {	
                //do {
                    linea = en_servidor1.readLine();//Recupera lo que envío el cliente
                    System.out.println("El servidor envía: " + linea);
                    System.out.println("El servidor envía............");
                    System.out.println("uno dos");                
                //}while (!(linea.trim().equals("")));    
				//} while ((linea = en_servidor1.readLine()) != null);
                //sal_servidor1.writeUTF("Hola mundo :) desde sockets");
                    sal_servidor1.println("Hola mundo :) desde sockets");
                    if (linea.trim().equals(null))
                            return;
                //sal_servidor1.flush();
                
                
   		
                /*DataOutputStream salida = new DataOutputStream(cli_servidor1.getOutputStream());                
                try {    
                    Socket socket= new Socket (en_servidor1.readLine(),80);//Me conecto a la página que haya enviado por el puerto 80
                    //Para recuperar la respuesta del server
                    DataInputStream	out2 = new DataInputStream(socket.getInputStream());
                    do {
                        cadenaPagina = out2.readUTF();//Recupera lo que envío el cliente
                        System.out.println("Leer: " + cadenaPagina);
                        salida.writeUTF(cadenaPagina);
			    //out.println(linea);						            				
                    }while ( !cadenaPagina.equals("") );
                } catch ( Exception e ) {
                        System.out.println("Error: " + e );
                }*/
                en_servidor1.close();// Se cierra la conexión remota
                cli_servidor1.close();
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}