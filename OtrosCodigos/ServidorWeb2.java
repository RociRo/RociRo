import java.net.*;
import java.io.*;
public class ServidorWeb2 {
    public static void main(String args[])
    {
            ServerSocket s;
            System.out.println("Servidor web iniciado en el puerto 90");
            try {
                // Crea el socket del servidor inicial
                s = new ServerSocket(5000);
            } catch ( Exception e ) {
            System.out.println("Error: " + e );
            return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion
                Socket remote = s.accept();
                String cadenaPagina;
                // se acepta la conexion
                System.out.println("Conexion, enviando datos.");
                BufferedReader in = new BufferedReader( new InputStreamReader(remote.getInputStream()) );
                PrintWriter out = new PrintWriter(remote.getOutputStream());
   		DataOutputStream salida = new DataOutputStream(remote.getOutputStream());                
                try {    
                    Socket socket= new Socket (in.readLine(),80);//Me conecto a la página que haya enviado por el puerto 80
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
                }

                remote.close();
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}