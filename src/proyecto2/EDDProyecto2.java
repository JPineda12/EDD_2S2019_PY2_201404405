/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import Estructuras.TablaHash;
import Interfaz.LoginFrame;
import proyecto2.Objetos.Usuario;

public class EDDProyecto2 {

    public static void main(String[] args) {
        LoginFrame f = new LoginFrame();
        f.setLocationRelativeTo(null);
        f.show();
      /*  TablaHash tab = new TablaHash();
        
        tab.insertar(new Usuario("Admin", "12", "what"));        
        tab.insertar(new Usuario("a", "juan", "what"));
        tab.insertar(new Usuario("Juan", "juan", "what"));
        tab.insertar(new Usuario("Pal", "juan", "what"));
        tab.insertar(new Usuario("Pal2a", "juan", "what"));
        tab.insertar(new Usuario("Juan", "juan", "what"));
        tab.imprimir();
        
       Usuario us = ((Usuario)tab.buscarPorNombre("a"));
        if(us != null){
            System.out.println("User: "+us.getUsername());
            System.out.println("Pass: "+us.getPassword());
        }
        tab.insertar(new Usuario("Palom2a", "juan", "what"));*/
        }
    
}
