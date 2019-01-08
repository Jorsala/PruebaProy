/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbprueba;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author George
 */

class NodoMatrizAdyaciencia {
    public int Fila; // Cabecera
    public int Columna; //Cabecera
    public int Ocupado; //Color del Bits
    
    
    public String CodigoCurso;
    
    
    //Punteros para mi matriz
    public NodoMatrizAdyaciencia Arriba;
    public NodoMatrizAdyaciencia Derecha;
    public NodoMatrizAdyaciencia Izquierda;
    public NodoMatrizAdyaciencia Abajo;    
}

class Encabezado {
    public int Id;
    public String Cod;
    public Encabezado Siguiente;
    public Encabezado Anteriror;
    public NodoMatrizAdyaciencia Acceso;
    
    
}

class ListaEncabezados {
    Encabezado Primero;
    Encabezado Ultimo;
    
    
    
}


public class MatrizAdyacencia {
    public ListaEncabezados Filas;
    public ListaEncabezados Columnas;
    
    NodoMatrizAdyaciencia CrearNodoMatrizOrtogonal(int CoordenadaFila, int CoordenadaColumna){
        NodoMatrizAdyaciencia Nuevo = new NodoMatrizAdyaciencia();
        Nuevo.Arriba = null;
        Nuevo.Derecha = null;
        Nuevo.Abajo = null;
        Nuevo.Izquierda = null;
        Nuevo.Fila = CoordenadaFila;
        Nuevo.Columna = CoordenadaColumna;
        return Nuevo;
    }
    
    Encabezado CrearNuevoEncabezado(int Id){
        Encabezado Nuevo = new Encabezado();
        Nuevo.Siguiente = null;
        Nuevo.Anteriror = null;
        Nuevo.Acceso = null;
        Nuevo.Id = Id;
        return Nuevo;        
    }
    
    ListaEncabezados CrearListaEncabezados(){
        ListaEncabezados Nuevo = new ListaEncabezados();
        Nuevo.Primero = null;
        Nuevo.Ultimo = null;
        return Nuevo; 
    }
    public void InsertarALista(Encabezado Nuevo, ListaEncabezados Lista){
        if(Lista.Primero == null){
            Lista.Primero = Nuevo;
            //Lista.Ultimo = Nuevo;
        }else if (Nuevo.Id < Lista.Primero.Id){
            Nuevo.Siguiente = Lista.Primero;
            Lista.Primero.Anteriror = Nuevo;
            Lista.Primero = Nuevo;
        }else{
            Encabezado tmp = Lista.Primero;
            while(tmp.Siguiente  != null){
                if(Nuevo.Id < tmp.Siguiente.Id){
                    Nuevo.Siguiente = tmp.Siguiente;
                    Nuevo.Anteriror = tmp;
                    tmp.Siguiente.Anteriror = Nuevo;
                    tmp.Siguiente = Nuevo;
                    return;
                }
                tmp = tmp.Siguiente;
            }
            if(tmp.Siguiente == null){
                tmp.Siguiente = Nuevo;
                Nuevo.Anteriror = tmp;
            }
        }
    }
    
    public Encabezado ObtenerEncabezado(int Id, ListaEncabezados Lista){
        Encabezado tmp = Lista.Primero;
        while(tmp != null){
            if(tmp.Id == Id){
                return tmp;
            }
            tmp = tmp.Siguiente;
        }
        return null;
    }

    public MatrizAdyacencia CrearMatrizOrtogonal(){
        MatrizAdyacencia Nuevo = new MatrizAdyacencia();
        Nuevo.Filas = CrearListaEncabezados();
        Nuevo.Columnas = CrearListaEncabezados();
        return Nuevo;
    }
    public void InsertarBit(int Fila, int Columna, int Ocupado, MatrizAdyacencia MatrizOrtg){
    if(MatrizOrtg != null){
        NodoMatrizAdyaciencia Nodo = BuscarEnCoordenadas(Fila,Columna,MatrizOrtg);
        if(Nodo == null){
            InsertarNodo(Fila,Columna,MatrizOrtg);
            Nodo = BuscarEnCoordenadas(Fila,Columna,MatrizOrtg);
            Nodo.Ocupado = Ocupado;  
        }else{
            Nodo.Ocupado = Ocupado;//duda
        }
    }
}
    public void InsertarNodo(int Fila, int Columna, MatrizAdyacencia MatrizOrtg){
        if(MatrizOrtg != null){
            NodoMatrizAdyaciencia Nuevo = CrearNodoMatrizOrtogonal(Fila,Columna);
            //Insertamos en la Fila
            Encabezado eFila = ObtenerEncabezado(Fila,MatrizOrtg.Filas);
            if(eFila == null){
                eFila = CrearNuevoEncabezado(Fila);
                InsertarALista(eFila,MatrizOrtg.Filas);
                eFila.Acceso = Nuevo;
            }else{
                if(Nuevo.Columna < eFila.Acceso.Columna){
                    Nuevo.Derecha = eFila.Acceso;
                    eFila.Acceso.Izquierda = Nuevo;
                    eFila.Acceso = Nuevo;
                }else{
                    NodoMatrizAdyaciencia tmp = eFila.Acceso;
                    while(tmp.Derecha != null){
                        if(Nuevo.Columna < tmp.Derecha.Columna){
                            Nuevo.Derecha = tmp.Derecha;
                            tmp.Derecha.Izquierda = tmp;
                            tmp.Derecha = Nuevo;
                            break;
                        }
                        tmp = tmp.Derecha;
                    }
                    if(tmp.Derecha == null){
                        tmp.Derecha = Nuevo;
                        Nuevo.Izquierda = tmp;
                    }
                }
            }
            //Insertar en Columna
            Encabezado eColumna = ObtenerEncabezado(Columna,MatrizOrtg.Columnas);
            if(eColumna == null){
                eColumna = CrearNuevoEncabezado(Columna);
                InsertarALista(eColumna,MatrizOrtg.Columnas);
                eColumna.Acceso = Nuevo;
            }else{
                if(Nuevo.Fila < eColumna.Acceso.Fila){
                    Nuevo.Abajo = eColumna.Acceso;
                    eColumna.Acceso.Arriba = Nuevo;
                    eColumna.Acceso = Nuevo;
                }else{
                    NodoMatrizAdyaciencia tmp2 = eColumna.Acceso;
                    while(tmp2.Abajo != null){
                        if(Nuevo.Fila < tmp2.Abajo.Fila){
                            Nuevo.Abajo = tmp2.Abajo;
                            Nuevo.Arriba = tmp2;
                            tmp2.Abajo.Arriba = Nuevo;
                            tmp2.Abajo = Nuevo;
                            break;
                        }
                        tmp2 = tmp2.Abajo;
                    }
                    if(tmp2.Abajo == null){
                        tmp2.Abajo = Nuevo;
                        Nuevo.Arriba = tmp2;
                    }

                }

            }
        }
    }
    public boolean BuscarColumnaCoordenada(int Id, ListaEncabezados Columna){
        Encabezado tmp = Columna.Primero;
        while(tmp != null){
            if(tmp.Id == Id){
                return true;
            }
            tmp = tmp.Siguiente;
        }
        return false;
    }

    public boolean BuscarFilaCoordenada(int Id, ListaEncabezados Fila){
        Encabezado tmp = Fila.Primero;
        while(tmp != null){
            if(tmp.Id == Id){
                return true;
            }
            tmp = tmp.Siguiente;
        }    
        return false;
    }
    
    public boolean BuscarColumnaCoordenada1(String Id, ListaEncabezados Columna){
        Encabezado tmp = Columna.Primero;
        while(tmp != null){
            if(tmp.Cod == Id){
                return true;
            }
            tmp = tmp.Siguiente;
        }
        return false;
    }

    public boolean BuscarFilaCoordenada1(String Id, ListaEncabezados Fila){
        Encabezado tmp = Fila.Primero;
        while(tmp != null){
            if(tmp.Cod.equals(Id)){ // antes estaba en vez de equals = 
                return true;
            }
            tmp = tmp.Siguiente;
        }    
        return false;
    }
    Encabezado ObtenerEncabezado1(String Id, ListaEncabezados Lista){
        Encabezado tmp = Lista.Primero;
        while(tmp != null){
            if(tmp.Cod.equals(Id)){// antes estaba en vez de equals = 
                return tmp;
            }
            tmp = tmp.Siguiente;
        }
        return null;
    }
    public NodoMatrizAdyaciencia BuscarEnCoordenadas1(String Fila, String Columna,int col, MatrizAdyacencia MatrizOrtg){
        if(MatrizOrtg != null){
            if(BuscarFilaCoordenada1(Fila,MatrizOrtg.Filas) && BuscarColumnaCoordenada1(Columna,MatrizOrtg.Columnas)){
                Encabezado tmp = ObtenerEncabezado1(Fila,MatrizOrtg.Filas);
                Encabezado tmp10 = ObtenerEncabezado1(Columna,MatrizOrtg.Columnas);
                NodoMatrizAdyaciencia tmp2 = tmp.Acceso;
                if(tmp10!=null){
                    while(tmp2 != null && tmp2.Columna != tmp10.Id){
                        tmp2 = tmp2.Derecha;
                    } 
                }                   
               
                return tmp2;
            }
        }
        return null;
    }
    
    NodoMatrizAdyaciencia BuscarEnCoordenadas(int Fila, int Columna, MatrizAdyacencia MatrizOrtg){
        if(MatrizOrtg != null){
            if(BuscarFilaCoordenada(Fila,MatrizOrtg.Filas) && BuscarColumnaCoordenada(Columna,MatrizOrtg.Columnas)){
                Encabezado tmp = ObtenerEncabezado(Fila,MatrizOrtg.Filas);
                NodoMatrizAdyaciencia tmp2 = tmp.Acceso;
                while(tmp2 != null && tmp2.Columna != Columna){
                    tmp2 = tmp2.Derecha;
                }
                return tmp2;
            }
        }
        return null;
    }
 public void GraficarMatrizOrtogonal(MatrizAdyacencia MatrizOrtg ){
    
    String texto = "";
    Encabezado temp;
    NodoMatrizAdyaciencia temp2;

    //archivo = fopen(cadena.c_str(),"w");

    texto+="digraph MatrizAdyaciencia{\n\tnode[shape = box];\n\tedge[style = \"bold\"];\n\tstruct0[label = \"Matriz\nAdyaciencia\" pos = \"0,0!\"];";

    temp = MatrizOrtg.Filas.Primero;
    while(temp != null){
        texto+= "\n\tstructx"+temp.Id+"[label = \"x = "+temp.Cod+"\" pos = \""+((temp.Id+1)*+80)+",0!\"];";
        temp = temp.Siguiente;
    }

    temp = MatrizOrtg.Columnas.Primero;
    while(temp != null){
        texto+= "\n\tstructy"+temp.Id+"[label = \"y = "+temp.Cod+"\" pos = \"0,"+((temp.Id+1)*-80)+"!\"];";
        temp = temp.Siguiente;
    }

    temp = MatrizOrtg.Filas.Primero;
    while(temp != null){
        temp2 = temp.Acceso;
        while(temp2 != null){
            texto+= "\n\tstruct"+temp2.Fila+"N"+temp2.Columna+"[label =\" "+temp2.Ocupado+"\" pos = \""+((temp2.Fila+1)*+80)+","+((temp2.Columna+1)*-80)+"!\"];";
            temp2 = temp2.Derecha;
        }
        temp = temp.Siguiente;
    }
    
//    //Lazos de Raiz a Cabezas
//    if(MatrizOrtg.Filas.Primero != null && MatrizOrtg.Columnas.Primero != null){
//        int PrimeraFila = MatrizOrtg.Filas.Primero.Id;
//        int PrimeraColumna = MatrizOrtg.Columnas.Primero.Id;
//        texto+= "\n\tstruct0 -> structy"+PrimeraFila+";\n\tstruct0 -> structx"+PrimeraColumna+";";
//    }
//
//    //Lazos Filas
//    temp = MatrizOrtg.Filas.Primero;
//    while(temp != null && temp.Siguiente != null){
//        texto+= "\n\tstructx"+temp.Id+" -> structx"+temp.Siguiente.Id+";";
//        temp = temp.Siguiente;
//    }
//
//    //Lazos Columnas
//    temp = MatrizOrtg.Columnas.Primero;
//    while(temp != null && temp.Siguiente != null){
//        texto+="\n\tstructy"+temp.Id+" -> structy"+temp.Siguiente.Id+";";
//        temp = temp.Siguiente;
//    }
//
//    //Lazos de Cabezas a Nodo Acceso Filas
//    temp = MatrizOrtg.Columnas.Primero;
//    while(temp != null){
//        temp2 = temp.Acceso;
//        texto+="\n\tstructy"+temp.Id+" -> struct"+temp2.Fila+""+temp2.Columna+";";
//        temp = temp.Siguiente;
//    }
//
//    //Lazos de Cabezas a Nodo Acceso Columnas
//    temp = MatrizOrtg.Filas.Primero;
//    while(temp != null){
//        temp2 = temp.Acceso;
//        texto+="\n\tstructx"+temp.Id+" -> struct"+temp2.Fila+""+temp2.Columna+";";
//        temp = temp.Siguiente;
//    }
//
//    //Lazos por Filas entre Nodos
//    temp = MatrizOrtg.Filas.Primero;
//    while(temp != null){
//        temp2 = temp.Acceso;
//        while(temp2.Derecha != null){
//            texto+="\n\tstruct"+temp2.Fila+""+temp2.Columna+" -> struct"+temp2.Fila+""+temp2.Derecha.Columna+"[dir = both];";
//            temp2 = temp2.Derecha;
//        }
//        temp = temp.Siguiente;
//    }
//
//    //Lazos por Columnas entre Nodos
//    temp = MatrizOrtg.Columnas.Primero;
//    while(temp != null){
//        temp2 = temp.Acceso;
//        while(temp2.Abajo != null){
//            texto+="\n\tstruct"+temp2.Fila+""+temp2.Columna+"-> struct"+temp2.Abajo.Fila+""+temp2.Columna+"[dir = both];";
//            temp2 = temp2.Abajo;
//        }
//        temp = temp.Siguiente;
//    }
    texto += "\n}";    
   
    // Borrar Archivo.dot
        File fichero = new File("C:\\Users\\George\\Desktop\\grafos\\MatrizAdyaciencia.dot");

        if (fichero.delete())
        System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
        System.out.println("El fichero no pudó ser borrado");
     // Generar Documento y Escribir en el mismo
        File Archivo;
        try{
            Archivo = new File("C:\\Users\\George\\Desktop\\grafos\\MatrizAdyaciencia.dot");
            if(Archivo.createNewFile()){
                System.out.println("Se a creado el archivo .dot de MatrizAdyaciencia");
                //Escribimos en el archivo .dot
                try (FileWriter Escribir = new FileWriter(Archivo,true)) {
                    //Escribimos en el archivo .dot
                    Escribir.write(texto);
                    System.out.println("Se a podido escribir en el archivo .dot de MatrizAdyaciencia");
                    //Cerramos la conexion del Archivo.dot
                }catch(Exception  e){
                     System.out.println("Nose a podido escribir en el archivo .dot de MatrizAdyaciencia");
                }
            }
        }catch(IOException  e){
            System.out.println("Nose a podido crear el archivo .dot de ImagenBits");
        }
        // Generar la Imagen
        try{
            ProcessBuilder pbuilder;
	    /*
	    * Realiza la construccion del comando
	    * en la linea de comandos esto es:
	    * dot -Tpng -o archivo.png archivo.dot
	    */
	    pbuilder = new ProcessBuilder( "neato","-n", "-Tpng", "-o", "C:\\Users\\George\\Desktop\\grafos\\MatrizAdyaciencia.png", "C:\\Users\\George\\Desktop\\grafos\\MatrizAdyaciencia.dot" );
	    pbuilder.redirectErrorStream( true );
	    //Ejecuta el proceso
	    pbuilder.start();
            //Runtime rt = Runtime.getRuntime();
            //rt.exec("dot -Tpng src/ABB_Estudiantes.dot -o src/ReporteGraphivz/ABB_Estudiantes.png");
	} catch (IOException e) {
            System.out.println(e.toString());
        }
}
    
}
