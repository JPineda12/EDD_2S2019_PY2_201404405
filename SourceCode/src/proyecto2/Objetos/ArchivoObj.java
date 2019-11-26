/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

import java.sql.Timestamp;

/**
 *
 * @author brest12
 */
public class ArchivoObj {
    
    private String nombre;
    private String contenido;
    private final String timestamp;
    private final String propietario;

    public ArchivoObj(String nombre, String contenido, String timestamp, String propietario) {
        this.nombre = nombre;
        this.contenido = contenido;
        this.timestamp = timestamp;
        this.propietario = propietario;
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
    
    public String getTimestamp(){
        return timestamp;
    }
    
    public String getPropietario(){
        return propietario;
    }
    
}
