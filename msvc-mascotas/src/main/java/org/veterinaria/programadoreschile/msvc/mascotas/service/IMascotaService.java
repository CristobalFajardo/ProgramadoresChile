package org.veterinaria.programadoreschile.msvc.mascotas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.MascotaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoDetalleProductoDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoListadoBusquedaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;


import java.util.List;

public interface IMascotaService extends ICRUD<Mascota, Integer>{
    Integer verificarNombreMascotaDisponible(String nombre);
    public List<MascotaDTO> listarMascotasRaza(String raza);
    public MascotaDTO obtenerMascotaNombre(String nombre);

    public List<MascotaDTO> listarMascotasDueno(Integer idDueno);

}
