package org.veterinaria.programadoreschile.msvc.mascotas.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.DuenoDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.MascotaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.exception.ModeloNotFoundException;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Dueno;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Mascota;
import org.veterinaria.programadoreschile.msvc.mascotas.service.IDuenoService;
import org.veterinaria.programadoreschile.msvc.mascotas.service.IMascotaService;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/mascota")
//@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

	@Autowired
	private IMascotaService service;
	@Autowired
	private IDuenoService duenoService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	//@RequestMapping(value = "/" , method = RequestMethod.GET)
	public ResponseEntity<List<MascotaDTO>> listar() throws Exception {
		List<MascotaDTO> lista = service.listar().stream().map(p -> mapper.map(p, MascotaDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MascotaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Mascota obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		MascotaDTO dto = mapper.map(obj, MascotaDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/listarPorRaza/{raza}")
	public ResponseEntity<List<MascotaDTO>> listarPorRaza(@PathVariable("raza") String raza) throws Exception {
		List<MascotaDTO> obj = service.listarMascotasRaza(raza);

		if(obj == null || obj.size() == 0) {
			throw new ModeloNotFoundException("RAZA NO ENCONTRADA " + raza);
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/obtenerMascotaNombre/{nombre}")
	public ResponseEntity<MascotaDTO> obtenerMascotaNombre(@PathVariable("nombre") String nombre) throws Exception {
		MascotaDTO obj = service.obtenerMascotaNombre(nombre);

		if(obj == null) {
			throw new ModeloNotFoundException("MASCOTA NO ENCONTRADA " + nombre);
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody MascotaDTO dto) throws Exception {
		Mascota p = mapper.map(dto, Mascota.class);

		Integer idMascota = service.verificarNombreMascotaDisponible(dto.getNombre());

		Dueno objDueno = duenoService.listarPorId(p.getDueno().getIdDueno());
		DuenoDTO duenoDTO = mapper.map(objDueno, DuenoDTO.class);

		if(duenoDTO == null){
			throw new ModeloNotFoundException("No existe dueño " + dto.getDueno().getIdDueno());
		}

		if(idMascota == 0){
			Mascota obj = service.registrar(p);

			//localhost:8080/Producto/5
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMascota()).toUri();
			return ResponseEntity.created(location).build();
		}else {
			throw new ModeloNotFoundException("Nombre de mascota ya utilizado " + dto.getNombre());
		}

	}
	
	@PutMapping
	public ResponseEntity<MascotaDTO> modificar(@Valid @RequestBody MascotaDTO dto) throws Exception {
		Mascota obj = service.listarPorId(dto.getIdMascota());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdMascota());
		}

		Mascota p = mapper.map(dto, Mascota.class);
		Mascota pac = service.modificar(p);
		MascotaDTO dtoResponse = mapper.map(pac, MascotaDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Mascota obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		if(obj.getTratamientoActivo() == false){
			throw new ModeloNotFoundException("No se puede eliminar una Mascota con tratamiento activo " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping("/hateoas/{id}")
	public EntityModel<MascotaDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		Mascota obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		MascotaDTO dto = mapper.map(obj, MascotaDTO.class);
		
		EntityModel<MascotaDTO> recurso = EntityModel.of(dto);
		//localhost:8080/productos/1
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));

		recurso.add(link1.withRel("dueno-info"));

		return recurso;

	}



}
