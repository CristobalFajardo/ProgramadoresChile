package org.veterinaria.programadoreschile.authserver.controller;

import org.veterinaria.programadoreschile.authserver.model.ResetToken;
import org.veterinaria.programadoreschile.authserver.model.Usuario;
import org.veterinaria.programadoreschile.authserver.service.ILoginService;
import org.veterinaria.programadoreschile.authserver.service.IResetTokenService;
import org.veterinaria.programadoreschile.authserver.util.EmailUtil;
import org.veterinaria.programadoreschile.authserver.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService service;
	
	@Autowired	
	private IResetTokenService tokenService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	//[mitotest21@gmail.com]
	@GetMapping(value = "/enviarCorreo")
	public ResponseEntity<Integer> enviarCorreo(@RequestParam("correo") String correo) throws Exception {
		int rpta = 0;
		
		Usuario us = service.verificarNombreUsuario(correo);
		if(us != null && us.getIdUsuario() > 0) {
			ResetToken token = new ResetToken();
			token.setToken(UUID.randomUUID().toString());
			token.setUser(us);
			token.setExpiracion(10);
			tokenService.guardar(token);
			
			Mail mail = new Mail();
			mail.setFrom("email.prueba.demo@gmail.com");
			mail.setTo(us.getUsername());
			mail.setSubject("RESTABLECER CONTRASEÑA  MEDIAPP");
			
			Map<String, Object> model = new HashMap<>();
			String url = "http://localhost:4200/#/recuperar/" + token.getToken();
			model.put("user", token.getUser().getUsername());
			model.put("resetUrl", url);
			mail.setModel(model);
			
			emailUtil.enviarMail(mail);
			
			rpta = 1;			
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/restablecer/verificar/{token}")
	public ResponseEntity<Integer> verificarToken(@PathVariable("token") String token) {
		int rpta = 0;
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getId() > 0) {
					if (!rt.estaExpirado()) {
						rpta = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> restablecerClave(@PathVariable("token") String token, @RequestBody String clave) {		
		try {
			ResetToken rt = tokenService.findByToken(token);			
			service.cambiarClave(clave, rt.getUser().getUsername());
			tokenService.eliminar(rt);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
