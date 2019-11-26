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
public class UsuarioError {
    private final String username;
    private final String password;
    private final int linea;
    private final int column;
    private final String mensajeError;

    public UsuarioError(String username, String password, int linea, int column, String mensajeError) {
        this.username = username;
        this.password = password;
        this.linea = linea;
        this.column = column;
        this.mensajeError = mensajeError;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumn() {
        return column;
    }

    public String getMensajeError() {
        return mensajeError;
    }
    
    
}
