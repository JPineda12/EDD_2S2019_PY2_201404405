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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    double factorCarga;
    int numElementos;
    public TablaHash(){
        size = 8;
        factorCarga = 0.0;
        numElementos = 0;
        arreglo = new Object[size];
        Arrays.fill(arreglo,null);
    }
    
    private int funcionHash(String nombre){
        int valor = 0;
        for(int i = 0; i<nombre.length(); i++){
            valor += nombre.charAt(i);
        }
        int indice = valor%(size-1);
        
        return indice;
    }
    
    private int resolverColision(int indice){
        int n = 1;
        int posInicial = indice;
        indice = exploracionCuadratica(indice, 1, posInicial, false);
        if(indice == -1){
            indice = exploracionLineal(indice, 1, posInicial, false);
        }
        if(indice == -1){
            System.out.println("\n---------------------------------------------");
            System.out.println("No se pudo encontrar ninguun indice disponible");
            System.out.println("Cancelando insercion ...");
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
        if(arreglo[indice] != null){
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
        if(arreglo[indice] != null){
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
                indice = resolverColision(indice);
            }
            if(indice == -1){
                System.out.println(":( --> "+user.getUsername());
                return false;
            }
            arreglo[indice] = user;

            numElementos++;
            if(calcularCarga() >= 75.0){
                Object[] temp = arreglo;
                size = proximoNumeroPrimo(size);
                //Se asigna el nuevo tamaño al arreglo
                arreglo = new Object[size+1];
                Arrays.fill(arreglo,null);
                numElementos = 0;
                //Se insertan los datos nuevamente (para que calcule un nuevo hash)
                for (Object temp1 : temp) {
                    Usuario aux = (Usuario) temp1;
                    if(aux != null){
                        insertar(aux);
                    }
                }             
            }
            return true;
        }
        return false;
    }

    private int proximoNumeroPrimo(int sizeActual){
        BigInteger b = new BigInteger(String.valueOf(sizeActual));
        long res = Long.parseLong(b.nextProbablePrime().toString());
        return (int)res;
    }
    public double calcularCarga(){
        return (numElementos * 100) / size;
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
                return us;
            }else{
                index = buscarExpCuadratica(nombre,index, 1, index, false);
                if(index == -2){
                    return null;
                }
                if(index == -1){
                    index = buscarExpLineal(nombre, index,1, index, false);
                    if (index == -1 || index == -2) {
                        return null;
                    }
                }
                return (Usuario)arreglo[index];
            }  
        }
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
