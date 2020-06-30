/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import proyectfinal.CompaniaObject;
import proyectfinal.ManejadorEstadoTransaccion;
import proyectfinal.TransaccionObject;
import proyectfinal.UsuarioObject;

/**
 *
 * @author Esthefany
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                  try {
      //Create and get reference to rmi registry
      Registry registry = LocateRegistry.createRegistry(1099);
 
      //Instantiate server object
      UsuarioObject user = new UsuarioObject();
      CompaniaObject company = new CompaniaObject();

      //Register state server
      ManejadorEstadoTransaccion mt = new ManejadorEstadoTransaccion();
       TransaccionObject transaccion = new TransaccionObject(mt);      
      //Register server object
      registry.rebind("Usuario", user);
      registry.rebind("Transaccion", transaccion);
      registry.rebind("Compania", company);
      System.out.println("Server's system  is created!!!");
    } catch (Exception e) {
      System.out.println(e);
    }
    }
    
}
