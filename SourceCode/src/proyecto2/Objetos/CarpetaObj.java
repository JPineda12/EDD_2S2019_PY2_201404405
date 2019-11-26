/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

import Estructuras.ArbolAVL;
import Estructuras.ListaEnlazada;

/**
 *
 * @author brest12
 */
public class CarpetaObj {
    
    private String nombre;
    private ArbolAVL archivos;
    private CarpetaObj padre;
    private ListaEnlazada hijos;
    private int nCarpeta;
    public CarpetaObj(String nombre,  ArbolAVL archivos, CarpetaObj carpetaPadre, ListaEnlazada hijos,int nCarpeta){
        this.nombre = nombre;
        this.archivos = archivos;
        this.nCarpeta = nCarpeta;
        this.padre = carpetaPadre;
        this.hijos = hijos;
    }
    
    

    public String getNombre() {
        return nombre;
    }
    
    public CarpetaObj getPadre(){
        return padre;
    }
    
    public void setPadre(CarpetaObj padre){
        this.padre = padre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ListaEnlazada getHijos(){
        return hijos;
    }
    
    public void setHijos(ListaEnlazada hijos){
        this.hijos = hijos;
    }
    
    public boolean addHijo(Object hijo){
        return hijos.insert(hijo);
    }

    public ArbolAVL getArchivos() {
        return archivos;
    }

    public void setArchivos(ArbolAVL archivos) {
        this.archivos = archivos;
    }

    public int getnCarpeta() {
        return nCarpeta;
    }

    public void setnCarpeta(int nCarpeta) {
        this.nCarpeta = nCarpeta;
    }

}
