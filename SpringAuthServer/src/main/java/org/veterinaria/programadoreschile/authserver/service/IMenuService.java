package org.veterinaria.programadoreschile.authserver.service;

import org.veterinaria.programadoreschile.authserver.model.Menu;

import java.util.List;

public interface IMenuService extends ICRUD<Menu, Integer>{
	
	List<Menu> listarMenuPorUsuario(String nombre);

}
