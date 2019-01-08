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
public class ArbolBPrueba {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        ArbolB ar = new ArbolB(5);
        ar.Insertar(new Estudiante(12), new NodoEstudiante(12, "juanito"));
        ar.Insertar(new Estudiante(13), new NodoEstudiante(13, "juanito"));
        ar.Insertar(new Estudiante(14), new NodoEstudiante(14, "juanito"));
        ar.Insertar(new Estudiante(15), new NodoEstudiante(15, "juanito"));
        ar.Insertar(new Estudiante(16), new NodoEstudiante(16, "juanito"));
        ar.Insertar(new Estudiante(17), new NodoEstudiante(17, "juanito"));
        ar.Insertar(new Estudiante(18), new NodoEstudiante(18, "juanito"));
        ar.GraficarArbolB();
        
        Avl av = new Avl();
        av.InsertarTutor(120, "Curso", "Nombre", "Periodo");
        av.InsertarTutor(12, "Curso", "Nombre", "Periodo");
        av.InsertarTutor(10, "Curso", "Nombre", "Periodo");
        av.InsertarTutor(121, "Curso", "Nombre", "Periodo");
        av.InsertarTutor(123, "Curso", "Nombre", "Periodo");
        av.GraficarAvl();
        
        
        TablaHash th = new TablaHash();
        th.agregarValor("Carnet", "Nombre", "Email");
        th.agregarValor("Carnet1", "Nombre", "Email");
        th.agregarValor("Carnet2", "Nombre", "Email");
        th.agregarValor("Carnet3", "Nombre", "Email");
        th.agregarValor("Carnet4", "Nombre", "Email");
        th.agregarValor("Carnet5", "Nombre", "Email");
        th.agregarValor("Carnet6", "Nombre", "Email");
        th.Graficar();
        
        MatrizAdyacencia mat1 = new MatrizAdyacencia();
        MatrizAdyacencia matriz = mat1.CrearMatrizOrtogonal();
        int dim = 5;
        int fil = dim;
                for(int j =0;j<dim;j++){
                     for(int i =0;i<fil;i++){
                        matriz.InsertarBit(j, i, 0, matriz);
                     }
//                    ApplicationConfig.MatrizAdy.GraficarMatrizOrtogonal(ApplicationConfig.MatrizAdy);
                }
        
        matriz.GraficarMatrizOrtogonal(matriz);
        
        
        
    
}
}