package org.poodb;

import controller.CursoController;
import controller.EstudianteController;
import controller.GrupoController;
import controller.ProfesorController;
import view.ConsoleView;
import view.EstudianteView;

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

    public Main() {
        setTitle("Gestión de Academia");
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gestionEstudianteButton.addActionListener(e -> mostrarVistaEstudiante());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                if (estudianteViewSize != null) {
                    estudianteViewSize = getSize();
                }else {
                    estudianteViewSize = new Dimension(800, 600);
                }
                if (estudianteViewLocation != null) {
                    estudianteViewLocation = getLocation();
                }else{
                    estudianteViewLocation = new Point(0, 0);
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if (estudianteViewLocation != null) {
                    estudianteViewLocation = getLocation();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}