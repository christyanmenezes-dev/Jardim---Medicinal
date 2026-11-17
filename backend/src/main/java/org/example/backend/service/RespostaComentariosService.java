package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.DTOs.RespostaComentariosRequestDTO;
import org.example.backend.DTOs.RespostaComentariosResponseDTO;
import org.example.backend.entity.ComentarioEntity;
import org.example.backend.entity.RespostaComentariosEntity;
import org.example.backend.exception.ComentarioNotFoundException;
import org.example.backend.repository.ComentarioRepository;
import org.example.backend.repository.RespostaComentariosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RespostaComentariosService {

    private final RespostaComentariosRepository repository;
    private final ComentarioRepository comentario;

    public RespostaComentariosResponseDTO criarResposta(RespostaComentariosRequestDTO dto) {
        Optional <ComentarioEntity> procurarComentario = comentario.findById(dto.getComentarioId());
        if (procurarComentario.isPresent()) {
            ComentarioEntity comentarioEncontrado = procurarComentario.get();
            RespostaComentariosEntity novaResposta = new RespostaComentariosEntity();
            novaResposta.setNome(dto.getNome());
            novaResposta.setResposta(dto.getResposta());
            novaResposta.setComentario(comentarioEncontrado);

            RespostaComentariosEntity salvar = repository.save(novaResposta);
            RespostaComentariosResponseDTO retorno = new RespostaComentariosResponseDTO(salvar);
            return retorno;
        } else {
            throw new ComentarioNotFoundException("Comentário não encontrado");
        }
    }

    public List<RespostaComentariosResponseDTO> procurarTodos() {
        List<RespostaComentariosEntity> procurarRespostas = repository.findAll();
        List<RespostaComentariosResponseDTO> conversao = new ArrayList<>();

        for (RespostaComentariosEntity resposta : procurarRespostas) {
            conversao.add(new RespostaComentariosResponseDTO(resposta));
        }

        return conversao;
    }

    public RespostaComentariosResponseDTO alterarResposta(Long id, RespostaComentariosRequestDTO respostas) {
        Optional<RespostaComentariosEntity> procura = repository.findById(id);
        if (procura.isPresent()) {
            RespostaComentariosEntity resultado = procura.get();

            resultado.setNome(respostas.getNome());
            resultado.setResposta(respostas.getResposta());

            RespostaComentariosEntity salvar = repository.save(resultado);

            RespostaComentariosResponseDTO retorno = new RespostaComentariosResponseDTO(salvar);
            return retorno;
        } else {
            throw new ComentarioNotFoundException("Comentário não encontrado");
        }
    }

    public void deletarResposta(Long id) {
        Optional<RespostaComentariosEntity> procura = repository.findById(id);

        if(procura.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ComentarioNotFoundException("Comentário não encontrado");
        }
    }
}
