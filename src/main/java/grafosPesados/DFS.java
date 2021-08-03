/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author user
 */
public class DFS {
    private List<Integer> recorrido;
    private GrafoPesado grafo;
    private UtilsRecorrido controlMarcado;
    public DFS(GrafoPesado unGrafo, int posVerticePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlMarcado = new UtilsRecorrido(grafo.cantidadDeVertices());
        controlMarcado.desmarcarTodo();
        ejecutarDFS(posVerticePartida);
    }
//    DFS recursivo    
//    public void marcadoDFS(int posVertice){       
//        controlMarcado.marcarVertice(posVertice);
//        Iterable<Integer> adyacentesEnTurno = grafo.adyacentesDeVertice(posVertice);
//        for (Integer posVerticeAdyacente : adyacentesEnTurno) {
//            if (!controlMarcado.estaMarcado(posVerticeAdyacente)) {
//                marcadoDFS(posVerticeAdyacente);
//            }
//        }
//    }  
    private void ejecutarDFS(int posVertice){
        Stack<Integer> pila = new Stack<>();;
        controlMarcado.marcarVertice(posVertice);
        pila.push(posVertice);
        //recorrido.add(posVertice);
        do{
            int posVerticeEnTurno = pila.pop(); 
            recorrido.add(posVerticeEnTurno);
            //Iterable<Integer> adyacentesEnTurno = grafo.adyacentesDeVertice(posVerticeEnTurno);
            List<AdyacenteConPeso> adyEnTurno = this.grafo.listaDeAdyacencia.get(posVerticeEnTurno);
           // for (Integer posVerticeAdyacente : adyacentesEnTurno) { por que no se hizo asi?
              for (int i = adyEnTurno.size() - 1; i >= 0; i--) {
                  AdyacenteConPeso posVerticeAdyacente = adyEnTurno.get(i);
                if (!controlMarcado.estaMarcado(posVerticeAdyacente.getIndiceVertice())) {
                    pila.push(posVerticeAdyacente.getIndiceVertice());
                    controlMarcado.marcarVertice(posVerticeAdyacente.getIndiceVertice());
                }
            }
        }while(!pila.isEmpty());
    }
    
    
    public boolean hayCaminoA(int posVertice){
         grafo.validarVertice(posVertice);   
         return controlMarcado.estaMarcado(posVertice);  
    }
    public Iterable<Integer> elRecorrido(){
        return recorrido;
    }
    
    public boolean hayCaminoAtodos(){
        return this.controlMarcado.estanTodosMarcados();
    }
}
