/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Estructuras.Nodos.NodoPila;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto2.Objetos.Registro;

/**
 *
 * @author brest12
 */
public class Pila {
    
    
    NodoPila inicio;
    int size;
    
    public Pila(){
        inicio = null;
        size = 0;
    }
    
    public boolean push(Object dato){
      if(dato == null){
          return false;
      }
      NodoPila nuevo = new NodoPila(dato);
      if(inicio == null){
            inicio = nuevo;
            size++;
            return true;
      }
      
      nuevo.setNext(inicio);
      this.inicio = nuevo;
      size++;
      return true;
    }
    
    public Object pop(){
        NodoPila temp = inicio;
        
        inicio = inicio.getNext();
        size--;
        return temp;
    }
    
    public Object peek(){
        return inicio;
    }
    
    public int getSize(){
        return size;
    }
    
    public void printpopRegistro(){
        while(size > 0){
            System.out.println("1. "+((Registro)inicio.getDato()).getOperacion());
            pop();
        }
    }
    
    public boolean graficar() {
        if (size > 0) {
            File archivo = new File("Reports/pila.dot");
            try {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                    writer.write(llenarDot());

                }

                ProcessBuilder publicar;
                publicar = new ProcessBuilder("dot", "-Tpng", "-o", "Reports/pila.png", "Reports/pila.dot");
                publicar.redirectErrorStream(true);
                publicar.start();

            } catch (IOException ex) {
                Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    private String llenarDot(){
        String archivo = "";
        archivo += "digraph stack{\n";
        archivo += "    node [shape=record];\n";
        archivo += "    pila[label=\"{";
        NodoPila temp = inicio;
        Registro r;
        while(temp != null){
            r = (Registro)temp.getDato();
            archivo += "|"+r.getFecha()+" "+r.getHora();
            archivo += "\\nOperacion: "+r.getOperacion();
            archivo += "\\nUsuario:"+r.getUsuario();
            temp = temp.getNext();
        }
        archivo += "}\"];\n";
        archivo += "}";
        return archivo;
    }
}
