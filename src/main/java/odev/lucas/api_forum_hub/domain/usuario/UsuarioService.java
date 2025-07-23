package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotNull;
import odev.lucas.api_forum_hub.domain.perfil.Perfil;
import odev.lucas.api_forum_hub.domain.perfil.PerfilEntity;
import odev.lucas.api_forum_hub.domain.perfil.PerfilService;
import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PerfilService perfilService,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilService = perfilService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario cadastrar(UsuarioCadastroDTO cadastroDTO) {
        Boolean usuarioExiste = usuarioRepository.existsByEmail(cadastroDTO.email());

        if(usuarioExiste) {
            throw new DomainException("Já existe um usuário com este email", HttpStatus.CONFLICT);
        }

        PerfilEntity perfil = perfilService.findByNome(Perfil.ALUNO);
        String senha = passwordEncoder.encode(cadastroDTO.senha());

        Usuario usuario = new Usuario(cadastroDTO.nome(), cadastroDTO.email(), senha, List.of(perfil));
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new RuntimeException("Unimplemented");
    }

    public Usuario findById(@NotNull Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> {throw new DomainException("Usuario não encontrado ou não existe", HttpStatus.NOT_FOUND);
                }
        );
    }

    public Page<Usuario> listarTodosPageavel(Pageable pageable) {
        return usuarioRepository.findByAtivoTrue(pageable);
    }

    public Usuario atualizar(Long id, UsuarioAtualizacaoDTO atualizacaoDTO) {
        //verifica se o usuario existe
        boolean usuarioExiste = usuarioRepository.existsById(id);

        if(!usuarioExiste) {
            throw new DomainException("Usuario não encontrado ou não existe", HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizar(atualizacaoDTO);

        usuarioRepository.save(usuario);
        return usuario;
    }
}
