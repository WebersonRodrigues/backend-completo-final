package com.novidades.gestaodeprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Ingrediente;
import com.novidades.gestaodeprojetos.model.Pessoa;
import com.novidades.gestaodeprojetos.repository.IngredienteRepository;

@Service
public class IngredienteService {
    
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> obterTodos(){
        var a = ingredienteRepository.findAll();
        return a;
    }

    public Optional<Ingrediente> obterPorId(Long id){
        return ingredienteRepository.findById(id);
    }

    public Ingrediente adicionar(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente atualizar(Long id, Ingrediente ingrediente){
        ingrediente.setId(id);
        return ingredienteRepository.save(ingrediente);
    }

    public void deletar(Long id){
        ingredienteRepository.deleteById(id);
    }
}
