package view;

import model.CursoModel;
import model.EstudianteModel;
import model.ProfesorModel;
import model.GrupoModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleView {

    private BufferedReader in;
    private PrintStream out;
    public ConsoleView(BufferedReader in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    public ConsoleView() {
        this.in =  new BufferedReader(new InputStreamReader(System.in));;
        this.out = System.out;
    }

    public void showMessage(String message) {
        out.println(message);
    }

    public void errorMessage(String message) {
        out.println(message);
    }

    public void consultarEstudiante(EstudianteModel estudiante) {
        if (estudiante != null) {
            out.println("Detalles del estudiante:");
            out.println("ID: " + estudiante.getId());
            out.println("Nombre: " + estudiante.getNombre());
            out.println("Identificación: " + estudiante.getIdentificacion());
            out.println("Email: " + estudiante.getEmail());
            out.println("Fecha de Nacimiento: " + estudiante.getFechaNacimiento());
            String estado = estudiante.getEstado();
            out.println("Estado: " + estado);
        } else {
            out.println("Estudiante no encontrado");
        }
    }

    public void consultarProfesor(ProfesorModel profesor) {
        if (profesor != null) {
            out.println("Detalles del profesor:");
            out.println("ID: " + profesor.getId());
            out.println("Nombre: " + profesor.getNombre());
            out.println("Identificación: " + profesor.getIdentificacion());
            out.println("Email: " + profesor.getEmail());
            out.println("Departamento: " + profesor.getDepartamento());
            String estado = profesor.getEstado();
            out.println("Estado: " + estado);
        } else {
            out.println("Profesor no encontrado");
        }
    }

    public void consultarCurso(CursoModel curso) {
        if (curso != null) {
            out.println("Detalles del curso:");
            out.println("ID: " + curso.getId());
            out.println("Nombre: " + curso.getNombre());
            out.println("Descripción: " + curso.getDescripcion());
            String estado = curso.getEstado();
            out.println("Estado: " + estado);
        } else {
            out.println("Curso no encontrado");
        }
    }

    public void consultarGrupo(GrupoModel grupo) {
        if (grupo != null) {
            out.println("Detalles del grupo:");
            out.println("ID: " + grupo.getId());
            out.println("Nombre: " + grupo.getNombre());
            out.println("Descripción: " + grupo.getDescripcion());
            String estado = grupo.getEstado();
            out.println("Estado: " + estado);
        } else {
            out.println("Grupo no encontrado");
        }
    }

}