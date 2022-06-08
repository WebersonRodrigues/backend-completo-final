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

import com.novidades.gestaodeprojetos.model.Ingrediente;
import com.novidades.gestaodeprojetos.service.IngredienteService;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {
    
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public List<Ingrediente> obterTodos(){
        return ingredienteService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Ingrediente> obterPorId(@PathVariable Long id){
        return ingredienteService.obterPorId(id);
    }

    @PostMapping
    public Ingrediente adicionar(@RequestBody Ingrediente ingrediente){
        return ingredienteService.adicionar(ingrediente);
    }

    @PutMapping("/{id}")
    public Ingrediente atualizar(@PathVariable Long id, @RequestBody Ingrediente ingrediente){
        return ingredienteService.atualizar(id, ingrediente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        ingredienteService.deletar(id);
    }
}
