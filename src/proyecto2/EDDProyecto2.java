/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import Estructuras.ArbolAVL;
import Estructuras.TablaHash;
import Interfaz.LoginFrame;
import Interfaz.Principal;
import proyecto2.Objetos.ArchivoObj;
import proyecto2.Objetos.Usuario;

public class EDDProyecto2 {

    public static void main(String[] args) {
       /*Principal p = new Principal("paloma");
       p.show();*/
        /*LoginFrame f = new LoginFrame();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        /*TablaHash tab = new TablaHash();
       
       tab.insertar(new Usuario("Brandon Alvarez", "12", "what",true));        
       tab.insertar(new Usuario("Antonio Gramajo", "juan", "what",true));
       tab.insertar(new Usuario("Abel Gutierrez", "juan", "what",true));
       tab.insertar(new Usuario("Alumno c", "juan", "what",true));
       tab.insertar(new Usuario("Jimmy Garcia", "juan", "what",true));
       tab.insertar(new Usuario("Jose Lopez", "juan", "what",false));
       tab.insertar(new Usuario("random", "juan", "what",false));
       tab.insertar(new Usuario("random3", "juan", "what",false));
       tab.insertar(new Usuario("riksa3", "juan", "what",false));
       tab.insertar(new Usuario("joh3", "juan", "what",false));

        //tab.graficar();
        tab.imprimir();
        */
        
        ArbolAVL t = new ArbolAVL();
        t.insert(new ArchivoObj("Asnaeb", "lol que cosas verdad"));
        t.insert(new ArchivoObj("Cacao", "nena is sleeping"));
        t.insert(new ArchivoObj("Dedo", "I like turtles"));
        t.insert(new ArchivoObj("Infancia", "verdad que si"));
        t.insert(new ArchivoObj("Tolteca", "Rambutanes are mine"));
        t.insert(new ArchivoObj("Impressive", "Weird thinking"));
        t.insert(new ArchivoObj("ignorant", "This is some stuff"));
        
       t.generateGraph();
    }
    
}
