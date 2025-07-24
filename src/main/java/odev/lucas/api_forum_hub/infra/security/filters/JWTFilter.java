package odev.lucas.api_forum_hub.infra.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioRepository;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioService;
import odev.lucas.api_forum_hub.infra.security.JWTProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //pega o token
        String token = getToken(request);

        if(token != null) {
            String subject = jwtProvider.getSubject(token);

            UserDetails usuario = usuarioRepository.findByEmail(subject);

            //autentica o usuario
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken == null) {
            return null;
        }

        return bearerToken.replace("Bearer ", "");
    }
}
