package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.DTOs.ComentarioRequestDTO;
import org.example.backend.DTOs.ComentarioResponseDTO;
import org.example.backend.entity.ComentarioEntity;
import org.example.backend.exception.ComentarioNotFoundException;
import org.example.backend.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComentarioService {
    private final ComentarioRepository repository;

    public ComentarioResponseDTO criarComentario(ComentarioRequestDTO dto) {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setNome(dto.getNome());
        comentario.setComentario(dto.getComentario());

        ComentarioEntity comentarioSalvo = repository.save(comentario);
        ComentarioResponseDTO enviar = new ComentarioResponseDTO(comentarioSalvo);
        return enviar;
    }

    public List<ComentarioResponseDTO> procurarTodos() {
        List<ComentarioEntity> consultarComentarios = repository.findAll();

        List<ComentarioResponseDTO> comentariosDTO = new ArrayList<>();
         for (ComentarioEntity comentario : consultarComentarios) {
             comentariosDTO.add(new ComentarioResponseDTO(comentario));
        }

         return comentariosDTO;
    }

    public ComentarioResponseDTO alterarComentario(Long id, ComentarioRequestDTO comentario) {
        Optional<ComentarioEntity> consultar = repository.findById(id);
        if (consultar.isPresent()) {
            ComentarioEntity alterar = consultar.get();
            alterar.setNome(comentario.getNome());
            alterar.setComentario(comentario.getComentario());

            ComentarioEntity salvar = repository.save(alterar);
            ComentarioResponseDTO retorno = new ComentarioResponseDTO(salvar);
            return retorno;
        } else {
            throw new ComentarioNotFoundException("Comentário não encontrado");
        }
    }

    public void deletarComentario(Long id) {
        Optional<ComentarioEntity> consultar = repository.findById(id);
        if (consultar.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ComentarioNotFoundException("Comentário não encontrado");
        }
    }
}
