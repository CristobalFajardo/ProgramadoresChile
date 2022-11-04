package org.veterinaria.programadoreschile.msvc.mascotas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.MascotaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoDetalleProductoDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoListadoBusquedaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Dueno;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;

import org.veterinaria.programadoreschile.msvc.mascotas.repo.IDuenoRepo;
import org.veterinaria.programadoreschile.msvc.mascotas.repo.IGenericRepo;
import org.veterinaria.programadoreschile.msvc.mascotas.repo.IMascotaRepo;

import org.veterinaria.programadoreschile.msvc.mascotas.service.IMascotaService;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class MascotaServiceImpl extends CRUDImpl<Mascota, Integer> implements IMascotaService {

    @Autowired
    private IMascotaRepo repo;
    @Autowired
    private IDuenoRepo duenoRepo;

    @Override
    protected IGenericRepo<Mascota, Integer> getRepo() {
        return repo;
    }

    @Override
   public Integer verificarNombreMascotaDisponible(String nombre){

        MascotaDTO responseMascota = new MascotaDTO();

        List<Object[]> data = repo.verificarNombreMascotaDisponible(nombre);

        if(!data.isEmpty()){
            Object mascota = data.get(0);

            if ((Integer)((Object[])mascota)[0] != null &&  (Integer)((Object[])mascota)[0] > 0) {

                return (Integer)((Object[])mascota)[0];
            }
            else {
                return 0;
            }
        }else {
            return 0;
        }
    }

    @Override
    public List<MascotaDTO> listarMascotasRaza(String raza){

        List<MascotaDTO> mascotaDTOList = new ArrayList<>();

        repo.listarMascotasRaza(raza).forEach(x -> {

            MascotaDTO mascotaDTO = new MascotaDTO();

            if (String.valueOf(x[0]) != null && String.valueOf(x[0]) != "") {
                mascotaDTO.setIdMascota(Integer.parseInt(String.valueOf(x[0])));
            }
            if (String.valueOf(x[1]) != null && String.valueOf(x[1]) != "") {
                mascotaDTO.dueno = new Dueno();

                var auxDueno = duenoRepo.findById(Integer.parseInt(String.valueOf(x[1])));

                mascotaDTO.setDueno(auxDueno.get());
            }
            if (String.valueOf(x[2]) != null && String.valueOf(x[2]) != "") {
                mascotaDTO.setNombre(String.valueOf(x[2]));
            }
            if (String.valueOf(x[3]) != null && String.valueOf(x[3]) != "") {
                mascotaDTO.setRaza(String.valueOf(x[3]));
            }
            if (String.valueOf(x[4]) != null && String.valueOf(x[4]) != "") {
                mascotaDTO.setEdad(Integer.parseInt(String.valueOf(x[4])));
            }
            if (String.valueOf(x[5]) != null && String.valueOf(x[5]) != "") {
                mascotaDTO.setTratamientoActivo(Boolean.parseBoolean(String.valueOf(x[5])));
            }
            mascotaDTOList.add(mascotaDTO);
        });

        return mascotaDTOList;
    }

    @Override
    public MascotaDTO obtenerMascotaNombre(String nombre){

        MascotaDTO responseMascota = new MascotaDTO();

        List<Object[]> data = repo.verificarNombreMascotaNombre(nombre);

        if(!data.isEmpty()){
            Object mascota = data.get(0);

            if ((Integer)((Object[])mascota)[0] != null &&  (Integer)((Object[])mascota)[0] > 0) {
                responseMascota.setIdMascota((Integer)((Object[])mascota)[0]);
            }
            if (String.valueOf((Integer) ((Object[])mascota)[1]) != null) {
                responseMascota.dueno = new Dueno();

                var auxDueno = duenoRepo.findById(((Integer) ((Object[])mascota)[1]));

                responseMascota.setDueno(auxDueno.get());
            }
            if (String.valueOf((String)((Object[])mascota)[2]) != null && String.valueOf((String)((Object[])mascota)[2]) != "") {
                responseMascota.setNombre((String)((Object[])mascota)[2]);
            }

            if (String.valueOf((String)((Object[])mascota)[3]) != null && String.valueOf((String)((Object[])mascota)[3]) != "") {
                responseMascota.setRaza((String)((Object[])mascota)[3]);
            }

            if (String.valueOf((Integer) ((Object[])mascota)[4]) != null) {
                responseMascota.setEdad((Integer) ((Object[])mascota)[4]);
            }

            if (String.valueOf((Boolean) ((Object[])mascota)[5]) != null ) {
                responseMascota.setTratamientoActivo((Boolean) ((Object[])mascota)[5]);
            }

            return responseMascota;
        }else {
            return null;
        }
    }

    @Override
    public List<MascotaDTO> listarMascotasDueno(Integer idDueno) {

        List<MascotaDTO> mascotaDTOList = new ArrayList<>();

        repo.obtenerMascotasDueno(idDueno).forEach(x -> {

            MascotaDTO mascotaDTO = new MascotaDTO();

            if (String.valueOf(x[0]) != null && String.valueOf(x[0]) != "") {
                mascotaDTO.setIdMascota(Integer.parseInt(String.valueOf(x[0])));
            }
            if (String.valueOf(x[1]) != null && String.valueOf(x[1]) != "") {
                mascotaDTO.dueno = new Dueno();

                var auxDueno = duenoRepo.findById(Integer.parseInt(String.valueOf(x[1])));

                mascotaDTO.setDueno(auxDueno.get());
            }
            if (String.valueOf(x[2]) != null && String.valueOf(x[2]) != "") {
                mascotaDTO.setNombre(String.valueOf(x[2]));
            }
            if (String.valueOf(x[3]) != null && String.valueOf(x[3]) != "") {
                mascotaDTO.setRaza(String.valueOf(x[3]));
            }
            if (String.valueOf(x[4]) != null && String.valueOf(x[4]) != "") {
                mascotaDTO.setEdad(Integer.parseInt(String.valueOf(x[4])));
            }
            if (String.valueOf(x[5]) != null && String.valueOf(x[5]) != "") {
                mascotaDTO.setTratamientoActivo(Boolean.parseBoolean(String.valueOf(x[5])));
            }
            mascotaDTOList.add(mascotaDTO);
        });
        return mascotaDTOList;
    }

}
