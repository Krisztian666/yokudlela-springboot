package hu.yokudlela.app.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConf {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Yokudlela Table Api")
                        .contact(new Contact()
                                .name("Krisztian Karoczkai")
                                .email(".......")
                                .url("........")
                        )
                        .description("Reservations and Tables administration")
                        .version("1.0.0")
                );
    }

}
