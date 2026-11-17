package org.example.backend.repository;

import org.example.backend.entity.RespostaComentariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaComentariosRepository extends JpaRepository<RespostaComentariosEntity, Long> {
}
