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

    public void mostrarMenuPrincipal() {
        out.println("Seleccione una opción:");
        out.println("1. Gestionar estudiantes");
        out.println("2. Gestionar profesores");
        out.println("3. Gestionar cursos");
        out.println("4. Gestionar grupos");
        out.println("5. Salir");
    }

    public void mostrarMenuEstudiante() {
        out.println("Seleccione una opción:");
        out.println("1. Agregar estudiante");
        out.println("2. Modificar estudiante");
        out.println("3. Eliminar estudiante");
        out.println("4. Consultar estudiante");
        out.println("5. Volver al menú principal");
    }

    public void mostrarMenuProfesor() {
        out.println("Seleccione una opción:");
        out.println("1. Agregar profesor");
        out.println("2. Modificar profesor");
        out.println("3. Eliminar profesor");
        out.println("4. Consultar profesor");
        out.println("5. Volver al menú principal");
    }

    public void mostrarMenuCurso() {
        out.println("Seleccione una opción:");
        out.println("1. Agregar curso");
        out.println("2. Modificar curso");
        out.println("3. Eliminar curso");
        out.println("4. Consultar curso");
        out.println("5. Volver al menú principal");
    }

    public void mostrarMenuGrupo() {
        out.println("Seleccione una opción:");
        out.println("1. Agregar grupo");
        out.println("2. Modificar grupo");
        out.println("3. Eliminar grupo");
        out.println("4. Consultar grupo");
        out.println("5. Volver al menú principal");
    }

    public int optionReader() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    public void printText(String mensaje) {
        out.println(mensaje);
    }

    public String readText() throws IOException {
        return null;
    }

}