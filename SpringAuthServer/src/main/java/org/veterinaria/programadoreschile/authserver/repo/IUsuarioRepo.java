package org.veterinaria.programadoreschile.authserver.repo;

import org.veterinaria.programadoreschile.authserver.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer>  {

	//from usuario where username = ?
	//@Query("FROM Usuario us where us.username = ?")
	//Derived Query
	Usuario findOneByUsername(String username);	
}
