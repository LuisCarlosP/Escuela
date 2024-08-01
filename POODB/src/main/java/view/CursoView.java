package view;

import controller.CursoController;
import model.CursoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CursoView extends JPanel {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JComboBox<String> estadoComboBox;
    private CursoController cursoController;

    private JFrame mainFrame;

    public CursoView(CursoController cursoController, JFrame mainFrame) {
        this.cursoController = cursoController;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.BOLD, 14);
        Color color = new Color(51, 51, 51);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(font);
        idLabel.setForeground(color);
        inputPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        idField = new JTextField();
        idField.setFont(font);
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(font);
        nombreLabel.setForeground(color);
        inputPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = new JTextField();
        nombreField.setFont(font);
        inputPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setFont(font);
        descripcionLabel.setForeground(color);
        inputPanel.add(descripcionLabel, gbc);

        gbc.gridx = 1;
        descripcionField = new JTextField();
        descripcionField.setFont(font);
        inputPanel.add(descripcionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(font);
        estadoLabel.setForeground(color);
        inputPanel.add(estadoLabel, gbc);

        gbc.gridx = 1;
        estadoComboBox = new JComboBox<>(new String[]{"activo", "inactivo"});
        estadoComboBox.setFont(font);
        inputPanel.add(estadoComboBox, gbc);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setFont(font);
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setBackground(color);
        agregarButton.addActionListener(e -> agregarCurso());
        buttonPanel.add(agregarButton, gbc);

        gbc.gridx = 1;
        JButton modificarButton = new JButton("Modificar");
        modificarButton.setFont(font);
        modificarButton.setForeground(Color.WHITE);
        modificarButton.setBackground(color);
        modificarButton.addActionListener(e -> modificarCurso());
        buttonPanel.add(modificarButton, gbc);

        gbc.gridx = 2;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setFont(font);
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setBackground(color);
        eliminarButton.addActionListener(e -> eliminarCurso());
        buttonPanel.add(eliminarButton, gbc);

        gbc.gridx = 3;
        JButton consultarButton = new JButton("Consultar");
        consultarButton.setFont(font);
        consultarButton.setForeground(Color.WHITE);
        consultarButton.setBackground(color);
        consultarButton.addActionListener(e -> consultarCurso());
        buttonPanel.add(consultarButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton volverButton = new JButton("Volver");
        volverButton.setFont(font);
        volverButton.setForeground(Color.WHITE);
        volverButton.setBackground(color);
        volverButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            Window win = SwingUtilities.getWindowAncestor(CursoView.this);
            win.dispose();
        });
        volverPanel.add(volverButton);
        add(volverPanel, BorderLayout.SOUTH);
    }

    private void agregarCurso() {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        String estado = (String) estadoComboBox.getSelectedItem();
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios a excepción de id.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CursoModel curso = cursoController.agregarCurso(nombre, descripcion, estado);
        if (curso != null) {
            JOptionPane.showMessageDialog(this, "Curso agregado exitosamente. ID: " + curso.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se pudo agregar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarCurso() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CursoModel cursoExistente = cursoController.consultarCurso(id);
        if (cursoExistente == null) {
            JOptionPane.showMessageDialog(this, "Error: El curso con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        String estado = (String) estadoComboBox.getSelectedItem();
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = cursoController.modificarCurso(id, nombre, descripcion, estado);
        if (success) {
            JOptionPane.showMessageDialog(this, "Curso modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se pudo modificar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCurso() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = cursoController.eliminarCurso(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: El curso con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarCurso() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CursoModel curso = cursoController.consultarCurso(id);
        if (curso != null) {
            JFrame detailsFrame = new JFrame("Detalles del Curso");
            detailsFrame.setLayout(new BorderLayout());

            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

            detailsPanel.add(createLabel("ID: " + curso.getId()));
            detailsPanel.add(createLabel("Nombre: " + curso.getNombre()));
            detailsPanel.add(createLabel("Descripción: " + curso.getDescripcion()));
            detailsPanel.add(createLabel("Estado: " + curso.getEstado()));

            detailsFrame.add(detailsPanel, BorderLayout.CENTER);

            JButton volverButton = new JButton("Volver");
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailsFrame.dispose();
                    Window win = SwingUtilities.getWindowAncestor(CursoView.this);
                    win.setVisible(true);
                }
            });
            detailsFrame.add(volverButton, BorderLayout.SOUTH);

            Window win = SwingUtilities.getWindowAncestor(this);
            detailsFrame.setSize(win.getSize());
            detailsFrame.setLocation(win.getLocation());
            win.setVisible(false);
            detailsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Curso no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }
}