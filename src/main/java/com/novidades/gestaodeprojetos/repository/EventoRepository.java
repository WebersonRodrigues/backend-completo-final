package com.novidades.gestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novidades.gestaodeprojetos.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
