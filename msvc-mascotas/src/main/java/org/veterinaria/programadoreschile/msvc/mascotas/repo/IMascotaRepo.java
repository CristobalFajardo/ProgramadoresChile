package org.veterinaria.programadoreschile.msvc.mascotas.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;


import java.util.List;

//@Repository
public interface IMascotaRepo extends IGenericRepo<Mascota, Integer>{

    @Query(value = "select id_mascota from mascota where nombre = :nombre", nativeQuery = true)
    List<Object[]> verificarNombreMascotaDisponible(@Param("nombre") String nombre);

    @Query(value = "select * from mascota where raza = :raza", nativeQuery = true)
    List<Object[]> listarMascotasRaza(@Param("raza") String raza);

    @Query(value = "select * from mascota where nombre = :nombre", nativeQuery = true)
    List<Object[]> verificarNombreMascotaNombre(@Param("nombre") String nombre);

    @Query(value = "select * from mascota where id_dueno = :id_dueno", nativeQuery = true)
    List<Object[]> obtenerMascotasDueno(@Param("id_dueno") Integer id_dueno);

}
