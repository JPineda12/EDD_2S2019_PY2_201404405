/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

import Estructuras.ArbolAVL;
import Estructuras.MatrizAdy;
import Estructuras.Nodos.Vertice;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

public class Usuario {
    
    private final String username;
    private String password;
    private final Timestamp timestamp;
    private final boolean rol;
    private MatrizAdy carpetas;
    
    public Usuario(String username, String password, Timestamp timestamp, boolean rol) {
        this.username = username;
        try {
            this.password = encriptPass(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.timestamp = timestamp;
        this.rol = rol;
        this.carpetas = new MatrizAdy();
        this.carpetas.crear_Cabeceras(0, "/");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public MatrizAdy getCarpetas(){
        return carpetas;
    }
    
    public void addCarpeta(String nombrePadre, String nombreHijo){
        Vertice p = carpetas.buscarFila(nombrePadre);
        int hijo = carpetas.cantidadCarpetas(p)+1;
        carpetas.crear_Cabeceras(hijo, nombreHijo);
        int padre = carpetas.numVertice(nombrePadre);
        String nombreNodo = "";
        if(nombrePadre.equals("/")){
            nombreNodo = "/"+nombreHijo;
        }else{
            nombreNodo = nombrePadre+"/"+nombreHijo;
        }
        CarpetaObj nueva = new CarpetaObj(nombreNodo, new ArbolAVL());
        carpetas.insertar_elemento(hijo, padre, nueva);
        
    }
    
    public String encriptPass(String password) throws NoSuchAlgorithmException{
        MessageDigest md= MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sh = new StringBuilder();
        for(byte b: digest){
            sh.append(String.format("%02x", b & 0xff));
        }
        System.out.println("Hash: "+sh.toString());
        return sh.toString();
    }
    public boolean getRol(){
        return rol;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }    

    public String getTimestamp() {
        return timestamp.toString();
    }
    
}
