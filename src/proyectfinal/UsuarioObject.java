/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.rmi.server.*;
import java.rmi.*;
import java.util.ArrayList;

/**
 *
 * @author Esthefany
 */
public class UsuarioObject extends UnicastRemoteObject implements IRemoteUsuario {

    private static final long serialVersionUID = 11L;

    public UsuarioObject() throws RemoteException {
        super();
    }

    public int save(Usuario u) throws RemoteException {
        try {
            System.out.println("Invoke save from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.save(u);
    }

    public int update(Usuario u) throws RemoteException {
        try {
            System.out.println("Invoke update from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.update(u);
    }

    public int delete(Usuario u) throws RemoteException {
        try {
            System.out.println("Invoke delete from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.delete(u);
    }

    public void deleteAll() throws RemoteException {
        try {
            System.out.println("Invoke deleteAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        UsuarioRepository.deleteAll();
    }

    @Override
    public ArrayList findAll() throws RemoteException {
        try {
            System.out.println("Invoke findAll from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.findAll();
    }

    public ArrayList findByCompany(String criteria) {
        try {
            System.out.println("Invoke findByCompany from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.findByCompany(criteria);
    }
    
    public ArrayList findByRFCU(String criteria) {
        try {
            System.out.println("Invoke findByCompany from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.findByRFCU(criteria);
    }
    
    public ArrayList findByComanyAndRFCU(String com, String rfcu) {
        try {
            System.out.println("Invoke findByCompany from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            snae.printStackTrace();
        }
        return UsuarioRepository.findByCompanyAndRFCU(com, rfcu);
    }
}
