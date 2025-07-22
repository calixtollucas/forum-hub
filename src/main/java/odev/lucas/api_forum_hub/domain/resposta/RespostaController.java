package odev.lucas.api_forum_hub.domain.resposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @PostMapping
    public ResponseEntity<RespostaResponseDTO> cadastrar(@RequestBody RespostaCadastroDTO cadastroDTO) {
        return null;
    }

}
