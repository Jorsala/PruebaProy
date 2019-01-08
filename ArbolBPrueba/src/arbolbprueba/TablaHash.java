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

//Nodo de la tabla hash
class NodoHashEstudiantes {
    String Carnet;
    String Nombre;
    String Email;
    boolean UsoActual;
    NodoHashEstudiantes Siguiente;
    
    public NodoHashEstudiantes(){
        this.Carnet = "";
        this.Nombre = "";
        this.Email = "";
        this.UsoActual = false;
        this.Siguiente = null;
    }
    public NodoHashEstudiantes(String Carnet,String Nombre,String Email){
        this.Carnet = Carnet;
        this.Nombre = Nombre;
        this.Email = Email;
        this.UsoActual = true;
        this.Siguiente = null;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String Carnet) {
        this.Carnet = Carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}//fin





public class TablaHash {
    private int size;
    private int index;
    private int[] sizes;
    private int ocu;
    private float espacioUsado;
    private float factorUsado;
    int contador = 0;
//    ListaDoble listaClaves = new ListaDoble();
    
    private NodoHashEstudiantes[] ArregloTabla;
    
    public TablaHash(){
        //this.sizes = new int[] { 23, 29,37,43,47,53,59,67,73,79,83,89,97,103,107,113,127,137,149,157,167,179,197,211,227,239,251,263,277,293,311,997,1311 };
        this.sizes = new int[] { 23, 46, 92, 184, 368, 736, 1472, 2944,5888 };
        this.index = 0;
        this.ocu = 0;
        this.factorUsado = 80.0f;
        this.size = sizes[index];
        this.ArregloTabla = new NodoHashEstudiantes[size];
        this.espacioUsado = calcularEspacioUsado();
    }
    private float calcularEspacioUsado(){
        float result = ((ocu * 100) / size);
        return result;
    }
    public int funcionDePlegamiento(String clave){
        int separador = clave.length();
        int splitSize = 1, suma = 0, i;
            if(separador == 2 || separador == 4 || separador == 3 || separador == 5){
                splitSize = 2;
            }else if(separador == 6 || separador == 7){
                splitSize = 3;
            }else if(separador == 8 || separador == 9){
                splitSize = 4;
            }else if(separador >= 10){
                splitSize = 5;
            }
        for(i =0; i < clave.length(); i+= splitSize){
            if(i + splitSize <= clave.length()){
                String juntar = clave.substring(i, i + splitSize);
                if(juntar.length() == 1){
                    suma +=  (int) juntar.charAt(0);
                }else if(juntar.length() == 2){
                    suma +=  (int) juntar.charAt(0) + (int) juntar.charAt(1);
                }else if(juntar.length() == 3){
                    suma +=  (int) juntar.charAt(0) + (int) juntar.charAt(1) + (int) juntar.charAt(2);
                }else if(juntar.length() == 4){
                    suma +=  (int) juntar.charAt(0) + (int) juntar.charAt(1) + (int) juntar.charAt(2) + (int) juntar.charAt(3);
                }else if(juntar.length()==5){
                    suma +=  (int) juntar.charAt(0) + (int) juntar.charAt(1) + (int) juntar.charAt(2) + + (int) juntar.charAt(3) + (int) juntar.charAt(4);
                }
               // suma += Integer.parseInt(juntar);
            }
        }
        if(separador % splitSize != 0){
            String numeroFaltante = clave.substring(i - splitSize, separador);
            suma += (int) numeroFaltante.charAt(0);
            //suma += Integer.parseInt(numeroFaltante);
        }
        
        return suma % size;
    }
     
    
    public boolean agregarValor(String Carnet,String Nombre,String Email){
        boolean yaseagrego = false;
        if(espacioUsado <= 80.0f){
                    
           int clave = funcionDePlegamiento(Nombre); // esta con carnet cambiarla a nombre y arreglar porque hay problema
           
            System.out.println("La clave fue: " + clave);
            if(clave > size){
                clave -= size;
            }
            if(ArregloTabla[clave] == null){
                ArregloTabla[clave] = new NodoHashEstudiantes(Carnet,Nombre,Email);
                ocu++;
                espacioUsado = calcularEspacioUsado();
                //listaClaves.agregarClave(clave, Carnet);
                yaseagrego = true;
                        
            }else{
                int x = 0; //COLISIONES

                ocu++;
                espacioUsado = calcularEspacioUsado();
                NodoHashEstudiantes tmp = this.ArregloTabla[clave];
                while(tmp.Siguiente != null)
                    tmp = tmp.Siguiente;
                tmp.Siguiente = new NodoHashEstudiantes(Carnet,Nombre,Email);
                yaseagrego = true;
            }
            return yaseagrego;
        }else{
           // listaClaves = new ListaDoble();
            rehash();
            agregarValor(Carnet,Nombre,Email);
            yaseagrego = true;
            return yaseagrego;
        }
        
    }
    
     public void imprimir(){
        for(int i = 0; i < ArregloTabla.length; i++){
            if(ArregloTabla[i] != null){
                System.out.println("La clave es: " + i + " con el valor: " + ArregloTabla[i].getCarnet());
            }
            //
        }
    }
    
    public void rehash(){
        NodoHashEstudiantes[] temporal = ArregloTabla;
        int sizeTempo = size;
        if(index < sizes.length){
            index++;
            if(index == sizes.length -1){
                //Aqui nomas se alcanzo lo max
            }
        }
        size = sizes[index];
        ArregloTabla = new NodoHashEstudiantes[size];
        ocu = 0;
        espacioUsado = calcularEspacioUsado();
        for(int i =0; i<sizeTempo; i++){
            if(temporal[i] != null){
                agregarValor(temporal[i].getCarnet(), temporal[i].getNombre(),temporal[i].getEmail());
                NodoHashEstudiantes temporal2 = temporal[i].Siguiente;
                while(temporal2!=null){
                    agregarValor(temporal2.getCarnet(),temporal2.getNombre(),temporal2.getEmail());
                    temporal2 = temporal2.Siguiente;
                }
            }
        }
    }
    
    public NodoHashEstudiantes buscarValor(String Carnet){
        NodoHashEstudiantes aux = null;
        boolean bandera = false;
        for(int i = 0; i< size; i++){
            if(ArregloTabla[i] != null){
                if(ArregloTabla[i].getCarnet().equals(Carnet)){
                    aux = ArregloTabla[i];
                    bandera = true;
                }
                
            }
        }
        return aux;
    }
    
    public boolean eliminar(String Carnet){
        boolean bandera = false;
        NodoHashEstudiantes aux = null;
        
        for(int i= 0; i< size; i++){
            if(ArregloTabla[i] != null){
              if(ArregloTabla[i].getCarnet().equals(Carnet)){
                    ArregloTabla[i] = null;
                    ocu -= 1;
                    espacioUsado =  calcularEspacioUsado();
                    bandera = true;
                }   
            }
        }
        
        return bandera;
        
    }
    public  String ListaEstudiantes(NodoHashEstudiantes Nodo){
        String Cadena = "";
        int Cont =0;
        while(Nodo != null){
            if(Cont != 0){
                Cadena += " | <n";
                Cadena += String.valueOf(Cont);
                Cadena += "> " + String.valueOf(Nodo.Carnet);
                Cont++;
            }else{
                Cadena += "<n";
                Cadena += String.valueOf(Cont);
                Cadena += "> " + String.valueOf(Nodo.Carnet);
                Cont++;
            }
            Nodo = Nodo.Siguiente;
        }
        return Cadena;
    }
      public String Graficar(){
        String texto = "digraph Tabla_Libreria{\n\t";
        texto += "node[shape = record, width = .5, height = 1];\n\t";
        texto += "edge[style = \"bold\"];\n\t";
        texto += "rankdir = LR;\n\t";
        texto += "nodesep = .05;\n\t";
        
        texto += "\n\tprincipal [label = \"";
        
        for(int i = 0; i < ArregloTabla.length;i++){                // Fila Principal de Grafica
            if(i != 0){ // Si no esta vacia
                texto += " |<f";
                texto += String.valueOf(i);
                texto += "> ";
                texto += String.valueOf(i);
            }else{
                texto += "<f";
                texto += String.valueOf(i);
                texto += "> ";
                texto += String.valueOf(i);
            }
        }
        texto +=" | \",heigth = 2.5];\n\tnode [width = 1.5 height = .3];";
        
        for(int x = 0; x < ArregloTabla.length;x++){
            if(ArregloTabla[x]!=null){
               if(this.ArregloTabla[x].UsoActual){
                texto += "\n\tnode";
                texto += String.valueOf(x);
                texto +=" [label = \"{";
                texto += ListaEstudiantes(this.ArregloTabla[x]);
                texto += "}\"];";
              } 
            }            
        }
  
        //Lazo Principal a Valores
        for(int i = 0; i < ArregloTabla.length; i++){
            if(ArregloTabla[i]!=null){
                if(this.ArregloTabla[i].UsoActual){
                    texto += "\n\tprincipal:f";
                    texto += String.valueOf(i);
                    texto += " -> node";
                    texto += String.valueOf(i); 
                    texto += ":n0;";
                }
            }            
        } 
        texto += "\n}";
        // Borrar Archivo.dot
         File fichero = new File("C:\\Users\\George\\Desktop\\grafos\\TablaHash.dot");

        if (fichero.delete())
        System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
        System.out.println("El fichero no pudó ser borrado");
        
        // Generar Documento y Escribir en el mismo
        File Archivo;
        try{
            Archivo = new File("C:\\Users\\George\\Desktop\\grafos\\TablaHash.dot");
            if(Archivo.createNewFile()){
                System.out.println("Se a creado el archivo .dot de tabla hash libreria");
                //Escribimos en el archivo .dot
                try (FileWriter Escribir = new FileWriter(Archivo,true)) {
                    //Escribimos en el archivo .dot
                    Escribir.write(texto);
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
	    pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "C:\\Users\\George\\Desktop\\grafos\\TablaHash.png", "C:\\Users\\George\\Desktop\\grafos\\TablaHash.dot" );
	    pbuilder.redirectErrorStream( true );
	    //Ejecuta el proceso
	    pbuilder.start();		    
	} catch (Exception e) { e.printStackTrace(); }
      
        return texto;
    } 


}

