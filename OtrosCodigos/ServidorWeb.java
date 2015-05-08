import java.net.*;
import java.io.*;
public class servidorWeb {
	
	public static String getContenidoHTML(String host) throws IOException {
	    URL url = new URL(host);
	    URLConnection uc = url.openConnection();
	    uc.connect();
	    
	    //Creamos el objeto con el que vamos a leer
	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	    String inputLine;
	    String contenido = "";
	      
	    while ((inputLine = in.readLine()) != null) {
	        contenido += inputLine + "\n";
	    }
	    in.close();
	    return contenido;
	}
	
    public static void main(String args[])
    {
    	
    	
            ServerSocket s;
            System.out.println("Servidor web iniciado en el puerto 5000");
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
                Socket remote = s.accept();//acepta la conexión del navegador
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
     			if ((linea.substring(0, 3)).equals("GET") )
                             host = linea.substring(4, linea.length()-9);
                    }
			  
                System.out.println("esto se mando al servidor: " + host);
              
                out.println(getContenidoHTML(host));
                    //out.println(linea);						            
//			    URL urlObject;
//			    String codigo;
//			    try{urlObject = new URL(host);
//			    InputStreamReader isr = new InputStreamReader(urlObject.openStream());
//			    BufferedReader br = new BufferedReader(isr);
//			    while((codigo = br.readLine())!= null){
//			    	out.println(codigo);
//			    	br.close();
//			    	
//			    }
//			    	
//			    }catch(MalformedURLException e){
//			    	e.printStackTrace();
//			    }catch(IOException e){
//			    	e.printStackTrace();
//			    }
		}while ( !linea.equals("") );
		
		//Ahora este va actuar como cliente
                
//                String servidor = "148.226.81.117";//Se indica la dirección IP del servidor
//                try {    
//                    Socket socket= new Socket (servidor,5000);// Se hace la conexión al servidor
//                    //para enviar el server
//                    PrintWriter cadenaServer = new PrintWriter(socket.getOutputStream(),true);
//                    //Para recuperar la respuesta del server
//                    DataInputStream	datoEntrada = new DataInputStream(socket.getInputStream());
//                    cadenaServer.println(host);
//                    
//                    datoEntrada.readUTF();
//                } catch ( Exception e ) {
//                        System.out.println("Error: " + e );
//                }   
//                
//                System.out.println("------------"+host);				
//                out.println("HTTP/1.0 200 OK");
//                out.println("Content-Type: text/html");
//                // esta línea en blanco indica el final de las cabeceras
//                out.println("");
//                // Mandamos la pagina HTML
//                out.println( "<H1>Hola</H2>");
//                out.println( "<p>Probando</p>");
//                out.flush(); //envía los datos
				
                remote.close();
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}