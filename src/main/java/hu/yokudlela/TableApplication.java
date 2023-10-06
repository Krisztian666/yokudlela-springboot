package hu.yokudlela;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages="hu.yokudlela")
@AutoConfiguration
//@EnableJpaRepositories(basePackages="hu.yokudlela")
public class TableApplication {
    public static void main(String[] args) {
        SpringApplication.run(TableApplication.class, args);
    }



    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


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