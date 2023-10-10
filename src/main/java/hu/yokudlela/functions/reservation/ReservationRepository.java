package hu.yokudlela.functions.reservation;


import hu.yokudlela.functions.reservation.models.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity,String> {

    public List<ReservationEntity> findByBeginBetweenOrEndBetween(LocalDateTime pBeginStart, LocalDateTime pBeginEnd, LocalDateTime pEndStart, LocalDateTime pEndEnd);
}
