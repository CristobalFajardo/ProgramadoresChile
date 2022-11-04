package org.veterinaria.programadoreschile.authserver.controller;

import org.veterinaria.programadoreschile.authserver.dto.MotorAuditoriaDTO;
import org.veterinaria.programadoreschile.authserver.exception.ModeloNotFoundException;
import org.veterinaria.programadoreschile.authserver.model.MotorAuditoria;
import org.veterinaria.programadoreschile.authserver.service.IMotorAuditoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/MotorAuditoria")
@PreAuthorize("hasAnyAuthority('ADMIN')")
//@CrossOrigin(origins = "http://localhost:4200") // permite la entrada de peticiones a mi aplicación
public class MotorAuditoriaController {
    @Autowired
    private IMotorAuditoriaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    //@RequestMapping(value = "/" , method = RequestMethod.GET)
    public ResponseEntity<List<MotorAuditoriaDTO>> listar() throws Exception {
        List<MotorAuditoriaDTO> lista = service.listar().stream().map(p -> mapper.map(p, MotorAuditoriaDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotorAuditoriaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
        MotorAuditoria obj = service.listarPorId(id);

        if(obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        MotorAuditoriaDTO dto = mapper.map(obj, MotorAuditoriaDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody MotorAuditoriaDTO dto) throws Exception {
        MotorAuditoria p = mapper.map(dto, MotorAuditoria.class);
        MotorAuditoria obj = service.registrar(p);

        //localhost:8080/usuario/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMotorAuditoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<MotorAuditoriaDTO> modificar(@Valid @RequestBody MotorAuditoriaDTO dto) throws Exception {
        MotorAuditoria obj = service.listarPorId(dto.getIdMotorAuditoria());

        if(obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdMotorAuditoria());
        }

        MotorAuditoria p = mapper.map(dto, MotorAuditoria.class);
        MotorAuditoria pac = service.modificar(p);
        MotorAuditoriaDTO dtoResponse = mapper.map(pac, MotorAuditoriaDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        MotorAuditoria obj = service.listarPorId(id);

        if(obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/hateoas/{id}")
    public EntityModel<MotorAuditoriaDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
        MotorAuditoria obj = service.listarPorId(id);

        if(obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        MotorAuditoriaDTO dto = mapper.map(obj, MotorAuditoriaDTO.class);

        EntityModel<MotorAuditoriaDTO> recurso = EntityModel.of(dto);
        //localhost:8080/controladorReferencia/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));

        recurso.add(link1.withRel("controladorReferencia-info"));

        return recurso;

    }

}

