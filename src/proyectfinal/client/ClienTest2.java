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
public class ClienTest2 {
    public static void main(String[] args) {
        String CompanyRFC = "1abcdr758r";
        String userRFC = "mnbvcxz123";
        //vender
        //perdedor
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            IRemoteTransaccion rtransacciones = (IRemoteTransaccion) registry.lookup("Transaccion");
            Date fecha = new Date();
            Transaccion transaccion_venta = new Transaccion();
            transaccion_venta.setRfcU(userRFC);
            transaccion_venta.setRfc(CompanyRFC);
            transaccion_venta.setAccionesO(2);
            transaccion_venta.setOperacion(1);
            transaccion_venta.setPrecioAOp(27.0f);
            transaccion_venta.setFechaOp(fecha);
            transaccion_venta.setNumeroAcciones(2);
            System.out.println(CompanyRFC);
            boolean res = rtransacciones.realizarOferta(transaccion_venta);
            System.out.println("client2");
            System.out.println(res);
        } catch (Exception ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
