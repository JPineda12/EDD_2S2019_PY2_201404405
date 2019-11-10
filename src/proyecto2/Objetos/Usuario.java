/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;


public class Usuario {
    
    private final String username;
    private String password;
    private final String timestamp;
    private final boolean rol;

    public Usuario(String username, String password, String timestamp, boolean rol) {
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean getRol(){
        return rol;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }    

    public String getTimestamp() {
        return timestamp;
    }
    
}
