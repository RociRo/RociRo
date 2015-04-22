#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <netdb.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
 
 
int main(int argc, char *argv[]) {                  
 	int v_socket;    // Declaro variable del socket
	int conexion;    // Declaro variable para conexión
	int c = 1;       // contador
        char ip[15];
        //ip = argv[1];
	struct sockaddr_in ipdestino; //Para almacenar información de la máquina destino a donde nos vamos a conectar
 
	ipdestino.sin_family = AF_INET;
	ipdestino.sin_addr.s_addr = inet_addr("127.0.0.1");//Para este ejemplo le mando mi ip
	bzero(&(ipdestino.sin_zero), 8);
        printf("Este programa muestra los puertos abiertos \n");
        printf("En seguida la lista del número de puerto \n");
        printf("    [Puerto] \n");
	for (c = 0; c != 9000; c++) {//Recorro hasta 9000
 	     v_socket = socket(AF_INET,SOCK_STREAM,0);//Se crea el socket orientado a conexión, a otras redes
             ipdestino.sin_port = htons(c);//El contador será el número de puerto
             conexion = connect(v_socket, (struct sockaddr *)&ipdestino, sizeof(struct sockaddr));
	     if (conexion != -1) {
		printf("      %d \n",c);
		close(conexion);//Cierro conexión
		close(v_socket);//Cierro socket
	     }
	     close(v_socket);//Cierro socket
	}
 
return 0;
}
