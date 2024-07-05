package model;

public class ProfesorModel extends EstudianteModel {
    private String departamento;

    public ProfesorModel() {}

    public ProfesorModel(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        super(id, nombre, identificacion, email, null, estado);
        this.departamento = departamento;
    }

    public ProfesorModel(String nombre, String identificacion, String email, String departamento, String estado) {
        super(nombre, identificacion, email, null, estado);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "ProfesorModel{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", identificacion='" + getIdentificacion() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", departamento='" + departamento + '\'' +
                ", estado='" + getEstado() + '\'' +
                '}';
    }
}