package org.veterinaria.programadoreschile.msvc.mascotas.dto.general;

import io.swagger.v3.oas.annotations.media.Schema;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;

import javax.persistence.*;
import java.util.List;

@Schema(description = "Dueno DTO Data")
public class DuenoDTO {



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

	private Integer idDueno;


	private String nombreCompleto;


	private String direccion;


	private int edad;


}
