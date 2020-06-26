/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.io.Serializable;

/**
 *
 * @author Esthefany
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String rfcU_;
    private String rfc_;
    private int numA_;
    private float upc_;

    public Usuario() {
    }

    public Usuario(String rfcU, String rfc, int numA, float upc) {
        this.rfcU_ = rfcU;
        this.rfc_ = rfc;
        this.numA_ = numA;
        this.upc_ = upc;
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

    public int getNumA() {
        return numA_;
    }

    public void setNumA(int numA) {
        numA_ = numA;
    }

    public float getUpc() {
        return upc_;
    }

    public void setUpc(float upc) {
        upc_ = upc;
    }

    public String toString() {
        return rfcU_ + " - " + rfc_ + " - " + numA_ + " - " + upc_;
    }

}
