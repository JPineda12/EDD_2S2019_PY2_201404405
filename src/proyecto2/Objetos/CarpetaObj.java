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
    
    public CarpetaObj(String nombre, ArbolAVL archivos){
        this.nombre = nombre;
        this.archivos = archivos;
    }

    public String getNombre() {
        return nombre;
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
