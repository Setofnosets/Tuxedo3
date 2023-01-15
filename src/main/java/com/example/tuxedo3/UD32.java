package com.example.tuxedo3;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UD32 {
    public static void Crear_UD32(ArrayList<Universidad> Universidades, File archivo, String SRVCNM){
        try {
            FileWriter writer = new FileWriter(archivo);
            for (Universidad universidad : Universidades) {
                writer.write("SRVCNM\t"+SRVCNM+"\n");
                if(universidad.getCodigo() != -1){
                    writer.write("CODIGO\t"+universidad.getCodigo()+"\n");
                }
                if(universidad.getGrupo() != ""){
                    writer.write("GRUPO\t"+universidad.getGrupo()+"\n");
                }
                if(universidad.getMateria() != ""){
                    writer.write("MATERIA\t"+universidad.getMateria()+"\n");
                }
                if(universidad.getCreditos() != -1){
                    writer.write("CREDITOS\t"+universidad.getCreditos()+"\n");
                }
                if(universidad.getTrimestre() != -1){
                    writer.write("TRIMESTRE\t"+universidad.getTrimestre()+"\n");
                }
                if(universidad.getNombreProfesor() != ""){
                    writer.write("NOMBREPROFESOR\t"+universidad.getNombreProfesor()+"\n");
                }
                writer.write("\n");
                //writer.write("SRVCNM\t"+SRVCNM+"\t\rCODIGO\t"+universidad.getCodigo()+"\t\rGRUPO\t"+universidad.getGrupo()+"\t\rMATERIA\t"+universidad.getMateria()+"\t\rCREDITOS\t"+universidad.getCreditos()+"\t\rTRIMESTRE\t"+universidad.getTrimestre()+"\t\rNOMBREPROFESOR\t"+universidad.getNombreProfesor()+"\t\r\n\n<CR>");
            }
            writer.close();
        }catch (Exception e){
            System.out.println("Error al acceder al archivo");
        }
    }

    public static ArrayList<Universidad> Leer_UD32(File archivo){
        try {
            ArrayList<Universidad> universidades = new ArrayList<>();
            ArrayList<Long> Codigo = new ArrayList<>();
            ArrayList<String> Grupo = new ArrayList<>();
            ArrayList<String> Materia = new ArrayList<>();
            ArrayList<Long> Creditos = new ArrayList<>();
            ArrayList<Long> Trimestre = new ArrayList<>();
            ArrayList<String> NombreProfesor = new ArrayList<>();
            FileReader reader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;
            Pattern pattern = Pattern.compile("RTN");
            while (!pattern.matcher(linea = bufferedReader.readLine()).find()){

            }
            while ((linea = bufferedReader.readLine()) != null) {
                //If matches CODIGO
                pattern = Pattern.compile("CODIGO");
                if(pattern.matcher(linea).find()){
                    Codigo.add((Long.parseLong(linea.substring(linea.indexOf("\t")+1))));
                }
                //If matches GRUPO
                pattern = Pattern.compile("GRUPO");
                if(pattern.matcher(linea).find()){
                    Grupo.add(linea.substring(linea.indexOf("\t")+1));
                }
                //If matches MATERIA
                pattern = Pattern.compile("MATERIA");
                if(pattern.matcher(linea).find()){
                    Materia.add(linea.substring(linea.indexOf("\t")+1));
                }
                //If matches CREDITOS
                pattern = Pattern.compile("CREDITOS");
                if(pattern.matcher(linea).find()){
                    Creditos.add(Long.parseLong(linea.substring(linea.indexOf("\t")+1)));
                }
                //If matches TRIMESTRE
                pattern = Pattern.compile("TRIMESTRE");
                if(pattern.matcher(linea).find()){
                    Trimestre.add(Long.parseLong(linea.substring(linea.indexOf("\t")+1)));
                }
                //If matches NOMBRE PROFESOR
                pattern = Pattern.compile("NOMBREPROFESOR");
                if(pattern.matcher(linea).find()){
                    NombreProfesor.add(linea.substring(linea.indexOf("\t")+1));
                }
            }
            for (int i = 0; i < Codigo.size(); i++) {
                Universidad universidad = new Universidad();
                universidad.setCodigo(Codigo.get(i));
                universidad.setGrupo(Grupo.get(i));
                universidad.setMateria(Materia.get(i));
                universidad.setCreditos(Creditos.get(i));
                universidad.setTrimestre(Trimestre.get(i));
                universidad.setNombreProfesor(NombreProfesor.get(i));
                universidades.add(universidad);
            }
            bufferedReader.close();
            return universidades;
        }catch (Exception e){
            System.out.println("Error al acceder al archivo");
            return null;
        }
    }


}
