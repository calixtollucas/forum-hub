package odev.lucas.api_forum_hub.domain.resposta;

import jakarta.persistence.*;
import lombok.*;
import odev.lucas.api_forum_hub.domain.topico.Topico;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "resposta")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String solucao;

    private Boolean ativo;

    public Resposta(String mensagem, Topico topico, Usuario autor, String solucao) {
        this.mensagem = mensagem;
        this.topico = topico;
        this.autor = autor;
        this.dataCriacao = LocalDate.now();
        this.solucao = solucao;
        this.ativo = true;
    }

    public void atualizar(RespostaAtualizacaoDTO dto) {
        this.mensagem = dto.mensagem() != null  && !dto.mensagem().isBlank() ? dto.mensagem() : mensagem;
        this.solucao = dto.solucao() != null && !dto.solucao().isBlank() ? dto.solucao() : solucao;
    }

    public void desativar() {
        this.ativo = false;
    }
}
