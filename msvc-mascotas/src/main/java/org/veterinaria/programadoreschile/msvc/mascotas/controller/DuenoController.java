package org.veterinaria.programadoreschile.msvc.mascotas.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.DuenoDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.general.MascotaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.input.CodigoProducto;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.input.PatenteVinDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoDetalleProductoDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.dto.output.ResponseIntegradorProductoListadoBusquedaDTO;
import org.veterinaria.programadoreschile.msvc.mascotas.exception.ModeloNotFoundException;
import org.veterinaria.programadoreschile.msvc.mascotas.model.Dueno;
import org.veterinaria.programadoreschile.msvc.mascotas.service.IDuenoService;
import org.veterinaria.programadoreschile.msvc.mascotas.service.IMascotaService;


import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/dueno")
//@CrossOrigin(origins = "http://localhost:4200")
public class DuenoController {

	@Autowired
	private IDuenoService service;
	@Autowired
	private IMascotaService mascotaService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	//@RequestMapping(value = "/" , method = RequestMethod.GET)
	public ResponseEntity<List<DuenoDTO>> listar() throws Exception {
		List<DuenoDTO> lista = service.listar().stream().map(p -> mapper.map(p, DuenoDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DuenoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Dueno obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		DuenoDTO dto = mapper.map(obj, DuenoDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody DuenoDTO dto) throws Exception {

		Dueno p = mapper.map(dto, Dueno.class);


		List<DuenoDTO> lista = service.listar().stream().map(t -> mapper.map(t, DuenoDTO.class)).collect(Collectors.toList());

		lista.forEach(x -> {

		  if(x.getNombreCompleto().equals(dto.getNombreCompleto())){
			  throw new ModeloNotFoundException("El nombre de la persona ya existe " + dto.getNombreCompleto());
		  }

		});
		Dueno obj = service.registrar(p);
		//localhost:8080/Producto/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDueno()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<DuenoDTO> modificar(@Valid @RequestBody DuenoDTO dto) throws Exception {
		Dueno obj = service.listarPorId(dto.getIdDueno());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdDueno());
		}

		Dueno p = mapper.map(dto, Dueno.class);
		Dueno pac = service.modificar(p);
		DuenoDTO dtoResponse = mapper.map(pac, DuenoDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Dueno obj = service.listarPorId(id);

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		List<MascotaDTO> mascotaDTOList = mascotaService.listarMascotasDueno(id);
		mascotaDTOList.forEach(x -> {

			if(x.getTratamientoActivo()){
				throw new ModeloNotFoundException("Existe un tratamiento activo para la mascota de este paciente, mascota " + x.getNombre());
			}
		});
		

		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping("/hateoas/{id}")
	public EntityModel<DuenoDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		Dueno obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		DuenoDTO dto = mapper.map(obj, DuenoDTO.class);
		
		EntityModel<DuenoDTO> recurso = EntityModel.of(dto);
		//localhost:8080/productos/1
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));

		recurso.add(link1.withRel("dueno-info"));


		return recurso;

	}

	@GetMapping("/listarMascotasDueno/{id}")
	//@RequestMapping(value = "/" , method = RequestMethod.GET)
	public ResponseEntity<List<MascotaDTO>> listarMascotasDueno(@PathVariable("id") Integer id) throws Exception {

		List<MascotaDTO> mascotaDTOList = mascotaService.listarMascotasDueno(id);



		return new ResponseEntity<>(mascotaDTOList, HttpStatus.OK);
	}


}
