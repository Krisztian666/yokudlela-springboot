package hu.yokudlela.functions.reservation;

import hu.yokudlela.app.error.BusinessException;
import hu.yokudlela.functions.table.TableRepository;
import hu.yokudlela.functions.reservation.models.ReservationEntity;
import hu.yokudlela.functions.reservation.models.ReservationRequest;
import hu.yokudlela.functions.table.Table;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component  //@Service
@Slf4j
public class ReservationComponent {

    @Autowired
    private TableRepository repTable;

    @Autowired
    private ReservationRepository repReservation;

    @Autowired
    private ModelMapper modelMapper;

    public ReservationEntity bookingTables(ReservationRequest pData){
        List<Table> tables=repTable.findByAvailable(true);
        List<ReservationEntity> reservations=repReservation.findByBeginBetweenOrEndBetween(pData.getBegin(),pData.getEnd(),pData.getBegin(),pData.getEnd());
        reservations.forEach(
                reservation -> {
                    reservation.getTable().forEach(t->{tables.remove(t);});
                });
        if(tables.isEmpty()){
            throw new BusinessException("error.reservation.nofreetables");
        }
        List<Table> res;
        res = oneTableEqualOrMoreCapacity(tables, pData.getPerson());
        if(res.isEmpty()) {
            res = multipleTables(tables, pData.getPerson());
        }
        int capacitySum=res.stream().mapToInt(Table::getCapacity).sum();
        if(capacitySum>=pData.getPerson()){
            return saveReservation(pData,res);
        }
        else {
            throw new BusinessException("error.reservation.nofreetables");
        }
    }

    private List<Table> oneTableEqualOrMoreCapacity(List<Table> tables, byte pCapacity){
        List list = tables.stream()
                .filter(table->table.getCapacity()>= pCapacity)
                .toList()
                .stream().sorted(Comparator.comparingInt(Table::getCapacity))
                .toList();

        return (list.isEmpty())?list:list.subList(0,1);

    }
    private List<Table> multipleTables(List<Table> tables, byte pCapacity){
        List<Table> list = tables.stream()
                .sorted(Comparator.comparingInt(Table::getCapacity).reversed())
                .toList();
        AtomicInteger capacityOfTables = new AtomicInteger(0);
        List res = list.stream().takeWhile(table->((capacityOfTables.getAndAdd(table.getCapacity()))<=pCapacity)).toList();
        return res;
    }

    private ReservationEntity saveReservation(ReservationRequest pData, List<Table> tables){
        ReservationEntity entity = modelMapper.map(pData, ReservationEntity.class);
        entity.setTable(tables);
        return repReservation.save(entity);
    }


}
