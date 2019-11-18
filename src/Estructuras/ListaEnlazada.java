/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Estructuras.Nodos.NodoLista;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto2.Objetos.ArchivoObj;
import proyecto2.Objetos.CarpetaObj;
import proyecto2.Objetos.UsuarioError;

/**
 *
 * @author brest12
 */
public class ListaEnlazada {

    NodoLista head;
    int size;

    public ListaEnlazada() {
        head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public NodoLista getHead() {
        return head;
    }

    public boolean insert(Object data) {
        if (data == null) {
            return false;
        }
        if (!contains(data)) {
            NodoLista newNode = new NodoLista(data, size);
            if (head == null) {
                head = newNode;
                size++;
                return true;
            }

            NodoLista aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            size++;
            return true;
        }
        return false;
    }
    
    public CarpetaObj obtainCarpeta(int i){
        NodoLista temp = head;
        int n = 0;
        while(temp != null){
            if(n == i){
                return (CarpetaObj)temp.getData();
            }
            temp = temp.getNext();
            n++;
        }
        return null;
    }
    
    public boolean deleteCarpeta(int pos){
        if(head == null){
            return false;
        }
        NodoLista temp = head;
        if(pos == 0){
            head = temp.getNext();
            return true;
        }
        for(int i = 0; i< pos-1 ; i++){
            if(temp != null){
                temp = temp.getNext();
            }
        }
        
        if(temp == null || temp.getNext() == null){
            return false;
        }
        
        NodoLista next = temp.getNext().getNext();
        size--;
        next.setN(size);
        temp.setNext(next);
        return true;
    }
    public boolean insertErr(Object data) {
        if (data == null) {
            return false;
        }
        if (!containsErr(data)) {
            NodoLista newNode = new NodoLista(data, size);
            if (head == null) {
                head = newNode;
                return true;
            }

            NodoLista aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            size++;
            return true;
        }
        return false;
    }

    public boolean insertArch(Object data) {
        if (data == null) {
            return false;
        }
        if (!containsArch(data)) {
            NodoLista newNode = new NodoLista(data, size);
            if (head == null) {
                head = newNode;
                return true;
            }

            NodoLista aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            size++;
            return true;
        }
        return false;
    }

    public boolean containsErr(Object data) {
        NodoLista aux = head;
        UsuarioError c;
        while (aux != null) {
            c = (UsuarioError) aux.getData();
            if (((UsuarioError) data).getUsername().equals(c.getUsername())) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean contains(Object data) {
        NodoLista aux = head;
        CarpetaObj c;
        while (aux != null) {
            c = (CarpetaObj) aux.getData();
            if (((CarpetaObj) data).getNombre().equals(c.getNombre())) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean containsArch(Object data) {
        NodoLista aux = head;
        ArchivoObj c;
        while (aux != null) {
            c = (ArchivoObj) aux.getData();
            if (((ArchivoObj) data).getNombre().equals(c.getNombre())) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public void imprimir() {
        NodoLista aux = head;
        CarpetaObj c;
        System.out.println("----------------------");
        while (aux != null) {
            c = (CarpetaObj) aux.getData();
            System.out.println(c.getNombre());
            aux = aux.getNext();
        }
        System.out.println("----------------------");
    }
    
    public boolean graficar() {
        if (size > 0) {
            File archivo = new File("Reports/Grafo.dot");
            try {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                    writer.write(llenarDot());

                }

                ProcessBuilder publicar;
                publicar = new ProcessBuilder("dot", "-Tpng", "-o", "Reports/Grafo.png", "Reports/Grafo.dot");
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
        archivo += "digraph grafo{\n";
        //CREACION DE NODOS
        NodoLista temp = head;
        CarpetaObj c = (CarpetaObj)temp.getData();
        //Enlaces
        archivo += escribirHijos(c, null);
        archivo += "}";
           
        return archivo;
    }
    
    private String escribirHijos(CarpetaObj c, String nombrePadre){
        String archivo = "";
        String nombreNodo = "";
        CarpetaObj c2;
        if(nombrePadre != null){
            if(c.getPadre().getNombre().equals("/")){
                nombreNodo = "raiz_"+c.getNombre();
                System.out.println("padre: "+c.getPadre().getNombre());                
            }else{
                nombreNodo = nombrePadre+"_"+c.getNombre();
                System.out.println("pad: "+c.getPadre().getNombre());
            }
        }else{
            nombreNodo = "raiz";
        }
        
        System.out.println("nombre: "+c.getNombre());
        System.out.println("Node: "+nombreNodo);
        //Escribe El nodo
        archivo += "    "+nombreNodo+"[label=\""+c.getNombre()+
                "\\ncarpetas: "+c.getHijos().getSize()+"\"];\n";
        //Escribir las relaciones con sus hijos
        System.out.println("carpetas: "+c.getHijos().getSize());
        String nombreAux;
        if(c.getHijos().getSize() > 0){
            for(int i = 0; i< c.getHijos().getSize(); i++){
                c2 = c.getHijos().obtainCarpeta(i);
                archivo += "    "+nombreNodo+"->"+nombreNodo+"_"+c2.getNombre()+";\n";
            }
            
            for(int k = 0; k< c.getHijos().getSize(); k++){
                archivo += escribirHijos(c.getHijos().obtainCarpeta(k), nombreNodo);
            }
        }
        return archivo;
    }
}
