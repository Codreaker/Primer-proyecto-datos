
package Juego;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class logicaserver {
    Socket socket;
    ServerSocket serversocket;
    public static void server(){
        try{
            System.out.print("eo");
            ServerSocket serversocket = new ServerSocket(1234);
            Socket socket = serversocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream das = new DataInputStream(socket.getInputStream());
            String mensaje = das.readUTF();
            System.out.println(mensaje);
            
        }catch(Exception e){
            
        }
    }
    public static void main(String[] args){
        logicaserver.server();
        System.out.println("pppp");   
    }    
}
