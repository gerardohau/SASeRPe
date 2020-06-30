/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Esthefany
 */
public interface IRemoteUsuario extends Remote {
    public int save(Usuario u) throws RemoteException;
    public int update(Usuario u) throws RemoteException;
    public int delete(Usuario u) throws RemoteException;
    public void deleteAll() throws RemoteException;
    public ArrayList findAll() throws RemoteException;
    public ArrayList findByCompany(String criteria) throws RemoteException;
    public ArrayList findByRFCU(String criteria) throws RemoteException;
    public ArrayList findByComanyAndRFCU(String co, String user) throws RemoteException;
}
