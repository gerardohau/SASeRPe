/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import proyectfinal.Compania;
import proyectfinal.IRemoteCompania;

/**
 *
 * @author Esthefany
 */
public class CompaniaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Get reference to rmi registry server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

            //Lookup server object
            IRemoteCompania rc = (IRemoteCompania) registry.lookup("Compania");

            //Save province
            Compania a = new Compania("1abcdr758r", 67, 34, 34.67f);
            Compania b = new Compania("1abfgr75rm", 60, 32, 30.67f);
            Compania c = new Compania("1abfgr12er", 50, 12, 45.30f);
            Compania d = new Compania("32ertyu89t", 15, 20, 70.70f); //wrong numAD
            Compania e = new Compania("32ertyu8rb", 45, 34, 30.45f);

            //Save province
            System.out.println("Saving companies...");
            rc.save(a);
            rc.save(b);
            rc.save(c);
            rc.save(d);
            rc.save(e);

            //Update province
            System.out.println("Update 20 to 10");
            Compania updatedD = new Compania("32ertyu89t", 15, 10, 70.70f);
            int iRet = rc.update(updatedD);

            //Display all provinces
            System.out.println("Display all companies");
            ArrayList<Compania> arrComp = rc.findAll();
            for (Compania comp : arrComp) {
                System.out.println(comp.toString());
            }

            //Delete C company
            System.out.println("Delete 1abfgr12er");
            rc.delete(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
