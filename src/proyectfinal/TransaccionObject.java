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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esthefany
 */
public class TransaccionObject extends UnicastRemoteObject implements IRemoteTransaccion {
    private int contador = 0;
    private static final long serialVersionUID = 11L;
    private ManejadorEstadoTransaccion state;
    
    public TransaccionObject() throws RemoteException {
        super();
    }
    
    public TransaccionObject(ManejadorEstadoTransaccion st) throws RemoteException {
        super();
        this.state = st;
    }
    
    //se toma en cuenta el paso por referencia, la transaccion t, despues
    //de que termine la competencia este refleja si gano o no, con su atributo
    //status
    public boolean realizarOferta(Transaccion t) throws RemoteException {
        contador +=1;
        System.out.println("==Contador de ofertas:"+contador +"===");
        System.out.println((t.getAccionesOp() < 0) ? "Operación Venta":"Operación Compra" );
        
        //validar si la compania existe y si existe la cantidad de acciones es razonable
        ArrayList<Compania> coms = CompaniaRepository.findByRFC(t.getRfc()); 
        ArrayList<Usuario> usuarios = UsuarioRepository.findByRFCU(t.getRfcU());
        ArrayList<Usuario> usuariosVentas = UsuarioRepository.findByCompanyAndRFCU(t.getRfc(),t.getRfcU());
        boolean acciones = (-1*t.getAccionesOp()) > usuariosVentas.get(0).getNumA();
        if(t.getAccionesOp() < 0 && acciones ){
            System.out.println("Intentas vender más de lo que tienes");
            return false;
        }
        
        if(usuarios.isEmpty()){
          System.out.println("No existe un usuario con RFC: " + t.getRfc());
          return false;
        }
        if(coms.isEmpty()){
            System.out.println("Lista vacia");
            return false;
        }
        
        if(coms.get(0).getNumAD() <= t.getAccionesOp()){
            System.out.println("Acciones superiores a las disponibles");
            return false;
        }
        //Obtiene o crea la competencia para la empresa
        Competencia competencia = this.state.crearOEncontrarCompetencia(t.getRfc());
        competencia.addOferta(t);
        
        //Espera mientras el hilo se finalize de ejecutar (la competencia)
        while(competencia.isAlive()){}
        this.state.eliminarCompetencia(t.getRfc());
        System.out.println("===La competencia ha terminado: " 
          + t.getRfc() +"=======");
        System.out.println((t.getOperacion() == 1) ? "Operación Venta":"Operación Compra" );
        System.out.println(t.isStatus());
        return t.isStatus();
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
