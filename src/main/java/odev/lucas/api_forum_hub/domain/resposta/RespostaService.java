package odev.lucas.api_forum_hub.domain.resposta;

import lombok.AllArgsConstructor;
import odev.lucas.api_forum_hub.domain.topico.Topico;
import odev.lucas.api_forum_hub.domain.topico.TopicoService;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioService;
import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final TopicoService topicoService;
    private final UsuarioService usuarioService;

    public Resposta cadastrar(RespostaCadastroDTO cadastroDTO) {
        //verifica se é igual pela mensagem
        boolean respostaExiste = respostaRepository.existsByMensagemAndAtivoTrue(cadastroDTO.mensagem());

        if(respostaExiste) {
            throw new DomainException("Esta Resposta já existe", HttpStatus.CONFLICT);
        }

        Topico topico = topicoService.findById(cadastroDTO.topico());
        Usuario autor = usuarioService.findById(cadastroDTO.autor());
        Resposta resposta = new Resposta(cadastroDTO.mensagem(), topico, autor, cadastroDTO.solucao());

        respostaRepository.save(resposta);
        return resposta;
    }

    public Page<Resposta> buscarTodas(Pageable pageable) {
        return respostaRepository.findByAtivoTrue(pageable);
    }

    public Page<Resposta> buscarPorTopicoPageable(Long id, Pageable pageable) {
        Topico topico = topicoService.findById(id);
        Page<Resposta> respostas = respostaRepository.findByTopicoAndAtivoTrue(topico, pageable);
        return respostas;
    }

    public Resposta buscarPorId(Long id) {
        return respostaRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DomainException("Resposta não encontrada ou não existe", HttpStatus.NOT_FOUND));
    }

    public Resposta atualizar(RespostaAtualizacaoDTO dto, Long id) {
        Resposta resposta = this.buscarPorId(id);
        resposta.atualizar(dto);

        respostaRepository.save(resposta);
        return resposta;
    }

    public void desativar(Long id) {
        Resposta resposta = buscarPorId(id);
        resposta.desativar();
        respostaRepository.save(resposta);
    }
}
