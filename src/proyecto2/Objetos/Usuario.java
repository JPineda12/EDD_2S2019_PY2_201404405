/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

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

    public Usuario(String username, String password, Timestamp timestamp, boolean rol) {
        this.username = username;
        try {
            this.password = encriptPass(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.timestamp = timestamp;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
