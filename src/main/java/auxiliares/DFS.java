/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import com.mycompany.practicografos.Grafo;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author user
 */
public class DFS {
    private List<Integer> recorrido;
    private Grafo grafo;
    private UtilsRecorrido controlMarcado;
    public DFS(Grafo unGrafo, int posVerticePartida) {
        this.grafo = unGrafo;
        recorrido = new ArrayList<>();
        controlMarcado = new UtilsRecorrido(grafo.cantidadDeVertices());
        procesarDFS(posVerticePartida);
    }
     public void procesarDFS(int posVertice){
        controlMarcado.marcarVertice(posVertice);
        recorrido.add(posVertice);
        Iterable<Integer> adyacentesEnTurno = grafo.adyacentesDeVertices(posVertice);
            for (Integer posVerticeAdyacente : adyacentesEnTurno) {
                if (!controlMarcado.estaMarcado(posVerticeAdyacente)) {
                procesarDFS(posVerticeAdyacente);
                }
            }
    }
    public int siguienteNoMarcado(){
        int verticeNoMarcado = 0;
        while(controlMarcado.estaMarcado(verticeNoMarcado)){
        verticeNoMarcado++;
        }
        return verticeNoMarcado;
    }
   
    public boolean hayCaminoA(int posVertice){
         return controlMarcado.estaMarcado(posVertice);  
    }
    public Iterable<Integer> elRecorrido(){
        return recorrido;
    }
    
    public boolean hayCaminoAtodos(){
        return this.controlMarcado.estanTodosMarcados();
    }
}
