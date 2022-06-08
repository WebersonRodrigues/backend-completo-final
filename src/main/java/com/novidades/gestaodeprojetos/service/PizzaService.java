package com.novidades.gestaodeprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Pizza;
import com.novidades.gestaodeprojetos.model.Pessoa;
import com.novidades.gestaodeprojetos.repository.PizzaRepository;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> obterTodos(){
        var a = pizzaRepository.findAll();
        return a;
    }

    public Optional<Pizza> obterPorId(Long id){
        return pizzaRepository.findById(id);
    }

    public Pizza adicionar(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Pizza atualizar(Long id, Pizza pizza){
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }

    public void deletar(Long id){
        pizzaRepository.deleteById(id);
    }
}
