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
import java.util.*;
import javax.swing.filechooser.FileSystemView;
public class Servidor{
	public static void main(String args[]){
		try {
			int puerto = 5000;//Indica el puerto que utiliza el socket servidor
			ServerSocket servidor = new ServerSocket(puerto);//Se crea un socket servidor con el puerto especificado anteriormente
                        String linea;//Variable que recibirá la cadena enviada por el socket cliente
			System.out.println("Servidor iniciado en el puerto " + puerto + "...");
			Socket cliente = servidor.accept(); //Se indica que el socket servidor acepta una conexión
			System.out.println("Aceptando conexion...");
                        //Buffer para leer datos que envía el cliente
 			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                        //Para enviar respuestas al cliente
   			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
   			do{
   				linea = entrada.readLine();//Recupera lo que envío el cliente
   				System.out.println("El usuario tecleo: " + linea);
				if ((linea.trim().toUpperCase()).equals("NAME")){//Si ha enviado NAME
                                    InetAddress direccionLocal = InetAddress.getLocalHost();//Se obtiene la dirección local
                                    String nombreEquipo = direccionLocal.getHostName();//A partir de la dirección se obtiene el nombre de la máquina
                                    System.out.println("Nombre de máquina: " + nombreEquipo);//Se imprime el nombre de la máquina
                                    salida.writeUTF(nombreEquipo);//Envía al cliente el nombre de la máquina
				} else if ((linea.trim().toUpperCase()).equals("MAC")){//Si ha enviado MAC
                                    NetworkInterface a; 
                                    a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());//Obtiene la dirección de localhost
                                    byte[] mac = a.getHardwareAddress();//Se obtiene la MAC
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < mac.length; i++) {//Se recorre la MAC para darle formato
                                         sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));  
                                    } 
                                    System.out.println("MAC de máquina: " + sb.toString());
                                    salida.writeUTF(sb.toString());//Se envía al cliente
                                } else if ((linea.trim().toUpperCase()).equals("UNIDAD")){//Si ha tecleado unidad
                                    File unidades[];//Se declara una unidad
                                    unidades = File.listRoots();//Se obtiene el listado de las unidades
                                    String unidadesTodas= "";
                                    for (int i=0;i<unidades.length;i++) {//Se recorre la lista de unidades
                                         String unidad = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
                                         String descripcion = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);                                        
                                         System.out.println("["+unidad +" , " +descripcion+ "]");                                         
                                         unidadesTodas = unidadesTodas + "["+unidad +" , " +descripcion+ "]";//Se concatena en una cadena unidad descripción
                                    }     
                                    salida.writeUTF(unidadesTodas); //Se envía al cliente                                   
                                } else if ((linea.trim().toUpperCase()).equals("DATETIME")){// si ha teclado datetime
                                    Calendar fecha = new GregorianCalendar();
                                    //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema
                                    int anio = fecha.get(Calendar.YEAR);
                                    int mes = fecha.get(Calendar.MONTH);
                                    int dia = fecha.get(Calendar.DAY_OF_MONTH);
                                    int hora = fecha.get(Calendar.HOUR_OF_DAY);
                                    int minuto = fecha.get(Calendar.MINUTE);
                                    int segundo = fecha.get(Calendar.SECOND);
                                    System.out.println("Fecha Actual: " + dia + "/" + (mes+1) + "/" + anio);
                                    System.out.printf("Hora Actual: %02d:%02d:%02d %n", hora, minuto, segundo);                                        
                                    salida.writeUTF(dia + "/" + (mes+1) + "/" + anio +"  "+hora +":"+ minuto +":"+ segundo);//Se envía al cliente                                    
                                } else if ((linea.trim().toUpperCase()).equals("USERNAME")){//Si ha tecleado username
                                    String nomuser = System.getProperty("user.name");//Obtiene el nombre de usuario que ha iniciado sesión
                                    System.out.println(nomuser);
                                    salida.writeUTF(nomuser);                                                                        
                                }					
                                
   			}while(!(linea.trim().equals("SALIR")));//Hasta que no sea diferente de 0
                        entrada.close();//Cierro objetos
   			cliente.close();
   			servidor.close();
                        if (linea.trim().equals("SALIR"))
                            return;
		} catch (IOException e) {//Error
			e.printStackTrace();
			System.exit(-1);
		}
	}


}