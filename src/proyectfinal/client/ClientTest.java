/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectfinal.IRemoteTransaccion;
import proyectfinal.Transaccion;

/**
 *
 * @author jorge
 */
public class ClientTest {
    public static void main(String[] args) {
        String CompanyRFC = "1abcdr758r";
        String userRFC = "mnbvcxz456";
        //vender
        //ganador
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteTransaccion rtransacciones = (IRemoteTransaccion) registry.lookup("Transaccion");
            Date fecha = new Date();
            Transaccion transaccion_venta = new Transaccion();
            transaccion_venta.setRfcU(userRFC);
            transaccion_venta.setRfc(CompanyRFC);
            transaccion_venta.setAccionesO(-2);
            transaccion_venta.setPrecioAOp(15.12f);
            transaccion_venta.setFechaOp(fecha);
            transaccion_venta.setNumeroAcciones(4);
            System.out.println(CompanyRFC);
            boolean res = rtransacciones.realizarOferta(transaccion_venta);
            System.out.println("client1");
            System.out.println(res);
        } catch (Exception ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
