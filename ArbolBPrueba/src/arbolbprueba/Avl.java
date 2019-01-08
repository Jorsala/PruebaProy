/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbprueba;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author George
 */

//Nodo del Arbol AVL
class NodoAvl {
    public int CarnetTutorAcademico;
    public String id;
    public String Nombre;
    public String pass;
    
    int balance;
    NodoAvl Izquierda;
    NodoAvl Derecha;
    NodoAvl padre;
    public NodoAvl(){
        this.CarnetTutorAcademico = 0;
        this.id = "";
        this.Nombre = "";
        this.pass = "";
        
        Izquierda = null;
        Derecha = null;
    }
    public NodoAvl (int CarnetTutorAcademico, String Curso,String Nombre, String Periodo){//, MatrizControlNotas HojaControlNotas, TablaHashEstudiantes ListadoEstudiantes) {
        this.CarnetTutorAcademico = CarnetTutorAcademico;
        this.id = Curso;
        this.Nombre = Nombre;
        this.pass = Periodo;
        
        this.Izquierda = null;
        this.Derecha = null;
    }
    public NodoAvl BuscarEstudiante(int CarneEstudiante){
        if (CarneEstudiante == CarnetTutorAcademico){
            return this;
        }else if (CarneEstudiante < CarnetTutorAcademico){
                if (Izquierda != null)
                    return Izquierda.BuscarEstudiante(CarneEstudiante);
                else
                    return null;
        }else if (CarneEstudiante > CarnetTutorAcademico){
                if (Derecha != null)
                    return Derecha.BuscarEstudiante(CarneEstudiante);
                else
                    return null;
        }
        return this;
    }
}
//fin


public class Avl {
    NodoAvl nuevo, raiz, temp;
    int altura=0;
    
    public Avl(){
        nuevo = null;
        raiz = null;
    }
    
    public void InsertarTutor(int CarnetTutorAcademico, String Curso,String Nombre, String Periodo){
        if(raiz==null){
            this.raiz = new NodoAvl(CarnetTutorAcademico, Curso,Nombre, Periodo);//, HojaControlNotas, ListadoEstudiantes);
            this.raiz.Derecha = null;
            this.raiz.Izquierda = null;
            this.raiz.padre = null;
            this.raiz.balance = 0;
        }else{
            temp = this.raiz;
            while(temp!=null){
                if((CarnetTutorAcademico > temp.CarnetTutorAcademico)&&(temp.Derecha==null)){
                    this.nuevo = new NodoAvl(CarnetTutorAcademico, Curso,Nombre, Periodo);//, HojaControlNotas, ListadoEstudiantes);
                    this.temp.Derecha = nuevo;
                    this.nuevo.padre = temp;
                    this.nuevo.balance = 0;
                    BalancearInsercion(nuevo.padre,1);
                    break;
                }else if((CarnetTutorAcademico < temp.CarnetTutorAcademico)&&(temp.Izquierda==null)){
                    this.nuevo=new NodoAvl(CarnetTutorAcademico, Curso,Nombre, Periodo);//, HojaControlNotas, ListadoEstudiantes);
                    this.temp.Izquierda=nuevo;
                    this.nuevo.padre=temp;
                    this.nuevo.balance=0;
                    BalancearInsercion(nuevo.padre,-1);
                    break;
                }else if(CarnetTutorAcademico == temp.CarnetTutorAcademico){
                    break;
                }else if(CarnetTutorAcademico > temp.CarnetTutorAcademico)
                    this.temp=temp.Derecha;
                else
                    this.temp=temp.Izquierda;
            }
        }
    }

    public void BalancearInsercion(NodoAvl Actual, int NuevoFEquilibrio){	
        int FEquilibrio = -1;
        while(Actual!=null){
            Actual.balance = Actual.balance+NuevoFEquilibrio; 
            if(Actual.balance== 0){
                break;
            }else{
                FEquilibrio = OpcionRotacion(Actual); 
                if(FEquilibrio==0){
                    if(Actual.padre!=null){
                        if(Actual.CarnetTutorAcademico<Actual.padre.CarnetTutorAcademico){
                            NuevoFEquilibrio=-1;
                        }else{
                            NuevoFEquilibrio=1;
                        }
                    }
                    Actual=Actual.padre;
                }
                if(FEquilibrio==1){
                    RotacionSI(Actual,Actual.Derecha);
                    break;
                }
                if(FEquilibrio==2){ 
                    RotacionSD(Actual.Derecha,Actual.Derecha.Izquierda);
                    RotacionSI(Actual,Actual.Derecha);
                    break;
                }
                if(FEquilibrio==3){ 
                    RotacionSD(Actual,Actual.Izquierda);
                    break;
                }
                if(FEquilibrio==4){ 
                    RotacionSI(Actual.Izquierda,Actual.Izquierda.Derecha);
                    RotacionSD(Actual,Actual.Izquierda); 
                    break;
                }
            }
        }
    }


    public int OpcionRotacion(NodoAvl Actual){
        if(Actual.balance==2){
            if((Actual.Derecha.balance==0)||(Actual.Derecha.balance==1)){
                return 1;
            }else if(Actual.Derecha.balance==-1)
                return 2;
            }else if(Actual.balance==-2){
                if((Actual.Izquierda.balance==0)||(Actual.Izquierda.balance==-1)){
                    return 3;
                }else if(Actual.Izquierda.balance==1){
                    return 4;
                }
            }
            return 0;
    }
    public void RotacionSI(NodoAvl Actual, NodoAvl HDerecha){
        int Factor;
        int FEqui;
        this.temp = HDerecha.Izquierda;
        if(Actual == this.raiz){
            this.raiz = HDerecha;
            HDerecha.padre=null;
        }else{
            if(Actual.CarnetTutorAcademico > Actual.padre.CarnetTutorAcademico){
                Actual.padre.Derecha = HDerecha;
            }else{
                Actual.padre.Izquierda = HDerecha;
            }
            HDerecha.padre = Actual.padre;
        }
        HDerecha.Izquierda = Actual;
        Actual.Derecha = temp;
        Actual.padre = HDerecha;
        if(temp!=null){
            temp.padre = Actual;
        }
        Factor = Actual.balance;
        Actual.balance = (Factor-1)-Math.max(HDerecha.balance,0);
        FEqui = Math.min((Factor-2),(Factor+HDerecha.balance-2));
        HDerecha.balance = Math.min(FEqui,(HDerecha.balance-1));
    }
    public void RotacionSD(NodoAvl pr, NodoAvl HIzquierdo){
        int Factor;
        int FEqui;
        this.temp = HIzquierdo.Derecha;
        if(pr == raiz){
            raiz = HIzquierdo;
            HIzquierdo.padre=null;
        }else{
            if(pr.CarnetTutorAcademico>pr.padre.CarnetTutorAcademico){
                pr.padre.Derecha = HIzquierdo;
            }else{
                pr.padre.Izquierda = HIzquierdo;
            }
        }
        HIzquierdo.padre = pr.padre;
        HIzquierdo.Derecha = pr;
        pr.Izquierda=temp;
        pr.padre = HIzquierdo;
        if(this.temp!=null){
            this.temp.padre=pr;
        }
        Factor = pr.balance;
        pr.balance = (Factor+1)-Math.min(HIzquierdo.balance,0);
        FEqui = Math.min((Factor+2),(Factor-HIzquierdo.balance+2));
        HIzquierdo.balance=Math.max(FEqui,(HIzquierdo.balance+1));
    }
    public void EliminarTutor(int CarnetTutor){
        if(this.raiz!=null){
            this.temp = raiz;
            if(this.temp.CarnetTutorAcademico ==CarnetTutor){
                this.raiz = RemplazarTutor(raiz);
            }else{
                NodoAvl Actual, tmp10 = raiz;
                boolean EstudianteEncontrado = false;
                if(CarnetTutor < temp.CarnetTutorAcademico)
                    Actual = raiz.Izquierda;
                else
                    Actual = raiz.Derecha;
                while(Actual != null && !EstudianteEncontrado){
                    if(CarnetTutor==Actual.CarnetTutorAcademico){
                        EstudianteEncontrado = true;
                        if(Actual ==tmp10.Izquierda){
                            tmp10.Izquierda = RemplazarTutor(Actual);
                        }else{
                            tmp10.Derecha = RemplazarTutor(Actual);
                        }
                    }else{
                        tmp10 = Actual;
                        if(CarnetTutor < Actual.CarnetTutorAcademico)
                            Actual = Actual.Izquierda;
                        else
                            Actual = Actual.Derecha;
                    }
                }               
            }
        }
    }
    public NodoAvl RemplazarTutor (NodoAvl Tutor){
        NodoAvl ptr = null;
        if ((Tutor.Izquierda ==null)&& (Tutor.Derecha == null)){
            ptr = null;
        }else if ((Tutor.Izquierda!=null)&& (Tutor.Derecha==null)){
            ptr = Tutor.Izquierda;
        }else if ((Tutor.Izquierda ==null)&& (Tutor.Derecha != null)){
            ptr = Tutor.Derecha;
        }else{
            NodoAvl Actual = Tutor.Derecha;
            NodoAvl Est = Tutor;
            while (Actual.Izquierda != null){
                Est = Actual;
                Actual = Actual.Izquierda;
            }
            if(Tutor.Derecha==Actual){
                Actual.Izquierda = Tutor.Izquierda;
            }else{         
                Est.Izquierda= Actual.Derecha;
                Actual.Derecha = Tutor.Derecha;
                Actual.Izquierda = Tutor.Izquierda;
            }
            ptr = Actual;
        }
        return ptr;
    }

    public NodoAvl BuscarTutor(int CarnetEstudiante){
        if (this.raiz != null)
            if(this.raiz.BuscarEstudiante(CarnetEstudiante)!= null)
                return (this.raiz.BuscarEstudiante(CarnetEstudiante));
        else
            return null;
        return null;
    }       

    public void GraficarAvl(){
        String cod;
        String nodos="";
        String nodosAux = "";
        nodos += "digraph AVL{\n\tnode[shape = circle,width=0.5,height=0.5,fillcolor=\"Orange\"style=\"filled\"];\n\tedge[style = \"bold\"]";
        nodos += "subgraph g{\n";
        nodosAux = "";
        nodos += CargarNodos(this.raiz);
        nodos+= "}\n";
        nodos+= nodosAux;
        nodos += "}\n";

         // Borrar Archivo.dot
         File fichero = new File("C:\\Users\\George\\Desktop\\grafos\\AVL.dot");

        if (fichero.delete())
        System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
        System.out.println("El fichero no pudó ser borrado");

        // Generar Documento y Escribir en el mismo
        File Archivo;
        try{
            Archivo = new File("C:\\Users\\George\\Desktop\\grafos\\AVL.dot");
            if(Archivo.createNewFile()){
                System.out.println("Se a creado el archivo .dot   AVLEstudiantes");
                //Escribimos en el archivo .dot
                try (FileWriter Escribir = new FileWriter(Archivo,true)) {
                    //Escribimos en el archivo .dot
                    Escribir.write(nodos);
                    System.out.println("Se a podido escribir en el archivo .dot de  AVLEstudiantes");
                    //Cerramos la conexion del Archivo.dot
                }catch(Exception  e){
                     System.out.println("Nose a podido escribir en el archivo .dot  de AVLEstudiantes");
                }
            }            
        }catch(Exception  e){
            System.out.println("Nose a podido crear el archivo .dot de AVLEstudiantes");
        }    
        // Generar la Imagen
        try{ 
            ProcessBuilder pbuilder;
            /*
            * Realiza la construccion del comando    
            * en la linea de comandos esto es: 
            * dot -Tpng -o archivo.png archivo.dot
            */
            pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "C:\\Users\\George\\Desktop\\grafos\\AVL.png", "C:\\Users\\George\\Desktop\\grafos\\AVL.dot" );
            pbuilder.redirectErrorStream( true );
            //Ejecuta el proceso
            pbuilder.start();		    
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public String CargarNodos(NodoAvl nodo){
       String f = "";
       String structuras = "";
       String stru = "";
       if(nodo!=null){
           if(nodo.Derecha==null && nodo.Izquierda ==null){
            stru += "struct" + nodo.CarnetTutorAcademico+"[label=\" Carnet: "+nodo.CarnetTutorAcademico+"\n Curso: "+ nodo.id+"\n Periodo: "+ nodo.pass+"\"];\n";
            structuras += stru;
            stru="";
          }

        if(nodo.Izquierda !=null){
           stru += "struct" + nodo.CarnetTutorAcademico+"[label=\" Carnet: "+nodo.CarnetTutorAcademico+"\n Curso: "+ nodo.id+"\n Periodo: "+ nodo.pass+"\"];\n";
           stru += "struct" + nodo.Izquierda.CarnetTutorAcademico+"[label=\" Carnet: "+nodo.Izquierda.CarnetTutorAcademico+"\n Curso: "+ nodo.Izquierda.id+"\n Periodo: "+ nodo.Izquierda.pass+"\"];\n";
           stru += "struct"+nodo.CarnetTutorAcademico+"->struct"+nodo.Izquierda.CarnetTutorAcademico+"[label=\"Izq\"];\n";
           structuras += stru;
           stru="";
           structuras+=(CargarNodos(nodo.Izquierda));
        }
        if(nodo.Derecha !=null){
            stru += "struct" + nodo.CarnetTutorAcademico+"[label=\" Carnet: "+nodo.CarnetTutorAcademico+"\n Curso: "+ nodo.id+"\n Periodo: "+ nodo.pass+"\"];\n";
            stru += "struct" + nodo.Derecha.CarnetTutorAcademico+"[label=\" Carnet: "+nodo.Derecha.CarnetTutorAcademico+"\n Curso: "+ nodo.Derecha.id+"\n Periodo: "+ nodo.Derecha.pass+"\"];\n";
            stru += "struct"+nodo.CarnetTutorAcademico+"->struct"+nodo.Derecha.CarnetTutorAcademico+"[label=\"Der\"];\n";
            structuras += stru;
            stru="";
            structuras+=(CargarNodos(nodo.Derecha));
        }
       }
       

    return structuras;
   }
}

