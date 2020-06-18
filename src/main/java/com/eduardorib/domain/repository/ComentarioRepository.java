package com.eduardorib.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardorib.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
