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
import proyectfinal.Usuario;

/**
 *
 * @author Esthefany
 */
public class UsuarioCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Get reference to rmi registry server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

            //Lookup server object
            IRemoteUsuario ru = (IRemoteUsuario) registry.lookup("Usuario");

            //Save province
            Usuario uno = new Usuario("mnbvcxz123", "1abcdr758r", 3, 65.87f);
            Usuario dos = new Usuario("mnbvcxz456", "1abcdr758r", 4, 345.87f);
            Usuario tres = new Usuario("mnbvcxz678", "1abfgr75rm", 5, 456.45f);
            Usuario cuatro = new Usuario("mnbvcxz098", "1abfgr12er", 0, 567.98f); //wrong numacciones
            Usuario cinco = new Usuario("mnbvcxz348", "1abfgr12er", 3, 73.67f);

            //Save users
            System.out.println("Saving users...");
            ru.save(uno);
            ru.save(dos);
            ru.save(tres);
            ru.save(cuatro);
            ru.save(cinco);

            //Update user
            System.out.println("Update 0 to 6");
            Usuario updatedCuatro = new Usuario("mnbvcxz098", "1abfgr12er", 6, 567.98f);
            int iRet = ru.update(updatedCuatro);

            //Display all users
            System.out.println("Display all provinces");
            ArrayList<Usuario> arrProv = ru.findAll();
            for (Usuario u : arrProv) {
                System.out.println(u.toString());
            }

            //Delete tres
            System.out.println("Delete tres");
            ru.delete(tres);

            //Display user with rfc 1abfgr12er
            System.out.println("Display from company 1abfgr12er");
            arrProv = ru.findByCompany("1abfgr12er");
            for (Usuario u : arrProv) {
                System.out.println(u.toString());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
