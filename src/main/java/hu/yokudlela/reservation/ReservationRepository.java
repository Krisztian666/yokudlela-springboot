package hu.yokudlela.reservation;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,String> {
    public List<Reservation> findByBeginIsGreaterThanEqualAndEndIsLessThanEqual(LocalDateTime pBegin, LocalDateTime pEnd);

    public List<Reservation> findByBeginBetweenOrEndBetween(LocalDateTime pBeginStart, LocalDateTime pBeginEnd, LocalDateTime pEndStart, LocalDateTime pEndEnd);
}
