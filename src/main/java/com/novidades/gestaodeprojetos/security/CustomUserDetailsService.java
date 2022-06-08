package com.novidades.gestaodeprojetos.security;

import java.util.Optional;
import java.util.function.Supplier;

import com.novidades.gestaodeprojetos.model.Usuario;
import com.novidades.gestaodeprojetos.repository.UsuarioRepository;
// import com.novidades.gestaodeprojetos.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // @Autowired
    // private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        // Usuario usuario = getUser(() -> usuarioService.obterPorEmail(email));
        // return usuario;
        return usuarioRepository.findByEmail(email).get();
    }

    public UserDetails obterUsuarioPorId(long id) {
        return usuarioRepository.findById(id).get();
    }

    // private Usuario getUser(Supplier<Optional<Usuario>> supplier){
    //     return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    // }
    
}
