package org.poodb;

import controller.CursoController;
import controller.EstudianteController;
import controller.GrupoController;
import controller.ProfesorController;
import view.ConsoleView;
import view.CursoView;
import view.EstudianteView;
import view.ProfesorView;

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

    private Dimension estudianteViewSize;
    private Point estudianteViewLocation;
    private Dimension profesorViewSize;
    private Point profesorViewLocation;
    private Dimension cursoViewSize;
    private Point cursoViewLocation;

    public Main() {
        setTitle("Gestión de Academia");
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gestionEstudianteButton.addActionListener(e -> mostrarVistaEstudiante());
        gestionProfesorButton.addActionListener(e -> mostrarVistaProfesor());
        gestionCursoButton.addActionListener(e -> mostrarVistaCurso());


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                if (estudianteViewSize != null) {
                    estudianteViewSize = getSize();
                } else {
                    estudianteViewSize = new Dimension(800, 600);
                }

                if (profesorViewSize != null) {
                    profesorViewSize = getSize();
                } else {
                    profesorViewSize = new Dimension(800, 600);
                }

                if (cursoViewSize != null) {
                    cursoViewSize = getSize();
                } else {
                    cursoViewSize = new Dimension(800, 600);
                }

                if (estudianteViewLocation != null) {
                    estudianteViewLocation = getLocation();
                } else {
                    estudianteViewLocation = new Point(0, 0);
                }

                if (profesorViewLocation != null) {
                    profesorViewLocation = getLocation();
                } else {
                    profesorViewLocation = new Point(0, 0);
                }

                if (cursoViewLocation != null) {
                    cursoViewLocation = getLocation();
                } else {
                    cursoViewLocation = new Point(0, 0);
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if (estudianteViewLocation != null) {
                    estudianteViewLocation = getLocation();
                }

                if (profesorViewLocation != null) {
                    profesorViewLocation = getLocation();
                }

                if (cursoViewLocation != null) {
                    cursoViewLocation = getLocation();
                }
            }
        });
    }

    private void mostrarVistaEstudiante() {
        JFrame estudianteFrame = new JFrame("Gestión de Estudiantes");
        EstudianteView estudianteView = new EstudianteView(estudianteController, this);
        estudianteFrame.setContentPane(estudianteView);
        estudianteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estudianteFrame.setVisible(true);
        this.setVisible(false);
        estudianteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        estudianteFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (estudianteViewSize != null && estudianteViewLocation != null) {
                    estudianteFrame.setSize(estudianteViewSize);
                    estudianteFrame.setLocation(estudianteViewLocation);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                estudianteViewLocation = estudianteFrame.getLocation();
                estudianteViewSize = estudianteFrame.getSize();
                Main.this.setLocation(estudianteViewLocation);
                Main.this.setSize(estudianteViewSize);
                Main.this.setVisible(true);
            }
        });
    }
    private void mostrarVistaProfesor() {
        JFrame profesorFrame = new JFrame("Gestión de Profesores");
        ProfesorView profesorView = new ProfesorView(profesorController, this);
        profesorFrame.setContentPane(profesorView);
        profesorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profesorFrame.setVisible(true);
        this.setVisible(false);
        profesorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profesorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (profesorViewSize != null && profesorViewLocation != null) {
                    profesorFrame.setSize(profesorViewSize);
                    profesorFrame.setLocation(profesorViewLocation);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                profesorViewLocation = profesorFrame.getLocation();
                profesorViewSize = profesorFrame.getSize();
                Main.this.setLocation(profesorViewLocation);
                Main.this.setSize(profesorViewSize);
                Main.this.setVisible(true);
            }
        });
    }
    private void mostrarVistaCurso() {
        JFrame cursoFrame = new JFrame("Gestión de Cursos");
        CursoView cursoView = new CursoView(cursoController, this);
        cursoFrame.setContentPane(cursoView);
        cursoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cursoFrame.setVisible(true);
        this.setVisible(false);
        cursoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cursoFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (cursoViewSize != null && cursoViewLocation != null) {
                    cursoFrame.setSize(cursoViewSize);
                    cursoFrame.setLocation(cursoViewLocation);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                cursoViewLocation = cursoFrame.getLocation();
                cursoViewSize = cursoFrame.getSize();
                Main.this.setLocation(cursoViewLocation);
                Main.this.setSize(cursoViewSize);
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