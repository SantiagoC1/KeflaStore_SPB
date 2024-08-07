package com.keflastore.kfstr.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "KEFLA STORE API",
                version = "1.0",
                description = "CRUD of ecommerce, with clients,products, carts and invoices"

        )
)
public class OpenApi {

}
