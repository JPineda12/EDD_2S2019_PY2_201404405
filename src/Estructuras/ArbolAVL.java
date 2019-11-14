/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Estructuras.Nodos.AVLNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto2.Objetos.ArchivoObj;




public class ArbolAVL {
    
    AVLNode root;
    int nodeCount;
    int tamanoNodos;
    public ArbolAVL(){
        this.root = null;
        this.nodeCount = 0;
    }
    
    public int treeHeight(){
        if(this.root != null){
            return this.root.getHeight();
        }
        return 0;
    }
    
    public int treeSize(){
        return nodeCount;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean contains(String cadena){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ArchivoObj f = new ArchivoObj(cadena, "who knows", time.toString(), "none");
        AVLNode temp = new AVLNode(f);
        return contains(temp);
    }
    
    public boolean contains (AVLNode node){
        if(this.root == null){
            return false;
        }
        return find(root, node);
    }
    
    
    private boolean find(AVLNode nodeCompare, AVLNode nodeToFind){
        if (nodeCompare == null){
            return false;
        }
        String comparador = ((ArchivoObj) nodeToFind.getHoja()).getNombre();
        String c2 = ((ArchivoObj)nodeCompare.getHoja()).getNombre();
        int result = comparador.compareTo(c2);
        //SAME STRING
        if(result == 0){
            return true;
        }
        //COMPARADOR IS BIGGER THAN C2
        else if(result > 0){
            return find(nodeCompare.getRight(), nodeToFind);
        }
        //COMPARADOR IS SMALLER THAN C2
        else if(result < 0){
            return find(nodeCompare.getLeft(), nodeToFind);
        }
        System.out.println("Wut");
        return false;
    }
    
    public boolean insert(ArchivoObj arch){
        if(arch == null){
            return false;
        }
        AVLNode newNode = new AVLNode(arch);
        if(arch.getContenido().length() > 40){
            tamanoNodos = 6;
        }
        if(contains(newNode)){
            return false;
        }else{
            root = insert(root, newNode);
            nodeCount++;
            
            return true;
        }
    }
    
    private AVLNode insert(AVLNode nodeCompare, AVLNode newNode){
        if(nodeCompare == null){
            return newNode;
        }
        String c1 = ((ArchivoObj)newNode.getHoja()).getNombre();
        String c2 = ((ArchivoObj)nodeCompare.getHoja()).getNombre();
        if(c1.compareTo(c2) < 0){
            nodeCompare.setLeft(insert(nodeCompare.getLeft(),newNode));
        }else if(c1.compareTo(c2) > 0){
            nodeCompare.setRight((insert(nodeCompare.getRight(),newNode)));
        }
        update(nodeCompare);
        
        return balance(nodeCompare);
    }
    
    private void update(AVLNode node){
        int leftHeight = -1;
        int rightHeight = -1;
        
        if(node.getLeft() != null){
            leftHeight = node.getLeft().getHeight();
        }
        if(node.getRight() != null){
            rightHeight = node.getRight().getHeight();
        }
        
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
        
        node.setBf(rightHeight - leftHeight);
    }
    
    private AVLNode balance(AVLNode node){
        if(node.getBf() == -2){
            if(node.getLeft().getBf() <= 0){
                return leftLeftCase(node);
            }
            else if(node.getLeft().getBf() > 0){
                return leftRightCase(node);
            }
        }
        else if(node.getBf() == 2){
            if(node.getRight().getBf() >= 0){
                return RightRightCase(node);
            }
            else if(node.getRight().getBf() < 0){
                return RightLeftCase(node);
            }
        }
        
        return node;
    }
    
    private AVLNode leftLeftCase(AVLNode node){
        return rightRotation(node);
    }
    private AVLNode leftRightCase(AVLNode node) {
        node.setLeft(leftRotation(node.getLeft()));
        return rightRotation(node);
    }

    private AVLNode RightRightCase(AVLNode node) {
        return leftRotation(node);
    }

    private AVLNode RightLeftCase(AVLNode node) {
        node.setRight(rightRotation(node.getRight()));
        return leftRotation(node);
    }
    
    private AVLNode rightRotation(AVLNode node){
        AVLNode newParent = node.getLeft();
        node.setLeft(newParent.getRight());
        newParent.setRight(node);
        update(node);
        update(newParent);
        return newParent;
    }
    
    private AVLNode leftRotation(AVLNode node){
        AVLNode newParent = node.getRight();
        node.setRight(newParent.getLeft());
        newParent.setLeft(node);
        update(node);
        update(newParent);
        return newParent;
    }
    
    
    public boolean remove(ArchivoObj arch){
        if(arch == null){
            return false;
        }
        AVLNode nodeDel = new AVLNode(arch);
        if(contains(nodeDel)){
            root = remove(root, nodeDel);
            nodeCount--;
            return true;
        }
        
        return false;
    }
    
    private AVLNode remove(AVLNode nodeCompare, AVLNode nodeDel){
        String c1 = ((ArchivoObj)nodeDel.getHoja()).getNombre();
        String c2 = ((ArchivoObj)nodeCompare.getHoja()).getNombre();
        
        if(c1.compareTo(c2) < 0){
            nodeCompare.setLeft(remove(nodeCompare.getLeft(), nodeDel));
        }
        else if(c1.compareTo(c2) > 0){
            nodeCompare.setRight(remove(nodeCompare.getRight(), nodeDel));
        }
        else{
            
            if (nodeCompare.getLeft() == null){
                return nodeCompare.getRight();
            }
            else if(nodeCompare.getRight() == null){
                return nodeCompare.getLeft();
            }
            
            else{
                if(nodeCompare.getLeft().getHeight() > nodeCompare.getRight().getHeight()){
                    AVLNode temp = findBigger(nodeCompare.getLeft());
                    nodeCompare.setHoja(temp.getHoja());
                    nodeCompare.setLeft(remove(nodeCompare.getLeft(), temp));
                }
                else if(nodeCompare.getLeft().getHeight() < nodeCompare.getRight().getHeight()){
                    AVLNode temp = findSmaller(nodeCompare.getRight());
                    nodeCompare.setHoja(temp.getHoja());
                    nodeCompare.setRight(remove(nodeCompare.getRight(), temp));
                }
            }
            
        }
        update(nodeCompare);
        return balance(nodeCompare);
    }
    
    private AVLNode findSmaller(AVLNode node){
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }
    private AVLNode findBigger(AVLNode node){
        while(node.getRight() != null){
            node = node.getRight();
        }
        return node;
    }
    
    
    
    public void generateGraph(){
        if (nodeCount > 0) {
            File archivo = new File("Reports/AVLTree.dot");
            try {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                    writer.write(llenarDot());

                }

                ProcessBuilder publicar;
                publicar = new ProcessBuilder("dot", "-Tpng", "-o", "Reports/AVLTree.png", "Reports/AVLTree.dot");
                publicar.redirectErrorStream(true);
                publicar.start();

            } catch (IOException ex) {
                Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ListaEnlazada convertirALista(){
        ListaEnlazada l = new ListaEnlazada();
        
        l = listaInorder(root, l);
        return l;
    }
    
    private ListaEnlazada listaInorder(AVLNode nodo, ListaEnlazada l){
        if(nodo.getLeft() != null){
            l = listaInorder(nodo.getLeft(), l);
        }
        
        l.insertArch(nodo.getHoja());
        
        if(nodo.getRight() != null){
            l = listaInorder(nodo.getRight(), l);
        }
        
        return l;
    }
    
    private String llenarDot(){
        String cadena = "";
        cadena += "digraph AVLTree {\n";
        cadena += "    rankdir=TB;\n";
        cadena += "    graph [pad=\".25\", ranksep=\"1.0\", nodesep=\"1\"];\n";
        cadena += "    node [shape=record, style = rounded, color = forestgreen];\n";
        cadena += "    node [width = "+tamanoNodos+", height = 1.5, fixedsize=\"true\"];\n";
        cadena += "";
        
        cadena += "    //Nodes and links creation\n";
        cadena += writeNodes(this.root);
               
        cadena += "}";
        return cadena;
    }
    
    private String writeNodes(AVLNode node){
        String cadena = "";
        if(node != null){
            ArchivoObj arch = (ArchivoObj)node.getHoja();
            cadena += "     nd"+arch.getNombre().replace(".", "")+"[label=\"<left> | Nombre: "+arch.getNombre();
            cadena += "\\nContenido: "+arch.getContenido().replace("\"", "\\\"")+"\\nBf: "+node.getBf();
            cadena += "\\nAltura: "+node.getHeight()+"\\nTimestamp: "+arch.getTimestamp();
            cadena += "\\nPropietario: "+arch.getPropietario()+" | <right>\"];\n";
            cadena += "     //Links nd"+arch.getNombre().replace(".", "")+"\n";
            ArchivoObj aux;
            if(node.getLeft() != null){
                aux = (ArchivoObj)node.getLeft().getHoja();
                cadena += "     nd"+arch.getNombre().replace(".", "")+":left->nd"+aux.getNombre().replace(".", "");
                cadena += "\n";
            }
            if(node.getRight() != null){
                aux = (ArchivoObj)node.getRight().getHoja();
                cadena += "     nd"+arch.getNombre().replace(".", "")+":right->nd"+aux.getNombre().replace(".", "");
                cadena += "\n"; 
            }

            if(node.getLeft() != null){
                cadena += writeNodes(node.getLeft());
            }
            if(node.getRight() != null){
                cadena += writeNodes(node.getRight());
            }
        }
        return cadena;
    }
}






