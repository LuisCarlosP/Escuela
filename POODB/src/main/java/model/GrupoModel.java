package model;

public class GrupoModel extends CursoModel {
    public GrupoModel() {
        super();
    }

    public GrupoModel(int id, String nombre, String descripcion, String estado) {
        super(id, nombre, descripcion, estado);
    }

    public GrupoModel(String nombre, String descripcion, String estado) {
        super(nombre, descripcion, estado);
    }

    @Override
    public String toString() {
        return "GrupoModel{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", estado='" + getEstado() + '\'' +
                '}';
    }
}