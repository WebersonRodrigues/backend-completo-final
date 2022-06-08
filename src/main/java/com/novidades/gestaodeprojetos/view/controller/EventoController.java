package com.novidades.gestaodeprojetos.view.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novidades.gestaodeprojetos.model.Evento;
import com.novidades.gestaodeprojetos.service.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> obterTodos(){
        var a = eventoService.obterTodos();

        return a;
    }

    @GetMapping("/{id}")
    public Optional<Evento> obterPorId(@PathVariable Long id){
        return eventoService.obterPorId(id);
    }

    @PostMapping
    public Evento adicionar(@RequestBody Evento evento){
        return eventoService.adicionar(evento);
    }

    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable Long id, @RequestBody Evento evento){
        return eventoService.atualizar(id, evento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        eventoService.deletar(id);
    }
}
