package com.novidades.gestaodeprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Pessoa;
import com.novidades.gestaodeprojetos.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> obterTodos(){
        var a = pessoaRepository.findAll();
        return a;
    }

    public Optional<Pessoa> obterPorId(Long id){
        return pessoaRepository.findById(id);
    }

    public Pessoa adicionar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa){
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    public void deletar(Long id){
        pessoaRepository.deleteById(id);
    }
}
