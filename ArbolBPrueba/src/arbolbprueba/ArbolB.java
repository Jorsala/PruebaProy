/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbprueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


/**
 *
 * @author George
 */


//Sirve para la division de las paginas del arbol B

class NodoDivision {
    NodoB puntero;
    Estudiante CarnetEstudiante;
    Object InformacionEstudiante;
    
    public NodoDivision(NodoB puntero, Estudiante CarnetEstudiante, Object InformacionEstudiante) {
        this.puntero = puntero;
        this.CarnetEstudiante = CarnetEstudiante;
        this.InformacionEstudiante = InformacionEstudiante;
    }
    
    public NodoB getPuntero() {
        return puntero;
    }
    
     public Estudiante getClave() {
        return CarnetEstudiante;
    }
     
    public Object getInfo() {
        return InformacionEstudiante;
    }
}//FIn


public class ArbolB {
    private NodoB raiz = null;
    private int mOrden = 2;
    private int altura = 0;
    
    public ArbolB(int M) {
        int k = ( (int) M/ 2);
        this.mOrden = k;
    }
    
    public Object buscado(String nombre){
       return raiz.buscarEstudiante(nombre);
   }
    
     public void Insertar(Estudiante id_libro, Object obj) {
        if (this.altura == 0) {
            this.raiz = new NodoB(this.mOrden, id_libro, obj);
            this.altura = 1;
            return;
        }
        
         
          NodoDivision Division = subirNodo(this.raiz, id_libro, obj, 1);

        if (Division == null) {
        } else {

            NodoB puntero = this.raiz;

            this.raiz = new NodoB(this.mOrden, Division.getClave(), Division.getInfo());
            this.raiz.punteros[0] = puntero;
            this.raiz.punteros[1] = Division.getPuntero();
            this.altura++;
        } 
     }
     
     protected NodoDivision subirNodo(NodoB Nodo, Estudiante id, Object obj, int nivel){
         NodoDivision Division = null;
         NodoB nodo_ptr;
         
        int i = 0;
        while ((i < Nodo.kHijos) && (id.mayorQue(Nodo.CarnetsEstudiante[i]))) {
            i++;
        }         
         
        if ((i < Nodo.kHijos) && id.igualA(Nodo.CarnetsEstudiante[i])) {
            Nodo.InfoCarnets[i] = obj;
            return null;
        }
        
        if (nivel < this.altura) {

            Division = subirNodo(Nodo.punteros[i], id, obj, nivel + 1);

            if (Division == null) {
                return null;
            } else {
                id = Division.CarnetEstudiante;
                obj = Division.InformacionEstudiante;
                nodo_ptr = Division.puntero;
            }
        }
        
        i = Nodo.kHijos - 1;
        while ((i >= 0) && (Nodo.CarnetsEstudiante[i] == null || id.menorQue(Nodo.CarnetsEstudiante[i]))) {
            Nodo.CarnetsEstudiante[i + 1] = Nodo.CarnetsEstudiante[i];
            Nodo.InfoCarnets[i + 1] = Nodo.InfoCarnets[i];
            Nodo.punteros[i + 2] = Nodo.punteros[i + 1];
            i--;
        }
        
        Nodo.CarnetsEstudiante[i + 1] = id;
        Nodo.InfoCarnets[i + 1] = obj;
        if (Division != null) {
            Nodo.punteros[i + 2] = Division.puntero;
        }
        Nodo.kHijos++;
         
        if (Nodo.kHijos > 2 * mOrden) {

            NodoB nuevo = new NodoB(this.mOrden);
            nuevo.punteros[this.mOrden] = Nodo.punteros[Nodo.kHijos];
            Nodo.punteros[Nodo.kHijos] = null;
            Nodo.kHijos = this.mOrden + 1;
            for (i = 0; i < this.mOrden; i++) {
                nuevo.CarnetsEstudiante[i] = Nodo.CarnetsEstudiante[i + Nodo.kHijos];
                Nodo.CarnetsEstudiante[i + Nodo.kHijos] = null;
                nuevo.InfoCarnets[i] = Nodo.InfoCarnets[i + Nodo.kHijos];
                Nodo.InfoCarnets[i + Nodo.kHijos] = null;
                nuevo.punteros[i] = Nodo.punteros[i + Nodo.kHijos];
                Nodo.punteros[i + Nodo.kHijos] = null;
            }
            Nodo.kHijos--;

            Division = new NodoDivision(nuevo, Nodo.CarnetsEstudiante[Nodo.kHijos], Nodo.InfoCarnets[Nodo.kHijos]);
            Nodo.CarnetsEstudiante[Nodo.kHijos] = null;
            Nodo.InfoCarnets[Nodo.kHijos] = null;
            nuevo.kHijos = this.mOrden;
            Nodo.kHijos = this.mOrden;

            return Division;
        }
        
         return null;
     }
     // OBJECT
     public NodoB Buscar(Estudiante id) {
        return Buscar(id, raiz);
    }
     
     public NodoB Buscar(Estudiante id_libro, NodoB NodoBuscado) {

        if ((NodoBuscado == null) || (NodoBuscado.kHijos < 1)) {
            System.err.println("error");
            return null;
        }

        if (id_libro.menorQue(NodoBuscado.CarnetsEstudiante[0])) {
            return Buscar(id_libro, NodoBuscado.punteros[0]);
        }

        if (id_libro.mayorQue(NodoBuscado.CarnetsEstudiante[NodoBuscado.kHijos - 1])) {
            return Buscar(id_libro, NodoBuscado.punteros[NodoBuscado.kHijos]);
        }

        int i = 0;
        while ((i < NodoBuscado.kHijos - 1) && (id_libro.mayorQue(NodoBuscado.CarnetsEstudiante[i]))) {
            i++;
        }

        if (id_libro.igualA(NodoBuscado.CarnetsEstudiante[i])) {
            return NodoBuscado;
        }

        return Buscar(id_libro, NodoBuscado.punteros[i]);

    }
     
   public void GraficarArbolB() throws IOException{
        String grafo = "";
        grafo += "digraph g { \n node[shape=record, color =\"orange\" ]; edge[color=skyblue4]; graph[splines=polyline];\n";
        grafo += raiz.GenerarDot();
        grafo += "}";
       // Borrar Archivo.dot
         File fichero = new File("C:\\Users\\George\\Desktop\\grafos\\arbolb.dot");

        if (fichero.delete())
        System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
        System.out.println("El fichero no pudó ser borrado");
        
        // Generar Documento y Escribir en el mismo
        File Archivo;
        try{
            Archivo = new File("C:\\Users\\George\\Desktop\\grafos\\arbolb.dot");
            if(Archivo.createNewFile()){
                System.out.println("Se a creado el archivo .dot de tabla hash libreria");
                //Escribimos en el archivo .dot
                try (FileWriter Escribir = new FileWriter(Archivo,true)) {
                    //Escribimos en el archivo .dot
                    Escribir.write(grafo);
                    System.out.println("Se a podido escribir en el archivo .dot de tabla hash libreria");
                    //Cerramos la conexion del Archivo.dot
                }catch(Exception  e){
                     System.out.println("Nose a podido escribir en el archivo .dot de tabla hash de libreria");
                }
            }            
        }catch(Exception  e){
            System.out.println("Nose a podido crear el archivo .dot de tabla hash de libreria");
        }    
        // Generar la Imagen
        try{ 
            ProcessBuilder pbuilder;
	    /*
	    * Realiza la construccion del comando    
	    * en la linea de comandos esto es: 
	    * dot -Tpng -o archivo.png archivo.dot
	    */
	    pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "C:\\Users\\George\\Desktop\\grafos\\arbolb.png", "C:\\Users\\George\\Desktop\\grafos\\arbolb.dot" );
	    pbuilder.redirectErrorStream( true );
	    //Ejecuta el proceso
	    pbuilder.start();		    
	} catch (IOException e) {}
        //return grafo;
    }
   
//   public String Hacer_Dot() {
//        String grafo = "";
//        grafo += "digraph g { \n node[shape=record, color =\"seagreen\" ]; edge[color=skyblue4]; graph[splines=polyline];\n";
//        grafo += raiz.GenerarDot();
//        grafo += "}";
//        return grafo;
//    }
}

