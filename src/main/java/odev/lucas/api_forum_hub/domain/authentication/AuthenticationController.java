package odev.lucas.api_forum_hub.domain.authentication;

import lombok.AllArgsConstructor;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.infra.security.JWTProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager em;
    private final JWTProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication userPassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());

        Authentication authentication = em.authenticate(userPassword);

        //gera token
        String token = jwtProvider.gerarToken((Usuario) authentication.getPrincipal());
        // retorna o token
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
