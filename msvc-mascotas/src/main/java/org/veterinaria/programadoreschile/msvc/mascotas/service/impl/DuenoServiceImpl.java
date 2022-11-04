package org.veterinaria.programadoreschile.msvc.mascotas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Dueno;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;
import org.veterinaria.programadoreschile.msvc.mascotas.repo.IDuenoRepo;
import org.veterinaria.programadoreschile.msvc.mascotas.repo.IGenericRepo;
import org.veterinaria.programadoreschile.msvc.mascotas.repo.IMascotaRepo;
import org.veterinaria.programadoreschile.msvc.mascotas.service.IDuenoService;


@Service
public class DuenoServiceImpl extends CRUDImpl<Dueno, Integer> implements IDuenoService {

    @Autowired
    private IDuenoRepo repo;

    @Override
    protected IGenericRepo<Dueno, Integer> getRepo() {
        return repo;
    }


}
