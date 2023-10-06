package hu.yokudlela.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class ReservationComponent {
    public ReservationComponent(){
        log.info("Create ReservationComponent instance");
    }
}
