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
public class NodoB {
    int claveMin; //guardare la cantidad de claves minimas en un nodo
    int kHijos;   //guardare los hijos dentro de un nodo 
    public NodoB[] punteros;
    public Object[] InfoCarnets;
    public Estudiante[] CarnetsEstudiante;

    public NodoB(int cMin) {
        this.claveMin = cMin;
        kHijos = 0;
        CarnetsEstudiante = new Estudiante[2 * cMin + 1];
        InfoCarnets = new Object[2 * cMin + 1];
        punteros = new NodoB[(2 * cMin) + 2];
    }

    public NodoB(int cMin, Estudiante Carnet, Object Informacion) {
        this(cMin);
        kHijos = 1;
        CarnetsEstudiante[0] = Carnet;
        InfoCarnets[0] = Informacion;
    }

    public void setClaveMin(int cMin) {
        this.claveMin = cMin;
    }

    public int getClaveMin() {
        return claveMin;
    }

    public String get_Nombre_dot() {
        return "ArbolB_Estudiantes" + this.hashCode();
    }

    public Object buscarEstudiante(String nombre) {
//        boolean encontrado = false;
        //String json = "[";
        for (int i = 0; i < kHijos; i++) {
            String NombreEstudiante = ((NodoEstudiante) InfoCarnets[i]).getDpi().trim();
            if (nombre.trim().equals(((NodoEstudiante) InfoCarnets[i]).getDpi().trim())) {
                  return InfoCarnets[i];
//                json += "{";
//                json += "'Carnet':'" + CarnetsEstudiante[i].getEstudiante().toString() + "',";
//                json += "'Dpi':'" + ((NodoEstudiante) InfoCarnets[i]).getDpi() + "',";
//                json += "'Password':'" + ((NodoEstudiante) InfoCarnets[i]).getPassword() + "',";
//                json += "'Token':'" + ((NodoEstudiante) InfoCarnets[i]).getToken() + "',";
//                json += "'Nombre':'" + ((NodoEstudiante) InfoCarnets[i]).getNombre() + "',";
//                json += "'Apellido':'" + ((NodoEstudiante) InfoCarnets[i]).getApellido() + "',";
//                json += "'No_Creditos':'" + ((NodoEstudiante) InfoCarnets[i]).getNo_Creditos() + "',";
//                json += "'Correo':'" + ((NodoEstudiante) InfoCarnets[i]).getCorreoElectronico() + "'},";

//                encontrado = true;
                //return "Estudiante ya Existente";
            }
        }
//        if(encontrado) {
//            json = json.substring(0,json.length()-1);
//            json +="]"; 
//            return json;
//        } else {
//            return null;
//        }
        return null;
    }

    public String GenerarDot() {

        StringBuilder b = new StringBuilder();

        b.append(get_Nombre_dot());
        b.append("[label=\"<P0>");
        for (int i = 0; i < kHijos; i++) {
            b.append("|{ Codigo:" + CarnetsEstudiante[i].getEstudiante().toString() + "|{\\lDestino: " + ((NodoEstudiante) InfoCarnets[i]).getDpi() + "\\l}}");
            b.append("|<P" + (i + 1) + ">");
        }

        b.append("\"];\n");

        for (int i = 0; i <= kHijos; i++) {
            if (punteros[i] != null) {
                b.append(punteros[i].GenerarDot());
                b.append(get_Nombre_dot() + ":P" + i + " -> " + punteros[i].get_Nombre_dot() + ";\n");
            }
        }

        return b.toString();

    }
}
