package org.veterinaria.programadoreschile.authserver.service.imp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    //Aca podemos manejar los datos que vienen cuando un usuario solicita un controlador clase Spring Security + Spring OAUTH2 + JWT
    //ejemplo de uso en controladores
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    //@PreAuthorize("@authServiceImpl.tieneAcceso('rol')")
    public boolean tieneAcceso(String path) {

        boolean rpta = false;

        String metodoRol = "";

        switch (path) {
            case "rol":
                metodoRol = "ADMIN";
                break;

            case "listarId":
                metodoRol = "ADMIN,USER,DBA";
                break;
        }

        String metodoRoles[] = metodoRol.split(",");

        Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(usuarioLogueado.getName());

        for (GrantedAuthority auth : usuarioLogueado.getAuthorities()) {
            String rolUser = auth.getAuthority();
            System.out.println(rolUser);

            for (String rolMet : metodoRoles) {
                if (rolUser.equalsIgnoreCase(rolMet)) {
                    rpta = true;
                }
            }
        }

        return rpta;
    }
}
