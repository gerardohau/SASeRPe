/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jorge
 */
public class ManejadorEstadoTransaccion {
    //Mapa de competencias sobre una compania
    private HashMap<String,Competencia> competencias = new HashMap<String,Competencia>();
    
    public ManejadorEstadoTransaccion(){
    }
    
    public Competencia crearOEncontrarCompetencia(String rfc){
        Competencia competencia = this.competencias.get(rfc);
        if(competencia == null){
            System.out.println("Nueva competencia:"+ rfc);
            competencia = new Competencia(rfc);
            this.competencias.put(rfc,competencia);
            competencia.start();
        }else{
            System.out.println("Se ha encontrado la competencia.");
        }
        
        return competencia;
    }
    
    public void eliminarCompetencia(String rfc){
        this.competencias.remove(rfc);
    }
    
}
