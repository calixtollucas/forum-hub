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
}
