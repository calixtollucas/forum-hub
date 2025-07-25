package odev.lucas.api_forum_hub.infra.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("API Forum-Hub")
                        .description("Uma API Rest desenvolvida para o challenge Forum-Hub do programa Oracle ONE")
                        .contact(new Contact()
                                .email("contato.lucaslcalixto334@gmail.com")
                                .url("https://www.linkedin.com/in/lucaslcalixto/")));
    }


}
