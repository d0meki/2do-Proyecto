/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practicografos;

import auxiliares.MatrizDeCamino;
import auxiliares.UtilsRecorrido;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Digrafos extends Grafo {
    public Digrafos(){
     super();
    }
    /*1. En un grafo dirigido implementar un algoritmo para encontrar si el grafo tiene ciclos*/
    public boolean existeCiclo(){
        MatrizDeCamino aux = new MatrizDeCamino(this);
        int[][] MC = aux.getMatrizCamino();
        boolean existe = false;
        for (int i = 0; i < MC.length; i++) {
            for (int j = 0; j < MC[i].length; j++) {
                if (i == j && MC[i][j]==1) {
                    existe = true;
                    break;
                }
            }
        }
      return existe;
    }
    /*2. En un grafo dirigido implementar un algoritmo para encontrar si es débilmente conexo*/
    public boolean esDebilmenteConexo(){
         boolean esdebil = true;
         int vertice = 0;
         UtilsRecorrido marcadoVertice = new UtilsRecorrido(this.cantidadDeVertices());
          marcadoDFS(this,0,marcadoVertice);
         do{
            if (marcadoVertice.estanTodosMarcados()) {
                esdebil = true; 
                break;
            }else{
                vertice = this.buscarUnVerticeNoMarcadoPeroConUnAdyacenteMarcado(marcadoVertice);
                if (vertice == -1) {
                    esdebil = false; 
                    break;
                } else {
                    this.marcadoDFS(this, vertice, marcadoVertice);
                }
            }
         }
         while(vertice <= this.cantidadDeVertices());
        return esdebil;
     }
    public int cantidadDeVertices(){
        return this.listaDeAdyacencia.size();
    }
    private void marcadoDFS(Grafo unDiGrafo, int posicionDeVertice, UtilsRecorrido marcadorVertice) {
       marcadorVertice.marcarVertice(posicionDeVertice);
        List<Integer> adyacenciaEnTurno = unDiGrafo.listaDeAdyacencia.get(posicionDeVertice);
        for (Integer posicionAdyacente : adyacenciaEnTurno) {
            if (!marcadorVertice.estaMarcado(posicionAdyacente)) {
                marcadoDFS(unDiGrafo,posicionAdyacente,marcadorVertice);
            }
        }
    }
    private int buscarUnVerticeNoMarcadoPeroConUnAdyacenteMarcado(UtilsRecorrido marcadorVertice){
         for (int i = 0; i < marcadorVertice.size() ; i++) {
            //Boolean verticeMarcado = marcador.get(i);
            Boolean verticeMarcado = marcadorVertice.estaMarcado(i);
            if (Objects.equals(verticeMarcado, Boolean.FALSE)) {
               List<Integer> adyacencia = this.listaDeAdyacencia.get(i);
                for (Integer pos : adyacencia) {
                    if (Objects.equals(marcadorVertice.estaMarcado(pos), Boolean.TRUE)) {
                        return i;
                    }
                }
            }
        }
         return -1;
     }
    /*5. En un grafo dirigido implementar un algoritmo para encontrar el número de islas que hay 
        en el grafo*/
    public int cantidadDeIslas(){
         int contadorIslas = 0;
        if (this.cantidadDeVertices()>0) {
           contadorIslas++;
            UtilsRecorrido marcadoVertice = new UtilsRecorrido(this.cantidadDeVertices());
            marcadoDFS(this,0,marcadoVertice);
            while(!marcadoVertice.estanTodosMarcados()){
                int verticeNoMarcado = this.buscarUnVerticeNoMarcadoPeroConUnAdyacenteMarcado(marcadoVertice);
                if (verticeNoMarcado == -1) {
                    contadorIslas++;
                    verticeNoMarcado = this.buscarVerticeNoMarcado(marcadoVertice);
                }
                marcadoDFS(this,verticeNoMarcado, marcadoVertice);
            }
        }
    return contadorIslas;
    }
    private int buscarVerticeNoMarcado(UtilsRecorrido marcador) {
        for (int i = 0; i < marcador.size(); i++) {
            Boolean marca = marcador.estaMarcado(i);
            if (Objects.equals(marca, Boolean.FALSE)) {
                return i;
            }
        }
        return -1;
    }
    /*6. En un grafo dirigido implementar el algoritmo de Warshal, que luego muestre entre que 
        vértices hay camino*/
    private void warshall(){
       int[][] matrizWarshall = MatrizAdyacencia();
        for (int k = 0; k < matrizWarshall.length; k++) {
            for (int i = 0; i < matrizWarshall.length; i++) {
                for (int j = 0; j < matrizWarshall.length; j++) {
                
                    matrizWarshall[i][j] = matrizWarshall[i][j] | (matrizWarshall[i][k] & matrizWarshall[k][j]);
                
                }
            }
        }
    }
    private int[][] MatrizAdyacencia(){
        int n = this.listaDeAdyacencia.size();
        int[][] matrizWarshall = new int[n][n];
        for (int[] M1 : matrizWarshall) {
            for (int y = 0; y < M1.length; y++) {
                M1[y] = 0;
            }
        }
        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
             List<Integer> L = this.listaDeAdyacencia.get(i);
             for (int j = 0; j < L.size(); j++) {
                matrizWarshall[i][L.get(j)] = 1;
            }
        }
        return matrizWarshall;
    }
}
