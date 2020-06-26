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
public interface IRemoteTransaccion extends Remote {
    public int save(Transaccion t) throws RemoteException;
    public int update(Transaccion t) throws RemoteException;
    public int delete(Transaccion t) throws RemoteException;
    public void deleteAll() throws RemoteException;
    public ArrayList findAll() throws RemoteException;
}
