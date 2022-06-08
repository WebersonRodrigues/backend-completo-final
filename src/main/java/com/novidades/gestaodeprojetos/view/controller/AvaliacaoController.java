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

import com.novidades.gestaodeprojetos.model.Avaliacao;
import com.novidades.gestaodeprojetos.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
    
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> obterTodos(){
        return avaliacaoService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Avaliacao> obterPorId(@PathVariable Long id){
        return avaliacaoService.obterPorId(id);
    }

    @PostMapping
    public Avaliacao adicionar(@RequestBody Avaliacao avaliacao){
        return avaliacaoService.adicionar(avaliacao);
    }

    @PutMapping("/{id}")
    public Avaliacao atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao){
        return avaliacaoService.atualizar(id, avaliacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        avaliacaoService.deletar(id);
    }
}
