package model;

import java.util.Date;

public class EstudianteModel {
    private int id;
    private String nombre;
    private String identificacion;
    private String email;
    private Date fechaNacimiento;
    private String estado;

    public EstudianteModel() {}

    public EstudianteModel(int id, String nombre, String identificacion, String email, Date fechaNacimiento, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public EstudianteModel(String nombre, String identificacion, String email, Date fechaNacimiento, String estado) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstudianteModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estado='" + estado + '\'' +
                '}';
    }
}