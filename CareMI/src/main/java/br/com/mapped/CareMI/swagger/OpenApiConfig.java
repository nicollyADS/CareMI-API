package br.com.mapped.CareMI.swagger;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "CareMI API",
                description = "Endpoints da API CareMI",
                version = "1.0",
                contact = @Contact(name = "Mapped Innovation", email = "innovationmapped@gmail.com")
        ),
        security = @SecurityRequirement(name = "bearerJWT"),
        externalDocs = @ExternalDocumentation(description = "Repositório GitHub", url = "https://github.com/nicollyADS/CareMI-API")
)
@SecurityScheme(
        name = "bearerJWT",
        description = "Autenticação com Token JWT",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
