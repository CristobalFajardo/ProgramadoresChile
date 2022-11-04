package org.veterinaria.programadoreschile.authserver.service;

import org.veterinaria.programadoreschile.authserver.model.ResetToken;

public interface IResetTokenService {

	ResetToken findByToken(String token);
	
	void guardar(ResetToken token);
	
	void eliminar(ResetToken token);

}
