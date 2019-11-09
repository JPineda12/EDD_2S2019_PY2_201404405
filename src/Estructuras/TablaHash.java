/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto2.Objetos.Usuario;

/**
 *
 * @author brest12
 */
public class TablaHash {
    Object[] arreglo;
    int size;
    
    public TablaHash(){
        size = 8;
        arreglo = new Object[8];
        Arrays.fill(arreglo,null);
    }
    
    private int funcionHash(String nombre){
        int valor = 0;
        for(int i = 0; i<nombre.length(); i++){
            valor += nombre.charAt(i);
        }
        System.out.println("Valor nombre: "+valor);
        int indice = valor%7;
        
        return indice;
    }
    private int resolverColision(int indice){
        int n = 1;
        int posInicial = indice;
        System.out.println("Usando Exploracion Cuadratica");
        System.out.println("------------------------------");
        indice = exploracionCuadratica(indice, 1, posInicial, false);
        if(indice == -1){
            System.out.println("------------------------------------------");
            System.out.println("Cambiando estrategia a: Exploracion Lineal");
            System.out.println("------------------------------------------");
            indice = exploracionLineal(indice, 1, posInicial, false);
        }
        if(indice == -1){
            System.out.println("\n---------------------------------------------");
            System.out.println("No se pudo encontrar ninguun indice disponible");
            System.out.println("Cancelando insercion...");
        }
        return indice;
    }
    private int exploracionCuadratica(int indice, int i, int posInicial, boolean flag){
        indice += i*i;
        
        if(flag && indice >= posInicial){
            return -1;
        }
        if(indice > size){
            indice = indice - size;
            flag = true;
        }
        System.out.println("New Index calculated: "+indice);
        if(arreglo[indice] != null){
            System.out.println("Not availabe... Re-Calculating....");
            indice = exploracionCuadratica(indice, i+1, posInicial, flag);
        }
        return indice;
    }
    private int exploracionLineal(int indice, int i, int posInicial, boolean flag ){
        indice += i;
        if(flag && indice >= posInicial){
            return -1;
        }
        if(indice >= size){
            indice = indice - size;
            flag = true;
        }
        System.out.println("New Index Calculated: "+indice);
        if(arreglo[indice] != null){
            System.out.println("Not available... Re-Calculating....");
            indice = exploracionLineal(indice, i+1, posInicial, flag);
        }
        return indice;
    }
    private int buscarExpLineal(String nombre, int indice, int i, int posInicial, boolean flag){
        indice += i;
        if(flag && indice >= posInicial){
            return -1;
        }
        if(indice >= size){
            indice = indice - size;
            flag = true;
        }
        System.out.println("Searching in index: "+indice);
        if(arreglo[indice] != null){
            Usuario us = (Usuario) arreglo[indice];
            if(us.getUsername().equals(nombre)){
                return indice;
            }
            return buscarExpLineal(nombre, indice, i+1, posInicial, flag);
        }
        return -2;
    }
    private int buscarExpCuadratica(String nombre, int indice, int i, int posInicial, boolean flag ){
        indice += i*i;
        if(flag && indice >= posInicial){
            return -1;
        }
        if(indice >= size){
            indice = indice - size;
            flag = true;
        }
        System.out.println("New Index Calculated: "+indice);
        if(arreglo[indice] != null){
            Usuario us = (Usuario) arreglo[indice];
            if(us.getUsername().equals(nombre)){
                return indice;
            }
            return buscarExpCuadratica(nombre, indice, i+1, posInicial, flag);
        }
        return -2;
    }
    
    public boolean insertar(Object elemento){
        Usuario user = (Usuario) elemento;
        if(!contains(user.getUsername())){
            int indice = funcionHash(user.getUsername());
            if(arreglo[indice] != null){
                System.out.print("Indice: "+indice);
                System.out.println(" not available... Re-calculando indice");
                indice = resolverColision(indice);
            }
            if(indice == -1){
                return false;
            }
            arreglo[indice] = user;
            System.out.println("Usuario insertado en el indice "+indice);
            return true;
        }
        return false;
    }
    
    public boolean contains(String nombre){
        return buscarPorNombre(nombre) != null;
    }
    
    public Usuario buscarPorNombre(String nombre){
        int index = funcionHash(nombre);
        Usuario us;       
        if(arreglo[index] != null){
            us = (Usuario) arreglo[index];
            if(us.getUsername().equals(nombre)){
                System.out.println("Se encuentra en el index: "+index);
                return us;
            }else{
                index = buscarExpCuadratica(nombre,index, 1, index, false);
                if(index == -2){
                    System.out.println("El usuario: "+nombre+" NO EXISTE cuad");
                    return null;
                }
                if(index == -1){
                    index = buscarExpLineal(nombre, index,1, index, false);
                    if (index == -1) {
                        System.out.println("El usuario: " + nombre + " NO EXISTE :/lin");
                        return null;
                    }
                }
                System.out.println("Se encuentra en el index: "+index);
                return (Usuario)arreglo[index];
            }  
        }
        System.out.println("El usuario: "+nombre+" NO existe.");
        return null;
    }
    
    public void imprimir(){
        for(int i = 0; i< size ; i++){
            System.out.print("Indice "+i+": ");
            if(arreglo[i] != null){
                Usuario us = (Usuario) arreglo[i];
                System.out.println(us.getUsername());
            }else{
                System.out.println("Null");
            }
        }
    }
    public void graficar(){
        File archivo = new File("Reports/UsersHashTable.dot");
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(llenarDot());
                
            }
            
            ProcessBuilder publicar;
            publicar = new ProcessBuilder("dot", "-Tpng", "-o", "Reports/UsersHashTable.png", "Reports/UsersHashTable.dot");
            publicar.redirectErrorStream(true);
            publicar.start();
            
        } catch (IOException ex) {
            Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private String llenarDot(){
        String cadena = "";
        cadena += "digraph G {\n";
        cadena += "     rankdir = LR;\n";
        cadena += "	graph[ranksep = \"0.02\"];\n";
        cadena += "     node [shape=record,height=.1];\n";
        cadena += "     node[style = \"filled\"];\n";
        cadena += "     sep=0;\n";
        cadena += "     nodesep=0\n";
        /*EMPIEZA CREACION DE INDICES DE LA TABLA
        /*Ejemplo de nodo:
        **       indice1[label =  "6)"];
        */
        for(int i=1; i<=size; i++){
            cadena += "     indice"+(size-i)+"[label= \""+(size-i)+")\"];\n";
        }
        //TERMINA CREACION DE INDICES DE LA TABLA
        
        
        /*EMPIEZA CREACION DE NODOS Y ENLACES DE LA TABLA HASH
        ** Ejemplo:
        ** node1 [label = "{Nombre: Pal Password: Pinguine Timestamp: 15/05/10} " width = 10];
        ** node0:f0-> node1[style=invis];
        */
        for(int i=0; i<size; i++){
            if(arreglo[i] != null){
                Usuario us = (Usuario) arreglo[i];
                cadena += "     node"+(i+1)+"[label = \"";
                cadena += "{Nombre: "+us.getUsername()+"  Password: "+us.getPassword();
                cadena += "  Timestamp: "+us.getTimestamp()+"}\" width = 10];\n";
                //Agregando enlace con node0
                cadena += "     indice"+i+"->node"+(i+1)+" [style=invis];\n";
            }else{
               //cadena += " node"+(i+1)+"[label =\""; 
            }
        }
        //ARCHIVO FINALIZADO
        cadena += "}";
        return cadena;
    }
}
