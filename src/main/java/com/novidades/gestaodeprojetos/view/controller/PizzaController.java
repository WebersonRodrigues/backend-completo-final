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

import com.novidades.gestaodeprojetos.model.Pizza;
import com.novidades.gestaodeprojetos.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> obterTodos(){
        var a =  pizzaService.obterTodos();
     
        return a;
    }

    @GetMapping("/{id}")
    public Optional<Pizza> obterPorId(@PathVariable Long id){
        return pizzaService.obterPorId(id);
    }

    @PostMapping
    public Pizza adicionar(@RequestBody Pizza pizza){
        return pizzaService.adicionar(pizza);
    }

    @PutMapping("/{id}")
    public Pizza atualizar(@PathVariable Long id, @RequestBody Pizza pizza){
        return pizzaService.atualizar(id, pizza);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        pizzaService.deletar(id);
    }
}
