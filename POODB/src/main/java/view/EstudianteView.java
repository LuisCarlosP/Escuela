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

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Identificación:"));
        identificacionField = new JTextField();
        inputPanel.add(identificacionField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Fecha de Nacimiento (yyyy-MM-dd):"));
        fechaNacimientoField = new JTextField();
        inputPanel.add(fechaNacimientoField);

        inputPanel.add(new JLabel("Estado:"));
        estadoComboBox = new JComboBox<>(new String[]{"activo", "inactivo"});
        inputPanel.add(estadoComboBox);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });
        buttonPanel.add(agregarButton, gbc);

        gbc.gridx = 1;
        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstudiante();
            }
        });
        buttonPanel.add(modificarButton, gbc);

        gbc.gridx = 2;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });
        buttonPanel.add(eliminarButton, gbc);

        gbc.gridx = 3;
        JButton consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarEstudiante();
            }
        });
        buttonPanel.add(consultarButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                Window win = SwingUtilities.getWindowAncestor(EstudianteView.this);
                win.dispose();
            }
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