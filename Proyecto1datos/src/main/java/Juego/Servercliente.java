/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/**
 *
 * @author Personal
 */
public class Servercliente {
    public static void cliente(){
        System.out.print("eo");
        String Host = "127.0.01";
        int puerto = 1234;
        try{
            
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
