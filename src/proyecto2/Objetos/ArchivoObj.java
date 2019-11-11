/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

/**
 *
 * @author brest12
 */
public class ArchivoObj {
    
    private String nombre;
    private String contenido;

    public ArchivoObj(String nombre, String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
