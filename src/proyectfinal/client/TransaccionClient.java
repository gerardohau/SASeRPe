/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import proyectfinal.IRemoteUsuario;
import proyectfinal.Transaccion;
import java.util.Date;
import proyectfinal.IRemoteTransaccion;
import proyectfinal.Usuario;

/**
 *
 * @author Esthefany
 */
public class TransaccionClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Get reference to rmi registry server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

            //Lookup server object
            IRemoteTransaccion rt = (IRemoteTransaccion) registry.lookup("Transaccion");

            //Save province
            Date date = new Date();
            Transaccion uno = new Transaccion("mnbvcxz123", "1abfgr12er", date, 3, 65.87f);
            Transaccion dos = new Transaccion("mnbvcxz456", "1abfgr12er", date, 4, 345.87f);
            Transaccion tres = new Transaccion("mnbvcxz678", "1abcdr758r", date, 5, 456.45f);
            Transaccion cuatro = new Transaccion("mnbvcxz098", "1abcdr758r", date, 0, 567.98f); //wrong numacciones
            Transaccion cinco = new Transaccion("mnbvcxz348", "1abfgr75rm", date, 3, 73.67f);

            //Save transacciones
            System.out.println("Saving users...");
            rt.save(uno);
            rt.save(dos);
            rt.save(tres);
            rt.save(cuatro);
            rt.save(cinco);

            //Update transaccion
            System.out.println("Update 0 to 6");
            Transaccion updatedCuatro = new Transaccion("mnbvcxz098", "1abfgr12er", date, 6, 567.98f);
            int iRet = rt.update(updatedCuatro);
            //Display all transacciones
            System.out.println("Display all provinces");
            ArrayList<Transaccion> arrProv = rt.findAll();
            for (Transaccion t : arrProv) {
                System.out.println(t.toString());
            }

            //Delete tres
            System.out.println("Delete tres");
            rt.delete(tres);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
