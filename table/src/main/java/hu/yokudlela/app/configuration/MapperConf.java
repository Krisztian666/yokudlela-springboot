package hu.yokudlela.app.configuration;

import hu.yokudlela.app.converter.TableNameConverter;
import hu.yokudlela.functions.reservation.models.ReservationEntity;
import hu.yokudlela.functions.reservation.models.ReservationResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConf {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper =new ModelMapper();
        mapper.addConverter(new TableNameConverter());
        TypeMap<ReservationEntity, ReservationResponse> propertyMapper = mapper.createTypeMap(ReservationEntity.class, ReservationResponse.class);
        propertyMapper.addMapping(ReservationEntity::getTableEntity, ReservationResponse::setTableName);
        return mapper;
    }

}
