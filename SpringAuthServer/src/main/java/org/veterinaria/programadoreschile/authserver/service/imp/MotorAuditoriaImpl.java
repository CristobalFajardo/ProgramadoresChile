package org.veterinaria.programadoreschile.authserver.service.imp;



import org.veterinaria.programadoreschile.authserver.model.MotorAuditoria;
import org.veterinaria.programadoreschile.authserver.repo.IGenericRepo;
import org.veterinaria.programadoreschile.authserver.repo.IMotorAuditoriaRepo;
import org.veterinaria.programadoreschile.authserver.service.IMotorAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorAuditoriaImpl extends CRUDImpl<MotorAuditoria, Integer> implements IMotorAuditoriaService {

    @Autowired
    private IMotorAuditoriaRepo repo;

    @Override
    protected IGenericRepo<MotorAuditoria, Integer> getRepo() {
        return repo;
    }



}
