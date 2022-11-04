package org.veterinaria.programadoreschile.msvc.mascotas.dto.general;

import io.swagger.v3.oas.annotations.media.Schema;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Dueno;

import javax.persistence.*;

@Schema(description = "Mascota DTO Data")
public class MascotaDTO {

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
private Integer idMascota;


    private Integer edad;

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    private String nombre;

    public Boolean getTratamientoActivo() {
        return tratamientoActivo;
    }

    public void setTratamientoActivo(Boolean tratamientoActivo) {
        this.tratamientoActivo = tratamientoActivo;
    }


    private Boolean tratamientoActivo;

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }


    private String raza;


    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }


    public Dueno dueno;



}
