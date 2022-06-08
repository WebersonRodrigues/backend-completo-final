package com.novidades.gestaodeprojetos.service;

import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.novidades.gestaodeprojetos.model.Usuario;
import com.novidades.gestaodeprojetos.repository.UsuarioRepository;
import com.novidades.gestaodeprojetos.security.JWTService;
import com.novidades.gestaodeprojetos.view.model.usuario.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    private static final String hederPrefix = "Bearer ";
    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> obterTodos(){
        return repositorioUsuario.findAll();
    }

    public Optional<Usuario> obterPorId(long id){
        return repositorioUsuario.findById(id);
    }

    public Optional<Usuario> obterPorEmail(String email){
        return repositorioUsuario.findByEmail(email);
    }

    public Usuario adicionar(Usuario usuario){
        usuario.setId(null);

        if(obterPorEmail(usuario.getEmail()).isPresent()){
            // Aqui poderia lançar uma exception informando que o usuario já existe.
            throw new InputMismatchException("Já existe um usuario cadastro com o email: " + usuario.getEmail());
        }

        // Aqui eu estou codificando a senha para não ficar publica, gerando um hash
        String senha = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senha);
        
        return repositorioUsuario.save(usuario);
    }

    public LoginResponse logar(String email, String senha){
        // Aqui que a autenticação acontece magicamente.
        Authentication autenticacao = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));

            // Aqui eu passo a nova autenticação para o Spring Security cuidar pra mim.
        SecurityContextHolder.getContext().setAuthentication(autenticacao);

        // Gero o token do usuario para devolver a ele.
        // Bearer acf12ghb3jhujh.asdfresdtuopi36jklo541.ascfhjvvcv
        String token = hederPrefix + jwtService.gerarToken(autenticacao);

        Usuario usuario = repositorioUsuario.findByEmail(email).get();

        return new LoginResponse(token, usuario);      
    }
}
