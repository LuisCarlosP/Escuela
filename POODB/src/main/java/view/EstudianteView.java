package view;

import controller.EstudianteController;
import model.EstudianteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstudianteView extends JPanel {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField identificacionField;
    private JTextField emailField;
    private JTextField fechaNacimientoField;
    private JComboBox<String> estadoComboBox;
    private EstudianteController estudianteController;

    private JFrame mainFrame;

    public EstudianteView(EstudianteController estudianteController, JFrame mainFrame) {
        this.estudianteController = estudianteController;
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
        JLabel identificacionLabel = new JLabel("Identificación:");
        identificacionLabel.setFont(font);
        identificacionLabel.setForeground(color);
        inputPanel.add(identificacionLabel, gbc);

        gbc.gridx = 1;
        identificacionField = new JTextField();
        identificacionField.setFont(font);
        inputPanel.add(identificacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        emailLabel.setForeground(color);
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setFont(font);
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setFont(font);
        fechaNacimientoLabel.setForeground(color);
        inputPanel.add(fechaNacimientoLabel, gbc);

        gbc.gridx = 1;
        fechaNacimientoField = new JTextField();
        fechaNacimientoField.setFont(font);
        inputPanel.add(fechaNacimientoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
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
        agregarButton.addActionListener(e -> agregarEstudiante());
        buttonPanel.add(agregarButton, gbc);

        gbc.gridx = 1;
        JButton modificarButton = new JButton("Modificar");
        modificarButton.setFont(font);
        modificarButton.setForeground(Color.WHITE);
        modificarButton.setBackground(color);
        modificarButton.addActionListener(e -> modificarEstudiante());
        buttonPanel.add(modificarButton, gbc);

        gbc.gridx = 2;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setFont(font);
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setBackground(color);
        eliminarButton.addActionListener(e -> eliminarEstudiante());
        buttonPanel.add(eliminarButton, gbc);

        gbc.gridx = 3;
        JButton consultarButton = new JButton("Consultar");
        consultarButton.setFont(font);
        consultarButton.setForeground(Color.WHITE);
        consultarButton.setBackground(color);
        consultarButton.addActionListener(e -> consultarEstudiante());
        buttonPanel.add(consultarButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton volverButton = new JButton("Volver");
        volverButton.setFont(font);
        volverButton.setForeground(Color.WHITE);
        volverButton.setBackground(color);
        volverButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            Window win = SwingUtilities.getWindowAncestor(EstudianteView.this);
            win.dispose();
        });
        volverPanel.add(volverButton);
        add(volverPanel, BorderLayout.SOUTH);
    }

    private void agregarEstudiante() {
    String nombre = nombreField.getText();
    String identificacion = identificacionField.getText();
    String email = emailField.getText();
    String fechaNacimientoStr = fechaNacimientoField.getText();
    String estado = (String) estadoComboBox.getSelectedItem();
    if (nombre.isEmpty() || identificacion.isEmpty() || email.isEmpty() || fechaNacimientoStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios a exepcion de id.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Date fechaNacimiento;
    try {
        fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoStr);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Por favor, use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    EstudianteModel estudiante = estudianteController.agregarEstudiante(nombre, identificacion, email, fechaNacimiento, estado);
    if (estudiante != null) {
        JOptionPane.showMessageDialog(this, "Estudiante agregado exitosamente. ID: " + estudiante.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Error: No se pudo agregar el estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void modificarEstudiante() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EstudianteModel estudianteExistente = estudianteController.consultarEstudiante(id);
        if (estudianteExistente == null) {
            JOptionPane.showMessageDialog(this, "Error: El estudiante con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = nombreField.getText();
        String identificacion = identificacionField.getText();
        String email = emailField.getText();
        String fechaNacimientoStr = fechaNacimientoField.getText();
        String estado = (String) estadoComboBox.getSelectedItem();
        if (nombre.isEmpty() || identificacion.isEmpty() || email.isEmpty() || fechaNacimientoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date fechaNacimiento;
        try {
            fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Por favor, use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = estudianteController.modificarEstudiante(id, nombre, identificacion, email, fechaNacimiento, estado);
        if (success) {
            JOptionPane.showMessageDialog(this, "Estudiante modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se pudo modificar el estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEstudiante() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = estudianteController.eliminarEstudiante(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: El estudiante con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarEstudiante() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EstudianteModel estudiante = estudianteController.consultarEstudiante(id);
        if (estudiante != null) {
            JFrame detailsFrame = new JFrame("Detalles del Estudiante");
            detailsFrame.setLayout(new BorderLayout());

            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

            detailsPanel.add(createLabel("ID: " + estudiante.getId()));
            detailsPanel.add(createLabel("Nombre: " + estudiante.getNombre()));
            detailsPanel.add(createLabel("Identificación: " + estudiante.getIdentificacion()));
            detailsPanel.add(createLabel("Email: " + estudiante.getEmail()));
            detailsPanel.add(createLabel("Fecha de Nacimiento: " + estudiante.getFechaNacimiento()));
            detailsPanel.add(createLabel("Estado: " + estudiante.getEstado()));

            detailsFrame.add(detailsPanel, BorderLayout.CENTER);

            JButton volverButton = new JButton("Volver");
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailsFrame.dispose();
                    Window win = SwingUtilities.getWindowAncestor(EstudianteView.this);
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
            JOptionPane.showMessageDialog(this, "Error: Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }
}