package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotNull;
import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
}
