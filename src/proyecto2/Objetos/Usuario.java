/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Objetos;

import Estructuras.ArbolAVL;
import Estructuras.ListaEnlazada;
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
        this.carpetas.crear_Cabeceras(0, "/", new CarpetaObj("/", new ArbolAVL(), null), false);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MatrizAdy getCarpetas() {
        return carpetas;
    }

    public void addCarpeta(String nombrePadre, String nombreHijo) {
        //Insertar nodo
        if (!insertInMatriz(nombreHijo, nombrePadre, carpetas, false)) {
            insertInMiddle(nombreHijo, nombrePadre, carpetas);
        }
    }

    private boolean insertInMatriz(String nombreHijo, String nombrePadre, MatrizAdy m, boolean rein) {
        Vertice p = m.buscarFila(nombrePadre);
        int hijo = m.cantidadCarpetas(p) + 1;
        int padre = m.numVertice(nombrePadre);
        CarpetaObj folder = new CarpetaObj(nombreHijo, new ArbolAVL(), nombrePadre);
        int finalx = m.crear_Cabeceras(hijo + padre, nombreHijo, folder, rein);
        if (finalx >= 0) {
            String nombreNodo = "";
            if (nombrePadre.equals("/")) {
                nombreNodo = "/" + nombreHijo;
            } else {
                nombreNodo = nombrePadre + "/" + nombreHijo;
            }
            CarpetaObj nueva = new CarpetaObj(nombreNodo, new ArbolAVL(), nombrePadre);
            m.insertar_elemento(finalx, padre, nueva);
            return true;
        }
        return false;
    }

    private void insertInMiddle(String nombreHijo, String nombrePadre, MatrizAdy m) {
        ListaEnlazada temp = new ListaEnlazada();
        Vertice p = m.buscarFila(nombrePadre);
        int hijo = m.cantidadCarpetas(p) + 1;
        int padre = m.numVertice(nombrePadre);
        CarpetaObj folder = new CarpetaObj(nombreHijo, new ArbolAVL(), nombrePadre);
        int xy = hijo + padre;
        CarpetaObj c, c2;
        for (int i = xy; i <= m.findDeepestCarpeta(); i++) {
            c = (CarpetaObj) m.buscarFila(i).getDato();
            c2 = new CarpetaObj(c.getNombre(), c.getArchivos(), c.getPadre());
            temp.insert(c2);
            m.eliminarVertice(c.getNombre());
        }
        m.crear_Cabeceras(xy, nombreHijo, folder, false);
        String nombreNodo = "";
        if (nombrePadre.equals("/")) {
            nombreNodo = "/" + nombreHijo;
        } else {
            nombreNodo = nombrePadre + "/" + nombreHijo;
        }
        CarpetaObj nueva = new CarpetaObj(nombreNodo, new ArbolAVL(), nombrePadre);
        m.insertar_elemento(xy, padre, nueva);
        for (int i = 0; i < temp.getSize(); i++) {
            c2 = temp.obtainCarpeta(i);
            if (c2 != null) {
                insertInMatriz(c2.getNombre(), c2.getPadre(), m, true);
            }
        }
    }

    public String encriptPass(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sh = new StringBuilder();
        for (byte b : digest) {
            sh.append(String.format("%02x", b & 0xff));
        }
        System.out.println("Hash: " + sh.toString());
        return sh.toString();
    }

    public boolean getRol() {
        return rol;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

}
