/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbprueba;

/**
 *
 * @author George
 */
public class NodoEstudiante {
    private int codigo;
    private String destino;
    
    
    public NodoEstudiante(int Carnet, String Dpi) {
        this.codigo = Carnet;
        this.destino = Dpi;
        
    }

    public int getCarnet() {
        return codigo;
    }

    public void setCarnet(int Carnet) {
        this.codigo = Carnet;
    }

    public String getDpi() {
        return destino;
    }

    public void setDpi(String Dpi) {
        this.destino = Dpi;
    }

    
    
    
}

