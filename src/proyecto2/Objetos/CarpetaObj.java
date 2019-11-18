/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

import Estructuras.ArbolAVL;

/**
 *
 * @author brest12
 */
public class CarpetaObj {
    
    private String nombre;
    private ArbolAVL archivos;
    private String padre;
    private int xy;
    public CarpetaObj(String nombre,  ArbolAVL archivos, String nombrePadre){
        this.nombre = nombre;
        this.archivos = archivos;
        this.padre = nombrePadre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getPadre(){
        return padre;
    }
    
    public void setPadre(String padre){
        this.padre = padre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArbolAVL getArchivos() {
        return archivos;
    }

    public void setArchivos(ArbolAVL archivos) {
        this.archivos = archivos;
    }

}
