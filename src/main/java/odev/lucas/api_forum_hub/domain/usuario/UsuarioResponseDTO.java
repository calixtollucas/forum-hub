package odev.lucas.api_forum_hub.domain.usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome());
    }
}
