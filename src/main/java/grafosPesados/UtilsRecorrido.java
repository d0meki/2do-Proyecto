/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class UtilsRecorrido {
    private List<Boolean> marcados;
    private int nroVertices;
    public UtilsRecorrido(int nroVertices){
        marcados = new ArrayList<>();
        this.nroVertices = nroVertices;
        for (int i = 0; i < this.nroVertices; i++) {
            marcados.add(Boolean.FALSE);
        }
    }
    public int size(){
        return marcados.size();
    }
    public void desmarcarTodo(){
        marcados = new ArrayList<>();
        for (int i = 0; i < marcados.size(); i++) {
           marcados.set(i, Boolean.FALSE);
        }
    }
    public void marcarVertice(int posVertice){
        marcados.set(posVertice, Boolean.TRUE);
    }
    public boolean estaMarcado(int posVertice){   
        return marcados.get(posVertice);  
    }
    public boolean estanTodosMarcados(){
        for (Boolean marcado: this.marcados) {
            if (!marcado) {
                return false;
            }
        }
        return true;
    }
    public int siguienteNoMarcado() {
        int proximoVerticeNoMarcado = 0;
        while (estaMarcado(proximoVerticeNoMarcado) && proximoVerticeNoMarcado < nroVertices) {
            proximoVerticeNoMarcado++;
        }
        return proximoVerticeNoMarcado;
    }
}
