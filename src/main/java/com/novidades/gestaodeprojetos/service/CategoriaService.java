package com.novidades.gestaodeprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Categoria;
import com.novidades.gestaodeprojetos.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obterTodos(){
        var a = categoriaRepository.findAll();
        return a;
    }

    public Optional<Categoria> obterPorId(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria adicionar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoria){
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id){
        categoriaRepository.deleteById(id);
    }
}
