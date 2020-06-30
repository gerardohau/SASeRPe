/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectfinal.IRemoteTransaccion;
import proyectfinal.IRemoteUsuario;
import proyectfinal.Transaccion;
import proyectfinal.Usuario;

/**
 *
 * @author Esthefany
 */
public class UsuarioCliente {
    
    public static void main(String[] args) {
     String CompanyRFC = "1abcdr758r";
        String userRFC = "mnbvcxz456";
        //vender
        //ganador
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteTransaccion rtransacciones = (IRemoteTransaccion) registry.lookup("Transaccion");
            IRemoteUsuario rusuario = (IRemoteUsuario) registry.lookup("Usuario");
            Date fecha = new Date();
            Transaccion transaccion_venta = new Transaccion();
            transaccion_venta.setRfcU(userRFC);
            transaccion_venta.setRfc(CompanyRFC);
            transaccion_venta.setAccionesO(2);
            transaccion_venta.setPrecioAOp(15.12f);
            transaccion_venta.setFechaOp(fecha);
            transaccion_venta.setNumeroAcciones(4);
            transaccion_venta.setOperacion(1);
            System.out.println(CompanyRFC);
            
            System.out.println("client prueba");
            ArrayList<Usuario> users = rusuario.findByComanyAndRFCU(CompanyRFC, userRFC);
            System.out.println(users.size());
            System.out.println(users.get(0).toString());
        } catch (Exception ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
