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
    private int sleep= 10000;
    
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
        System.out.println("Filtro de vistas y compras");
        for (int a = 0; a < this.ofertas.size(); a++) {
            //ventas
            System.out.println(this.ofertas.get(a).toString());
            if((this.ofertas.get(a).getAccionesOp() < 0) &&
              (this.ofertas.get(a).getPrecioAOp() < company.getvTA()  )){
                System.out.println(this.ofertas.get(a).toString());
                ventas.add(ofertas.get(a));
            }
            
            //Compras
            if((this.ofertas.get(a).getAccionesOp() > 0) &&
              (this.ofertas.get(a).getPrecioAOp() > company.getvTA()  )){
                compras.add(ofertas.get(a));
            }
        }
        
        System.out.println("==Competicion de ventas ==");
        Transaccion ganadorVenta = new Transaccion();
        if(!ventas.isEmpty()){
            float precioMenor = ventas.get(0).getPrecioAOp();
            for (int g = 0; g < ventas.size(); g++) {
                System.out.println(ventas.get(g));
                if(ventas.get(g).getPrecioAOp() <= precioMenor){
                    ganadorVenta = ventas.get(g);
                    precioMenor = ventas.get(g).getPrecioAOp();
                }
             }
            System.out.println(ganadorVenta.toString());
            this.updateDB(ganadorVenta);
            ganadorVenta.setStatus(true);
        }
        
        //Competir ventas
        System.out.println("==Competir compras==");
        Transaccion ganadorCompra = new Transaccion();
         System.out.println("tamaño de compra");
        if(!compras.isEmpty()){
            float precioMayor = compras.get(0).getPrecioAOp();
            for (int g = 0; g < compras.size(); g++) {
                System.out.println(compras.get(g));
                if(compras.get(g).getPrecioAOp() >= precioMayor){
                    ganadorVenta = compras.get(g);
                    precioMayor = compras.get(g).getPrecioAOp();
                    System.out.println("Compras:"+ganadorVenta.toString());
                }
            }
            System.out.println(ganadorVenta.toString());
            this.updateDB(ganadorVenta);
            ganadorVenta.setStatus(true);
        }
        
    }
    
    public void updateDB(Transaccion t){
        //venta
        System.out.println("Update bd");
        ArrayList<Compania> coms = new ArrayList(); 
        ArrayList<Usuario> uss = new ArrayList();
        if(t.getAccionesOp() < 0){
            coms = CompaniaRepository.findByRFC(keyCompany);
            System.out.println("compania" + coms.get(0).toString() );
            coms.get(0).setNumAD(coms.get(0).getNumAD() + -1*(t.getAccionesOp()));
            coms.get(0).setvTA(t.getPrecioAOp());
            CompaniaRepository.update(coms.get(0));
            System.out.println("consultando datos");
            System.out.println("Parameters: " + this.keyCompany +"--"+ t.getRfcU() );
            uss = UsuarioRepository.findByCompanyAndRFCU(t.getRfc(),t.getRfcU());
            System.out.println("Usuario:"+uss.toString());
            uss.get(0).setNumA(uss.get(0).getNumA()+ t.getAccionesOp());
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
