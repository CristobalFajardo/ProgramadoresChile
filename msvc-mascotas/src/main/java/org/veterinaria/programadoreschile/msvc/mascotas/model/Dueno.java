package org.veterinaria.programadoreschile.msvc.mascotas.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dueno {

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



    public Integer getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Integer idDueno) {
        this.idDueno = idDueno;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDueno;

    @Column(name = "nombreCompleto", nullable = false, length = 200)
    private String nombreCompleto;

    @Column(name = "direccion", nullable = false, length = 70)
    private String direccion;

    @Column(name = "edad", nullable = false, length = 8)
    private int edad;

}
