import java.net.*;
import java.io.*;
public class ServidorWeb {
    public static void main(String args[])
    {
            ServerSocket s;
            System.out.println("Servidor web iniciado en el puerto 90");
            try {
                // Crea el socket del servidor inicial
                s = new ServerSocket(9090);
            } catch ( Exception e ) {
            System.out.println("Error: " + e );
            return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion
                Socket remote = s.accept();
                // se acepta la conexion
                System.out.println("Conexion, enviando datos.");
                BufferedReader in = new BufferedReader( new InputStreamReader(remote.getInputStream()) );
                PrintWriter out = new PrintWriter(remote.getOutputStream());
                // leer los datos enviados,
                // para de leer hasta que lee el fin de linea, es decir la linea en blanco
                // la linea en blaco es la señal de fin de las cabeceras HTTP
                String linea=".";
		String host = "";
                //System.out.println(linea);		
				//linea = in.readLine();
   				// Manda la respuesta
                // Manda las cabeceras
		do {
                    linea = in.readLine();//Recupera lo que envío el cliente
                    System.out.println("El usuario tecleo: " + linea);
                    if  (linea.length()>7) {
     			if ((linea.substring(0, 4)).equals("Host") )
                             host = linea.substring(6, linea.length());
                    }
			    //out.println(linea);						            				
		}while ( !linea.equals("") );
				
                System.out.println("------------"+host);				
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html");
                // esta línea en blanco indica el final de las cabeceras
                out.println("");
                // Mandamos la pagina HTML
                out.println( "<H1>Hola</H2>");
                out.println( "<p>Probando</p>");
                out.flush(); //envía los datos
				
                remote.close();
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}