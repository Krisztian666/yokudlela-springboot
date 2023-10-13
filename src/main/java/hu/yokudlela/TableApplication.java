package hu.yokudlela;

import hu.yokudlela.app.converter.TableNameConverter;
import hu.yokudlela.functions.reservation.models.ReservationEntity;
import hu.yokudlela.functions.reservation.models.ReservationResponse;
import hu.yokudlela.functions.table.Table;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EntityScan(basePackages="hu.yokudlela")
@AutoConfiguration
//@EnableJpaRepositories(basePackages="hu.yokudlela")
public class TableApplication {
    public static void main(String[] args) {
        SpringApplication.run(TableApplication.class, args);
    }

}