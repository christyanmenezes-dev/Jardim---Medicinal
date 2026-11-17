package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.DTOs.LoginRequestDTO;
import org.example.backend.DTOs.LoginResponseDTO;
import org.example.backend.DTOs.UsuarioRequestDTO;
import org.example.backend.DTOs.UsuarioResponseDTO;
import org.example.backend.entity.UsuarioEntity;
import org.example.backend.exception.EmailCadastradoException;
import org.example.backend.exception.CredenciaisInvalidasException;
import org.example.backend.exception.UsuarioNotFoundException;
import org.example.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;
    private final JwtService jwtService;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {

        boolean verificar = repository.existsByEmail(dto.getEmail());

        if (verificar) {
            throw new EmailCadastradoException("Email já cadastrado");
        }

        UsuarioEntity cadastro = new UsuarioEntity();
        cadastro.setNome(dto.getNome());
        cadastro.setEmail(dto.getEmail());
        cadastro.setSenha(encoder.encode(dto.getSenha()));

        UsuarioEntity salvar = repository.save(cadastro);
        UsuarioResponseDTO retorno = new UsuarioResponseDTO(salvar);

        return retorno;
    }

    public UsuarioResponseDTO procurarId(Long id) {
        Optional<UsuarioEntity> procurar = repository.findById(id);
        if (procurar.isPresent()) {
            UsuarioEntity converter = procurar.get();
            UsuarioResponseDTO retorno = new UsuarioResponseDTO(converter);
            return retorno;
        } else {
            throw new UsuarioNotFoundException("Usuario não encontrado");
        }
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Optional<UsuarioEntity> procurar = repository.findByEmail(dto.getEmail());
        if (procurar.isPresent()) {
            UsuarioEntity converter = procurar.get();
            boolean senhaValida = encoder.matches(dto.getSenha(), converter.getSenha());

            if (!senhaValida) {
                throw new CredenciaisInvalidasException("Email ou senha inválidos");
            } else {
                String token = jwtService.gerarToken(converter);
                LoginResponseDTO retorno = new LoginResponseDTO(converter, token);
                return retorno;
            }
        } else {
            throw new CredenciaisInvalidasException("Email ou senha inválidos");
        }


    }
}
