package org.veterinaria.programadoreschile.authserver.service.imp;

import org.veterinaria.programadoreschile.authserver.model.ResetToken;
import org.veterinaria.programadoreschile.authserver.repo.IResetTokenRepo;
import org.veterinaria.programadoreschile.authserver.service.IResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenRepo repo;
	
	@Override
	public ResetToken findByToken(String token) {
		return repo.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) {
		repo.save(token);
	}

	@Override
	public void eliminar(ResetToken token) {
		repo.delete(token);
	}

}
