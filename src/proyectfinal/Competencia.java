/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class Competencia extends Thread {
    private ArrayList<Transaccion> ofertas = new ArrayList();
    private String keyCompany;
    private int sleep= 15000;
    
    public Competencia(String company){
        keyCompany= company;
    }
    
    public void addOferta(Transaccion oferta){
        if(!this.ofertas.isEmpty()) {this.sleep +=10000;}
        ofertas.add(oferta);
        System.out.println("==Una nueva oferta ha sido agregada"
          + "\n Ventana de tiempo total: "+this.sleep/1000+"s == \n");
    }
    
    private void realizarCompetencia(){
        System.out.println(" == Realizando competencia ==");
        Compania company = (Compania) CompaniaRepository.findByRFC(this.keyCompany).get(0);
        ArrayList<Transaccion> ventas = new ArrayList();
        ArrayList<Transaccion> compras = new ArrayList();
        //Filtrar en vistas y compras
        for (int a = 0; a < this.ofertas.size(); a++) {
            //ventas
            if((this.ofertas.get(a).getOperacion() == 1) &&
              (this.ofertas.get(a).getPrecioAOp() < company.getvTA()  )){
                ventas.add(ofertas.get(a));
            }
            
            //Compras
            if((this.ofertas.get(a).getOperacion() == -1) &&
              (this.ofertas.get(a).getPrecioAOp() > company.getvTA()  )){
                compras.add(ofertas.get(a));
            }
        }
        
        //Competir ventas
        System.out.println("Competir ventas");
        Transaccion ganadorVenta = new Transaccion();
        //marca error si no existe el get(0)
        if(ventas.size() > 0 ){
            ganadorVenta = ventas.get(0);
            System.out.println(ventas.size());
            //en el caso de que sea más de uno
            if(ventas.size() > 1){
                for (int i = 0; i < ventas.size(); i++) {
                    System.out.println(compras.get(i).toString());
                if (ventas.get(i).getPrecioAOp() <= ganadorVenta.getPrecioAOp()) {
                    System.out.println(compras.get(i).toString());
                    ganadorVenta = compras.get(i);
                }
            }
            }
          //Realizar modificación en la base de datos
          //Actualizar compania
         
         System.out.println("aop"+ ganadorVenta.getPrecioAOp()+ "acciones"+ganadorVenta.getAccionesOp());
          ganadorVenta.setStatus(true);  
         // this.updateDB(ganadorVenta);   
        }
        //Competir Compras
        System.out.println("competir compras");
        Transaccion ganadorCompra = new Transaccion();
        if(compras.size() > 0){
            ganadorCompra = compras.get(0);
            if(compras.size() > 1){
            for (int j = 0; j < compras.size(); j++) {
                if (compras.get(j).getPrecioAOp() >= ganadorCompra.getPrecioAOp()) {
                    ganadorCompra = compras.get(j);
                }
            }
            }
            System.out.println(ganadorCompra.getPrecioAOp()+ganadorCompra.getAccionesOp());  
          ganadorCompra.setStatus(true);
         // this.updateDB(ganadorCompra);
        }
        
    }
    
    public void updateDB(Transaccion t){
        //venta
        ArrayList<Compania> coms = new ArrayList(); 
        ArrayList<Usuario> uss = new ArrayList();
        if(t.getOperacion() == 1){
            coms = CompaniaRepository.findByRFC(keyCompany);
            System.out.println("Guardan datos 1");
            coms.get(0).setNumAD(coms.get(0).getNumAD() + t.getAccionesOp());
            coms.get(0).setvTA(t.getPrecioAOp());
            CompaniaRepository.update(coms.get(0));
            System.out.println("consultando datos");
            uss = UsuarioRepository.findByCompanyAndRFCU(keyCompany,t.getRfcU());
            uss.get(0).setNumA(uss.get(0).getNumA()-t.getAccionesOp());
            uss.get(0).setUpc(t.getPrecioAOp());
            UsuarioRepository.update(uss.get(0));
        }
        else{
         //compra
            coms = CompaniaRepository.findByRFC(keyCompany);
            coms.get(0).setNumAD(coms.get(0).getNumAD() - t.getAccionesOp());
            coms.get(0).setvTA(t.getPrecioAOp());
            CompaniaRepository.update(coms.get(0));
            
            uss = UsuarioRepository.findByCompanyAndRFCU(keyCompany,t.getRfcU());
            //Comprar a una nueva compania
            if(uss.isEmpty()){
                Usuario usuario = new Usuario();
                usuario.setNumA(t.getAccionesOp());
                usuario.setRfc(t.getRfc());
                usuario.setRfcU(t.getRfcU());
                UsuarioRepository.save(usuario);
            }else{
                uss.get(0).setNumA(uss.get(0).getNumA()+t.getAccionesOp());
                uss.get(0).setUpc(t.getPrecioAOp());
                UsuarioRepository.update(uss.get(0));
            }
        }
        TransaccionRepository.save(t);
    }
    
    
    
    @Override
    public void run() {
        try {
            Thread.sleep(this.sleep);
            realizarCompetencia();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
