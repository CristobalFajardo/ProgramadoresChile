package org.veterinaria.programadoreschile.authserver.service.imp;

import org.veterinaria.programadoreschile.authserver.model.Usuario;
import org.veterinaria.programadoreschile.authserver.repo.IUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceimpl implements UserDetailsService {

        @Autowired
        private IUsuarioRepo repo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Usuario usuario = repo.findOneByUsername(username);

                if(usuario == null) {
                        throw new UsernameNotFoundException(String.format("Usuario no existe", username));
                }

                List<GrantedAuthority> roles = new ArrayList<>();

                usuario.getRoles().forEach(rol -> {
                        roles.add(new SimpleGrantedAuthority(rol.getNombre()));
                });

                UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, roles);

                return ud;
        }

}
