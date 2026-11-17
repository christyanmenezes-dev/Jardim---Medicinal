package org.example.backend.repository;

import org.example.backend.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository <ComentarioEntity, Long> {
}
