package view;

import controller.ProfesorController;
import model.ProfesorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfesorView extends JPanel {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField identificacionField;
    private JTextField emailField;
    private JTextField departamentoField;
    private JComboBox<String> estadoComboBox;
    private ProfesorController profesorController;

    private JFrame mainFrame;

    public ProfesorView(ProfesorController profesorController, JFrame mainFrame) {
        this.profesorController = profesorController;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

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

        inputPanel.add(new JLabel("Departamento:"));
        departamentoField = new JTextField();
        inputPanel.add(departamentoField);

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
                agregarProfesor();
            }
        });
        buttonPanel.add(agregarButton, gbc);

        gbc.gridx = 1;
        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProfesor();
            }
        });
        buttonPanel.add(modificarButton, gbc);

        gbc.gridx = 2;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProfesor();
            }
        });
        buttonPanel.add(eliminarButton, gbc);

        gbc.gridx = 3;
        JButton consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarProfesor();
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
                Window win = SwingUtilities.getWindowAncestor(ProfesorView.this);
                win.dispose();
            }
        });
        volverPanel.add(volverButton);
        add(volverPanel, BorderLayout.SOUTH);
    }

    private void agregarProfesor() {
        String nombre = nombreField.getText();
        String identificacion = identificacionField.getText();
        String email = emailField.getText();
        String departamento = departamentoField.getText();
        String estado = (String) estadoComboBox.getSelectedItem();
        if (nombre.isEmpty() || identificacion.isEmpty() || email.isEmpty() || departamento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios a excepción de id.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProfesorModel profesor = profesorController.agregarProfesor(nombre, identificacion, email, departamento, estado);
        if (profesor != null) {
            JOptionPane.showMessageDialog(this, "Profesor agregado exitosamente. ID: " + profesor.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se pudo agregar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarProfesor() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProfesorModel profesorExistente = profesorController.consultarProfesor(id);
        if (profesorExistente == null) {
            JOptionPane.showMessageDialog(this, "Error: El profesor con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = nombreField.getText();
        String identificacion = identificacionField.getText();
        String email = emailField.getText();
        String departamento = departamentoField.getText();
        String estado = (String) estadoComboBox.getSelectedItem();
        if (nombre.isEmpty() || identificacion.isEmpty() || email.isEmpty() || departamento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = profesorController.modificarProfesor(id, nombre, identificacion, email, departamento, estado);
        if (success) {
            JOptionPane.showMessageDialog(this, "Profesor modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se pudo modificar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProfesor() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = profesorController.eliminarProfesor(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: El profesor con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarProfesor() {
        String idStr = idField.getText();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese un número entero válido como ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProfesorModel profesor = profesorController.consultarProfesor(id);
        if (profesor != null) {
            JFrame detailsFrame = new JFrame("Detalles del Profesor");
            detailsFrame.setLayout(new BorderLayout());

            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

            detailsPanel.add(createLabel("ID: " + profesor.getId()));
            detailsPanel.add(createLabel("Nombre: " + profesor.getNombre()));
            detailsPanel.add(createLabel("Identificación: " + profesor.getIdentificacion()));
            detailsPanel.add(createLabel("Email: " + profesor.getEmail()));
            detailsPanel.add(createLabel("Departamento: " + profesor.getDepartamento()));
            detailsPanel.add(createLabel("Estado: " + profesor.getEstado()));

            detailsFrame.add(detailsPanel, BorderLayout.CENTER);

            JButton volverButton = new JButton("Volver");
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailsFrame.dispose();
                    Window win = SwingUtilities.getWindowAncestor(ProfesorView.this);
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
            JOptionPane.showMessageDialog(this, "Error: Profesor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }
}