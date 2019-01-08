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
public class Estudiante {
     private Integer CarnetEstudiante = null;
    
     public Estudiante(int id) {
        CarnetEstudiante = new Integer(id);
    }

    public Object getEstudiante() {
        return CarnetEstudiante;
    }
    
     public boolean igualA(Estudiante pObjeto) {
        return CarnetEstudiante.equals(pObjeto.getEstudiante());
    }

    public boolean menorQue(Estudiante pObjeto) {
        return CarnetEstudiante.compareTo((Integer)pObjeto.getEstudiante()) < 0;
    }
    
    public boolean mayorQue(Estudiante pObjeto) {
        return CarnetEstudiante.compareTo((Integer)pObjeto.getEstudiante()) > 0;
    }
    
    public boolean menorOIgualQue(Estudiante pObjeto) {
        return CarnetEstudiante.compareTo((Integer)pObjeto.getEstudiante()) <= 0;
    }
  
    public boolean mayorOIgualQue(Estudiante pObjeto) {
        return CarnetEstudiante.compareTo((Integer)pObjeto.getEstudiante()) >= 0;
    }
}
