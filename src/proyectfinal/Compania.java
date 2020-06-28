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
public class Compania implements Serializable {

    private static final long serialVersionUID = 1L;
    private String rfc_;
    private int numTA_;
    private int numAD_;
    private float vTA_;

    public Compania(String rfc, int numTA, int numAD, float vTA) {
        this.rfc_ = rfc;
        this.numTA_ = numTA;
        this.numAD_ = numAD;
        this.vTA_ = vTA;
    }

    public Compania() {
    }

    public String getRfc() {
        return rfc_;
    }

    public void setRfc(String rfc) {
        rfc_ = rfc;
    }

    public int getNumTA() {
        return numTA_;
    }

    public void setNumTA(int numTA) {
        numTA_ = numTA;
    }

    public int getNumAD() {
        return numAD_;
    }

    public void setNumAD(int numAD) {
        numAD_ = numAD;
    }

    public float getvTA() {
        return vTA_;
    }

    public void setvTA(float vTA) {
        vTA_ = vTA;
    }

    public String toString() {
        return rfc_ + " - " + numTA_ + " - " + numAD_ + " - " + vTA_;
    }

}
