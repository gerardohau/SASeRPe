/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import proyectfinal.TransaccionObject;

/**
 *
 * @author Esthefany
 */
public class TransaccionServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1099);

            //Instantiate server object
            TransaccionObject to = new TransaccionObject();

            //Register server object
            registry.rebind("Transaccion", to);
            System.out.println("TransaccionServer is created!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
