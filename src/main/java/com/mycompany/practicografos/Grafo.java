/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practicografos;

import auxiliares.DFS;
import auxiliares.UtilsRecorrido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Grafo {
    public List<List<Integer>> listaDeAdyacencia;
    public Grafo() {
        this.listaDeAdyacencia = new ArrayList<>();
    }
    public Grafo(int nroDeVerticesInicial){
        if (nroDeVerticesInicial > 0) {
            this.listaDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < nroDeVerticesInicial; i++) {
            insertarVertice();
            }
        }
        
    }
    public int cantidadDeVertices(){
        return this.listaDeAdyacencia.size();
    }
    private void insertarVertice(){
        List<Integer> adyacentesDeNuevoVertice = new ArrayList<>();
        this.listaDeAdyacencia.add(adyacentesDeNuevoVertice);
    }
    public Iterable<Integer> adyacentesDeVertices(int posVertice){
      List<Integer> adyacenciaDelVertice  = this.listaDeAdyacencia.get(posVertice);
      Iterable<Integer> iterableDeAdyacentes = adyacenciaDelVertice;
      return iterableDeAdyacentes;
    }
    /*3.- En un grafo no dirigido implementar un algoritmo para encontrar si el grafo tiene ciclo*/
    public boolean existenCiclos(){
       Grafo grafoAuxilar = new Grafo(this.cantidadDeVertices());
       UtilsRecorrido recorrido = new UtilsRecorrido(this.cantidadDeVertices());
        boolean existeUnCiclo = false;
        int verticeActual = 0;
        recorrido.marcarVertice(verticeActual);
        List<Integer> listaDeAdyacentes = (List<Integer>)this.adyacentesDeVertices(verticeActual);
        while (!recorrido.estanTodosMarcados() && !existeUnCiclo) {

            if (listaDeAdyacentes.size() == 0) {
                verticeActual = recorrido.siguienteNoMarcado();
                
            } else {
                int verticeSiguiente = listaDeAdyacentes.get(0);
                if (recorrido.estaMarcado(verticeSiguiente)) {
                    if (listaDeAdyacentes.size() < 2)
                        verticeActual = recorrido.siguienteNoMarcado();
                    else {
                        verticeSiguiente = listaDeAdyacentes.get(1);
                        if (recorrido.estaMarcado(verticeSiguiente)) {
                            existeUnCiclo = true;
                        } else {
                            verticeActual = verticeSiguiente;
                        }
                    }
                } else {
                    verticeActual = verticeSiguiente;
                }
            }

            listaDeAdyacentes = (List<Integer>) this.adyacentesDeVertices(verticeActual);
            recorrido.marcarVertice(verticeActual);
        }
        return existeUnCiclo;
    }
    /*4. En un grafo no dirigido implementar un algoritmo para encontrar el número de islas que hay 
    en el grafo*/
    public int contarIslas(int posVertice, int cantidadIslas, UtilsRecorrido controlMarcados) {
        if (controlMarcados.estanTodosMarcados())
            return cantidadIslas;

        cantidadIslas++;
        Iterable<Integer> recorridoIsla = new DFS(this, posVertice).elRecorrido();
        for (Integer verticeAMarcar : recorridoIsla) {
            controlMarcados.marcarVertice(verticeAMarcar);
        }

        for (int posVerticeNoMarcado = 0; posVerticeNoMarcado < this.listaDeAdyacencia.size(); posVerticeNoMarcado++) {
            if (!controlMarcados.estaMarcado(posVerticeNoMarcado))
                cantidadIslas = contarIslas(posVerticeNoMarcado, cantidadIslas,controlMarcados);
        }
        return cantidadIslas;
    }
    
}
