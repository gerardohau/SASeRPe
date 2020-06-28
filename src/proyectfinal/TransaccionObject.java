/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.rmi.RemoteException;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Esthefany
 */
public class TransaccionObject extends UnicastRemoteObject implements IRemoteTransaccion {

    private static final long serialVersionUID = 11L;

    public TransaccionObject() throws RemoteException {
        super();
    }

    public int save(Transaccion t) throws RemoteException {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return TransaccionRepository.save(t);
    }

    public int update(Transaccion t) throws RemoteException {
        try {
            System.out.println("Invoke update from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return TransaccionRepository.update(t);
    }

    public int delete(Transaccion t) throws RemoteException {
        try {
            System.out.println("Invoke delete from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return TransaccionRepository.delete(t);
    }

    @Override
    public void deleteAll() throws RemoteException {
        try {
            System.out.println("Invoke deleteAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        TransaccionRepository.deleteAll();
    }

    public ArrayList findAll() throws RemoteException {
        try {
            System.out.println("Invoke findAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return TransaccionRepository.findAll();
    }
    
    public ArrayList findByRFCU(String Rfcu) throws RemoteException {
        try {
            System.out.println("Invoke findAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return TransaccionRepository.findByRFCU(Rfcu);
    }

}
