package org.veterinaria.programadoreschile.authserver.repo;

import org.veterinaria.programadoreschile.authserver.model.ResetToken;

public interface IResetTokenRepo extends IGenericRepo<ResetToken, Integer>{
	
	//from ResetToken rt where rt.token = :?
	ResetToken findByToken(String token);

}
