package com.novidades.gestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novidades.gestaodeprojetos.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    
}
