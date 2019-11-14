/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Estructuras.Nodos.NodoLista;
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
            NodoLista newNode = new NodoLista(data);
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

    public boolean insertErr(Object data) {
        if (data == null) {
            return false;
        }
        if (!containsErr(data)) {
            NodoLista newNode = new NodoLista(data);
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
            NodoLista newNode = new NodoLista(data);
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
}
