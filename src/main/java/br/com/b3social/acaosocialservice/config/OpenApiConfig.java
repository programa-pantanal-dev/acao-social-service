package br.com.b3social.acaosocialservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
	info = @Info(
		title = "Swagger Ação Social Api", 
		version = "1.0", 
		description = "Api desenvolvida para gerenciamento de Ações Sociais",
        contact = @Contact(
            name = "B3Social",
            email = "fake.email@gmail.com",
            url = "https://www.b3.com.br/pt_br/b3/"
        )        
	)
)
@SecurityScheme(
    name = "Keycloak",
    openIdConnectUrl = "http://localhost:8080/realms/b3social/.well-known/openid-configuration",
    scheme = "bearer",
    type = SecuritySchemeType.OPENIDCONNECT,
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {}
