/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Esthefany
 */
public class Transaccion implements Serializable {
    private boolean status = false;
    private static final long serialVersionUID = 1L;
    private String rfcU_;
    private String rfc_;
    private Date fechaOp_;
    private int accionesOp_;
    private float precioAOp_;
    private int numeroAcciones_;
    private int operacion_;
    public Transaccion() {
    }

    public Transaccion(String rfcU, String rfc, Date fechaOp, int accionesOp, float precioAOp, int numAcciones) {
        rfcU_ = rfcU;
        rfc_ = rfc;
        fechaOp_ = fechaOp;
        accionesOp_ = accionesOp;
        precioAOp_ = precioAOp;
        numeroAcciones_ = numAcciones;
    }

    public String getRfcU() {
        return rfcU_;
    }

    public void setRfcU(String rfcU) {
        rfcU_ = rfcU;
    }

    public String getRfc() {
        return rfc_;
    }

    public void setRfc(String rfc) {
        rfc_ = rfc;
    }

    public Date getFechaOp() {
        return fechaOp_;
    }

    public void setFechaOp(Date fechaOp) {
        this.fechaOp_ = fechaOp;
    }

    public int getAccionesOp() {
        return accionesOp_;
    }

    public void setAccionesO(int accionesOp) {
        accionesOp_ = accionesOp;
    }

    public float getPrecioAOp() {
        return precioAOp_;
    }

    public void setPrecioAOp(float precioAOp) {
        this.precioAOp_ = precioAOp;
    }
    
    public int getNumeroAcciones() {
        return numeroAcciones_;
    }
    
    public void setNumeroAcciones(int numAcciones) {
       this.numeroAcciones_ = numAcciones;
    }
    
    public int getOperacion() {
        return operacion_;
    }
    
    public void setOperacion(int operacion) {
       this.operacion_ = operacion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return rfcU_ + " - " + rfc_ + " - " + dateFormat.format(fechaOp_) + " -Acciones Operacion " + 
          accionesOp_ + " -Precio Actual de operacion " + precioAOp_;
    }

}
