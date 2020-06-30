package proyectfinal.client;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import proyectfinal.Compania;
import proyectfinal.IRemoteCompania;
import proyectfinal.IRemoteTransaccion;
import proyectfinal.IRemoteUsuario;
import proyectfinal.Transaccion;
import proyectfinal.Usuario;

/**
 *
 * @author Jorge
 */
public class Client {
  
  public static void main(String[] args){
    try {
     //Get reference to rmi registry server
     Registry registry = LocateRegistry.getRegistry("127.0.0.1");

     //Lista de objectos remotos Lookup server object
     IRemoteUsuario user = (IRemoteUsuario) registry.lookup("Usuario");
     IRemoteTransaccion rtransacciones = (IRemoteTransaccion) registry.lookup("Transaccion");
     IRemoteCompania rcompanies = (IRemoteCompania) registry.lookup("Compania");
    //Lista de scanners
     Scanner inputUser = new Scanner(System.in);
     Scanner menuOption = new Scanner(System.in);
     
     //Inicio de cliente
    System.out.println("====Sistema de compra - venta de acciones ====");
    System.out.println("Por favor, introdusca su RFC Usuario para identificar "
      + "y recuperar sus datos:");
    //Obtengo el rfc
    String userRfcu = inputUser.nextLine();
    ArrayList<Usuario> infoUsuario = user.findByRFCU(userRfcu);
    //Obtengo las empresas del usuario
    infoUsuario.stream().forEach(e -> {
        System.out.println("| Empresa: "+e.getRfc() +" | Acciones: " + 
          e.getNumA() +" | Último precio de compra : " + e.getUpc() + " | " );
    });
    
    System.out.println("===============================================");
    
    while(true){
      System.out.println("Operaciones que puedes realizar:");
      System.out.println(
        " 1.Comprar acciones \n "
        + "2.Vender mis acciones \n "
        + "3.Ver mis transacciones \n"
        + "4.Introducir otro RFC (Implica salir de su sesión)  \n "
          + "Escriba su opción:");
      String option = menuOption.nextLine();
       System.out.println("=============================================== \n \n");
      switch(option){
        case "1":
            System.out.println(
              "Lista de Compañias para comprar acciones:"
            );
            ArrayList<Compania> companies = rcompanies.findAll();
            companies.stream().forEach(e -> {
                System.out.println(
                  " | Empresa: "+e.getRfc() +
                  " | Total de acciones: " + e.getNumTA() +
                  " | Acciones disponibles: " + e.getNumAD() +
                  " | Valor actual de acción : " + e.getvTA() + " | " );
            });
            System.out.println(
              "\n  Introduce el RFC de  la compañia que deseas comprar:"
            );
            String compania = inputUser.nextLine();
            System.out.println("\n Introduce la cantidad de acciones:");
            int acciones = inputUser.nextInt();
            System.out.println(
              "Introduce una oferta de compra"
              + " (debe ser mayor al precio actual de las acciones acomprar"
              + " para tener más posibilidades de obtener la compra )"  );
            float precio = inputUser.nextFloat();
            System.out.println("Recuento de información: "
              + compania+"-" + acciones+"-" + precio);
            
            //Aquí se realiza la transacción
            //Aquí se realiza la transacción
            Date fechaCompra = new Date();
            Transaccion transaccion_compra = new Transaccion();
            transaccion_compra.setRfcU(userRfcu);
            transaccion_compra.setRfc(compania);
            transaccion_compra.setAccionesO(acciones < 0 ? acciones: -acciones);
            transaccion_compra.setPrecioAOp(precio);
            transaccion_compra.setFechaOp(fechaCompra);
            
            //Operación de compras
            
            boolean resultoper =rtransacciones.realizarOferta(transaccion_compra);
            if(resultoper){
              System.out.println("Se ha realiza con exito su transacción, \n"
               + "Regrese al menú y revise su estado de acciones \n "
                + "y transacción");
            
            }else{
              System.out.println("No se ha realizado su transacción, \n"
                + "gracias por participar. Realize una nueva transacción \n"
                + " y suerte");  
            }
                  
            
            System.out.println("===============================================");
            infoUsuario = user.findByRFCU(userRfcu);
            infoUsuario.stream().forEach(e -> {
            System.out.println("| Empresa: "+e.getRfc() +" | Acciones: " + 
              e.getNumA() +" | Último precio de compra : " + e.getUpc() + " | " );
            });
            System.out.println("===============================================");
            break;
        case "2":
            System.out.println(
              "Tu lista de Compañias para vender acciones:"
            );
            infoUsuario.stream().forEach(e -> {
                System.out.println(
                  " | Empresa: "+e.getRfc() +
                  " | Acciones: " + e.getNumA() +
                  " | Último precio de compra : " + e.getUpc() + " | " );
            });
            System.out.println(
              "\n  Introduce el RFC de  la compañia que desea vender acciones:"
            );
            String companiaVenta = inputUser.nextLine();
            System.out.println("\n Introduce la cantidad de acciones:");
            int accionesVenta = inputUser.nextInt();
            System.out.println(
              "Introduce una oferta de venta"
              + " (debe ser menor al precio actual de las acciones de la empresa"
              + " para tener más posibilidades de consolidar tu venta)"  );
            float precioVenta = inputUser.nextFloat();
            System.out.println("Recuento de información: "
              + companiaVenta +"-"+ accionesVenta +"-"+ precioVenta);
            
            //Aquí se realiza la transacción
            Date fecha = new Date();
            Transaccion transaccion_venta = new Transaccion();
            transaccion_venta.setRfcU(userRfcu);
            transaccion_venta.setRfc(companiaVenta);
            transaccion_venta.setAccionesO(accionesVenta < 0 ? accionesVenta: -accionesVenta);
            transaccion_venta.setPrecioAOp(precioVenta);
            transaccion_venta.setFechaOp(fecha);
            
            //Operación de compras
            
            boolean result =rtransacciones.realizarOferta(transaccion_venta);
            if(result){
              System.out.println("Se ha realiza con exito su transacción, \n"
               + "Regrese al menú y revise su estado de acciones \n "
                + "y transacción");
            
            }else{
              System.out.println("No se ha realizado su transacción, \n"
                + "gracias por participar. Realize una nueva transacción \n"
                + " y suerte");  
            }
                  
            
            System.out.println("===============================================");
            infoUsuario = user.findByRFCU(userRfcu);
            infoUsuario.stream().forEach(e -> {
            System.out.println("| Empresa: "+e.getRfc() +" | Acciones: " + 
              e.getNumA() +" | Último precio de compra : " + e.getUpc() + " | " );
            });
            System.out.println("===============================================");
            break;
        case "3": 
            System.out.println(
              "Transacciones realizadas o actuales:"
            );
            ArrayList<Transaccion> transacciones = rtransacciones.findByRFCU(userRfcu);
            transacciones.stream().forEach(e -> {
                    System.out.println(
                  " | Empresa: "+e.getRfc() +
                  " | Acciones de operación: " + e.getAccionesOp() +
                  " | Precio de operación: " + e.getPrecioAOp() +  
                  " | Fecha de operación : " + e.getFechaOp() + " | " );
                    });
            System.out.println("===============================================");
            break;
            
        case "4":  
            System.out.println(
              "Por favor, introdusca su RFC Usuario para identificar "
              + "y recuperar sus datos:");
            //Obtengo el rfc
            userRfcu = inputUser.nextLine();
            infoUsuario = user.findByRFCU(userRfcu);
            //Obtengo las empresas del usuario
            infoUsuario.stream().forEach(e -> {
                System.out.println(
                  " | Empresa: "+e.getRfc() +
                  " | Acciones: " + e.getNumA() +
                  " | Último precio de compra : " + e.getUpc() + " | " );
            });

            System.out.println("===============================================");
            break;  
        default: 
            System.out.println("===============================================");
            infoUsuario = user.findByRFCU(userRfcu);
            infoUsuario.stream().forEach(e -> {
            System.out.println("| Empresa: "+e.getRfc() +" | Acciones: " + 
              e.getNumA() +" | Último precio de compra : " + e.getUpc() + " | " );
            });
            System.out.println("============================================= \n");
            break;
       }
      
        


    }
     
    } catch (Exception e) {
      //TODO: handle exception
        System.out.println("Error:" + e.getMessage());
    }
    
  }


}