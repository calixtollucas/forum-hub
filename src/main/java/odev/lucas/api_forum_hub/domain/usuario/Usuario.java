package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import odev.lucas.api_forum_hub.domain.perfil.PerfilEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis",
    joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_perfil"))
    private List<PerfilEntity> perfis;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream()
                .map(p -> new SimpleGrantedAuthority("ROLE_"+p.getNome().name()))
                .toList();
    }

    public Usuario(String nome, String email, String senha, List<PerfilEntity> perfis) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
        this.ativo = true;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    public void atualizar(UsuarioAtualizacaoDTO atualizacaoDTO) {
        this.nome = atualizacaoDTO.nome() != null ? atualizacaoDTO.nome() : this.nome;
    }

    public void desativar() {
        this.ativo = false;
    }
}
