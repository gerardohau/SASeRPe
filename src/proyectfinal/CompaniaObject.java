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
public class CompaniaObject extends UnicastRemoteObject implements IRemoteCompania {

    private static final long serialVersionUID = 11L;

    public CompaniaObject() throws RemoteException {
        super();
    }

    public int save(Compania c) throws RemoteException {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return CompaniaRepository.save(c);
    }

    public int update(Compania c) throws RemoteException {
        try {
            System.out.println("Invoke update from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return CompaniaRepository.update(c);
    }

    public int delete(Compania c) throws RemoteException {
        try {
            System.out.println("Invoke delete from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return CompaniaRepository.delete(c);
    }

    public void deleteAll() throws RemoteException {
        try {
            System.out.println("Invoke deleteAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        CompaniaRepository.deleteAll();
    }

    public ArrayList findAll() throws RemoteException {
        try {
            System.out.println("Invoke findAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return CompaniaRepository.findAll();
    }
    
}
