import java.net.*;
import java.io.*;
public class ServidorWeb {
	public static String getContenidoHTML(String host) throws IOException {
	    URL url = new URL(host);
	    URLConnection uc = url.openConnection();
	    uc.connect();
	    //Creamos el objeto con el que vamos a leer
	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	    String inputLine = ".";
	    String contenido = "";
	      
	    while ((inputLine = in.readLine()) != null) {
	        contenido += inputLine + "\n";
	    }
	    in.close();
	    return contenido;
	}
        public static void CnxServer2 (String recurso, PrintWriter salida_navegador) {
            //Ahora se comportar� como un cliente
            try{
                Socket ser_privado= new Socket ("148.226.81.117", 5100);// Se hace la conexi�n al servidor
       		  	System.out.println(" Entro aqu�");
                String cadena;
                //para enviar el server privado
                PrintWriter sal_servidor1 = new PrintWriter(ser_privado.getOutputStream(),true);
                //Para recuperar la respuesta del server
                DataInputStream en_servidor1 = new DataInputStream(ser_privado.getInputStream());
                sal_servidor1.println(recurso);//Escribo al server
                while (!(cadena = en_servidor1.readUTF()).equals(null)){ //imprimir respuesta del servidor
          		  System.out.println(" El servidor privado me responde = "+cadena);
          		  salida_navegador.println(cadena);
          		  salida_navegador.flush();
          	  	}
                ser_privado.close();//Cierro objetos
                sal_servidor1.close();
                en_servidor1.close();
            } catch (IOException e) {//Error
                System.out.println("Error en conexi�n " + e);
            }
            
        }
                
    public static void main(String args[])
    {
        ServerSocket s;
        System.out.println("Servidor web 1 iniciado en el puerto 5000");
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
                Socket cli_navegador = s.accept();//acepta la conexi�n del navegador
                // se acepta la conexion
                System.out.println("Conexion, enviando datos.");
                //Lee la petici�n del navegador
                BufferedReader en_navegador = new BufferedReader( new InputStreamReader(cli_navegador.getInputStream()) );
                //Para mandarle respuesta al navegador
                PrintWriter sal_navegador = new PrintWriter(cli_navegador.getOutputStream());
                // leer los datos enviados,
                // para de leer hasta que lee el fin de linea, es decir la linea en blanco
                // la l�nea en blanco es la se�al de fin de las cabeceras HTTP
                String linea=".";
                String host = "";
                do {
                    linea = en_navegador.readLine();//Recupera lo que env�o el cliente
                    System.out.println("El navegador env�a: " + linea);
                    if  (linea.length()>7) {
                    	if ((linea.substring(0, 3)).equals("GET") ) {
                             host = linea.substring(4, linea.length()-10);
                             CnxServer2(host, sal_navegador);
                    	}
                    }
                }while ( !linea.equals("") );
                cli_navegador.close();// Se cierra la conexi�n remota
                en_navegador.close();// Se cierra la conexi�n remota
                sal_navegador.close();// Se cierra la conexi�n remota
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}