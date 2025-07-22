package odev.lucas.api_forum_hub.domain.topico;

import jakarta.persistence.*;
import lombok.*;
import odev.lucas.api_forum_hub.domain.curso.Curso;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"titulo", "mensagem"})
@Entity
@Table(name = "topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne()
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Boolean ativo;

    public void atualizar(TopicoAtualizacaoDto atualizacaoDto, Curso curso) {
        this.titulo = atualizacaoDto.titulo() != null ? atualizacaoDto.titulo() : titulo;
        this.mensagem = atualizacaoDto.mensagem() != null ? atualizacaoDto.mensagem() : mensagem;
        this.curso = curso != null ? curso : this.curso;
    }

    public void desativar() {
        this.ativo = false;
    }
}
