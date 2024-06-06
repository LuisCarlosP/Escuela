package org.poodb;

import controller.ConexionController;
import controller.CursoController;
import view.ConsoleView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello MySQL!");

        ConsoleView consoleView = new ConsoleView();
//        ConexionController conexionController = new ConexionController(consoleView);
  //      conexionController.openConnection();

        CursoController cursoController = new CursoController(consoleView);
        String nombre ="Matematicas";
        int estado = 1;

        cursoController.agregarCurso(nombre, estado);

    }
}