package org.veterinaria.programadoreschile.authserver.service;

import org.veterinaria.programadoreschile.authserver.model.Usuario;

public interface ILoginService {

	Usuario verificarNombreUsuario(String usuario);
	void cambiarClave(String clave, String nombre);
}
