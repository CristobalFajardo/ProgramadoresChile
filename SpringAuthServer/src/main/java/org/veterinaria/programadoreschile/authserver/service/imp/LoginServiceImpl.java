package org.veterinaria.programadoreschile.authserver.service.imp;

import org.veterinaria.programadoreschile.authserver.model.Usuario;
import org.veterinaria.programadoreschile.authserver.repo.ILoginRepo;
import org.veterinaria.programadoreschile.authserver.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;	
	
	@Autowired
	private ILoginRepo repo;

	@Override
	public Usuario verificarNombreUsuario(String usuario) {
		return repo.verificarNombreUsuario(usuario);
	}

	@Override
	public void cambiarClave(String clave, String nombre) {
		repo.cambiarClave(bcrypt.encode(clave), nombre);
	}
	
	

}
