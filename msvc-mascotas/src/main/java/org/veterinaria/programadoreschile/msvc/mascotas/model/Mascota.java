package org.veterinaria.programadoreschile.msvc.mascotas.model;

import javax.persistence.*;

@Entity
public class Mascota {

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "raza", nullable = false, length = 70)
    private String raza;


    @ManyToOne
    @JoinColumn(name = "id_dueno", nullable = false, foreignKey = @ForeignKey(name = "fk_mascota_dueno"))
    private Dueno dueno;

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public Boolean getTratamientoActivo() {
        return tratamientoActivo;
    }

    public void setTratamientoActivo(Boolean tratamientoActivo) {
        this.tratamientoActivo = tratamientoActivo;
    }

    @Column(name = "tratamientoActivo", nullable = false)
    private Boolean tratamientoActivo;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

}
