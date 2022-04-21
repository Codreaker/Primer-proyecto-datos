/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;
//imports
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


// se ceran los sockets del cliente
public class Servercliente {
    public static void cliente(){
        //se crea el host
        System.out.print("eo");
        String Host = "127.0.01";
        int puerto = 1234;
        try{
            // se crean los datainput y data output
            Socket socket = new Socket(Host,puerto);
            System.out.print("conectado");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream das = new DataInputStream(socket.getInputStream());
            dos.writeUTF("hola");
            
        }catch(Exception ex){
            
        }
    
    }
    public static void main(String[] args){
        cliente();
    }
    
    
}
