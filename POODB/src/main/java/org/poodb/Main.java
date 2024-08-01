package org.poodb;

import controller.CursoController;
import controller.EstudianteController;
import controller.GrupoController;
import controller.ProfesorController;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JButton gestionEstudianteButton;
    private JButton gestionProfesorButton;
    private JButton gestionCursoButton;
    private JButton gestionGrupoButton;

    ConsoleView consoleView = new ConsoleView();
    EstudianteController estudianteController = new EstudianteController(consoleView);
    ProfesorController profesorController = new ProfesorController(consoleView);
    CursoController cursoController = new CursoController(consoleView);
    GrupoController grupoController = new GrupoController(consoleView);

    private Dimension sharedViewSize;
    private Point sharedViewLocation;

    public Main() {
        setTitle("Gestión de Academia");
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gestionEstudianteButton.addActionListener(e -> mostrarVistaEstudiante());
        gestionProfesorButton.addActionListener(e -> mostrarVistaProfesor());
        gestionCursoButton.addActionListener(e -> mostrarVistaCurso());
        gestionGrupoButton.addActionListener(e -> mostrarVistaGrupo());

        sharedViewSize = getSize();
        sharedViewLocation = getLocation();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                sharedViewSize = getSize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                sharedViewLocation = getLocation();
            }
        });
    }

    private void mostrarVistaEstudiante() {
        JFrame estudianteFrame = new JFrame("Gestión de Estudiantes");
        EstudianteView estudianteView = new EstudianteView(estudianteController, this);
        estudianteFrame.setContentPane(estudianteView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        estudianteFrame.setSize(sharedViewSize);
        estudianteFrame.setLocation(sharedViewLocation);
        estudianteFrame.setVisible(true);
        this.setVisible(false);
        estudianteFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                sharedViewLocation = estudianteFrame.getLocation();
                sharedViewSize = estudianteFrame.getSize();
                Main.this.setLocation(sharedViewLocation);
                Main.this.setSize(sharedViewSize);
                Main.this.setVisible(true);
            }
        });
    }

    private void mostrarVistaProfesor() {
        JFrame profesorFrame = new JFrame("Gestión de Profesores");
        ProfesorView profesorView = new ProfesorView(profesorController, this);
        profesorFrame.setContentPane(profesorView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profesorFrame.setSize(sharedViewSize);
        profesorFrame.setLocation(sharedViewLocation);
        profesorFrame.setVisible(true);
        this.setVisible(false);
        profesorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                sharedViewLocation = profesorFrame.getLocation();
                sharedViewSize = profesorFrame.getSize();
                Main.this.setLocation(sharedViewLocation);
                Main.this.setSize(sharedViewSize);
                Main.this.setVisible(true);
            }
        });
    }

    private void mostrarVistaCurso() {
        JFrame cursoFrame = new JFrame("Gestión de Cursos");
        CursoView cursoView = new CursoView(cursoController, this);
        cursoFrame.setContentPane(cursoView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cursoFrame.setSize(sharedViewSize);
        cursoFrame.setLocation(sharedViewLocation);
        cursoFrame.setVisible(true);
        this.setVisible(false);
        cursoFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                sharedViewLocation = cursoFrame.getLocation();
                sharedViewSize = cursoFrame.getSize();
                Main.this.setLocation(sharedViewLocation);
                Main.this.setSize(sharedViewSize);
                Main.this.setVisible(true);
            }
        });
    }

    private void mostrarVistaGrupo() {
        JFrame grupoFrame = new JFrame("Gestión de Grupos");
        GrupoView grupoView = new GrupoView(grupoController, this);
        grupoFrame.setContentPane(grupoView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grupoFrame.setSize(sharedViewSize);
        grupoFrame.setLocation(sharedViewLocation);
        grupoFrame.setVisible(true);
        this.setVisible(false);
        grupoFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                sharedViewLocation = grupoFrame.getLocation();
                sharedViewSize = grupoFrame.getSize();
                Main.this.setLocation(sharedViewLocation);
                Main.this.setSize(sharedViewSize);
                Main.this.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}