package odev.lucas.api_forum_hub.domain.perfil;

import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public PerfilEntity findByNome(Perfil perfil) {
        return perfilRepository.findByNome(perfil).orElseThrow(
                () -> new DomainException("Este tipo de Perfil não existe", HttpStatus.NOT_FOUND)
        );
    }
}
