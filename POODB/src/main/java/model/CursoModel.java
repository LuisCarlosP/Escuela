package model;

public class CursoModel {

    private int id;
    private String nombre;

    private int estado;



    public CursoModel(String nombre, int estado) {
        this.nombre = nombre;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}



/*
DELETE FROM `curso` WHERE 0
UPDATE `curso` SET `id`='[value-1]',`nombre`='[value-2]',`estado`='[value-3]' WHERE 1
INSERT INTO `curso`(`id`, `nombre`, `estado`) VALUES ('[value-1]','[value-2]','[value-3]')
SELECT `id`, `nombre`, `estado` FROM `curso` WHERE 1

*/
