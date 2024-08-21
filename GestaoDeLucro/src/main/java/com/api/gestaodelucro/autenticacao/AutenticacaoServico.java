package com.api.gestaodelucro.autenticacao;

import com.api.gestaodelucro.autenticacao.dto.LoginRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.LoginResponseDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroResponseDTO;
import com.api.gestaodelucro.excecao.EntidadeNaoEncontradaExcecao;
import com.api.gestaodelucro.seguranca.TokenService;
import com.api.gestaodelucro.usuario.Usuario;
import com.api.gestaodelucro.usuario.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticacaoServico implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenServico;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorio.findByLogin(username);
    }

    public LoginResponseDTO login(LoginRequestDTO dto){

        Usuario usuario = Optional.of( (Usuario) usuarioRepositorio.findByLogin(dto.login()))
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Usuario nao encontrado"));

        if (passwordEncoder.matches(dto.senha(), usuario.getSenha())){
            var token = tokenServico.criarToken(usuario);
            var expiraEm = tokenServico.buscaTempoExpiracaoDoToken(token);

            return new LoginResponseDTO(usuario.getLogin(), token, expiraEm.toString());
        }

        return new LoginResponseDTO("Credenciais invalidas", null, null);
    }

    public RegistroResponseDTO registraUsuario(RegistroRequestDTO dto){

        Usuario usuarioExistente = (Usuario) usuarioRepositorio.findByLogin(dto.login());

        if (Objects.isNull(usuarioExistente)){
            Usuario usuario = new Usuario();
            usuario.setLogin(dto.login());
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
            usuario.setCargo(dto.cargo());

            usuarioRepositorio.save(usuario);

            var token = tokenServico.criarToken(usuario);
            var expiraEm = tokenServico.buscaTempoExpiracaoDoToken(token);

            return new RegistroResponseDTO(usuario.getLogin(), token, expiraEm.toString());

        }

        return new RegistroResponseDTO("Usuario ja existe", null, null);
    }
}
