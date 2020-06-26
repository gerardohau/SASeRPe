/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import proyectfinal.CompaniaObject;

/**
 *
 * @author Esthefany
 */
public class CompaniaServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1099);

            //Instantiate server object
            CompaniaObject co = new CompaniaObject();

            //Register server object
            registry.rebind("Compania", co);
            System.out.println("CompaniaServer is created!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
