package com.novidades.gestaodeprojetos.view.controller;

import com.novidades.gestaodeprojetos.model.MensagemEmail;
import com.novidades.gestaodeprojetos.model.Usuario;
import com.novidades.gestaodeprojetos.service.EmailService;
import com.novidades.gestaodeprojetos.service.UsuarioService;
import com.novidades.gestaodeprojetos.view.model.usuario.LoginRequest;
import com.novidades.gestaodeprojetos.view.model.usuario.LoginResponse;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService servicoUsuario; 

    @Autowired
    private EmailService servicoEmail;

    @GetMapping
    public List<Usuario> obterTodos(){
        return servicoUsuario.obterTodos();
    }
    
    @GetMapping("/{id}")
    public Optional<Usuario> obter(@PathVariable("id") long id){
        return servicoUsuario.obterPorId(id);
    }

    @PostMapping
    public Usuario adicionar (@RequestBody Usuario usuario){
        return servicoUsuario.adicionar(usuario);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return servicoUsuario.logar(request.getEmail(), request.getSenha());
    }

    @PostMapping("/email")
    public String enviarEmail(@RequestBody MensagemEmail email){

        try {
            servicoEmail.enviar(email);
            return "Deu certo";
        } catch (Exception e) {
            e.printStackTrace();
            return "Deu ruim";
        }
    }
}
