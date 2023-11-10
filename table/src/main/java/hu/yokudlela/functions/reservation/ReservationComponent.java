package hu.yokudlela.functions.reservation;

import hu.yokudlela.app.error.BusinessException;
import hu.yokudlela.functions.table.TableRepository;
import hu.yokudlela.functions.reservation.models.ReservationEntity;
import hu.yokudlela.functions.reservation.models.ReservationRequest;
import hu.yokudlela.functions.table.models.TableEntity;
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
        List<TableEntity> tableEntities =repTable.findByAvailable(true);
        List<ReservationEntity> reservations=repReservation.findByBeginBetweenOrEndBetween(pData.getBegin(),pData.getEnd(),pData.getBegin(),pData.getEnd());
        reservations.forEach(
                reservation -> {
                    reservation.getTableEntity().forEach(t->{
                        tableEntities.remove(t);});
                });
        if(tableEntities.isEmpty()){
            throw new BusinessException("error.reservation.nofreetables");
        }
        List<TableEntity> res;
        res = oneTableEqualOrMoreCapacity(tableEntities, pData.getPerson());
        if(res.isEmpty()) {
            res = multipleTables(tableEntities, pData.getPerson());
        }
        int capacitySum=res.stream().mapToInt(TableEntity::getCapacity).sum();
        if(capacitySum>=pData.getPerson()){
            return saveReservation(pData,res);
        }
        else {
            throw new BusinessException("error.reservation.nofreetables");
        }
    }

    private List<TableEntity> oneTableEqualOrMoreCapacity(List<TableEntity> tableEntities, byte pCapacity){
        List list = tableEntities.stream()
                .filter(table->table.getCapacity()>= pCapacity)
                .toList()
                .stream().sorted(Comparator.comparingInt(TableEntity::getCapacity))
                .toList();

        return (list.isEmpty())?list:list.subList(0,1);

    }
    private List<TableEntity> multipleTables(List<TableEntity> tableEntities, byte pCapacity){
        List<TableEntity> list = tableEntities.stream()
                .sorted(Comparator.comparingInt(TableEntity::getCapacity).reversed())
                .toList();
        AtomicInteger capacityOfTables = new AtomicInteger(0);
        return  list.stream().takeWhile(table->((capacityOfTables.getAndAdd(table.getCapacity()))<=pCapacity)).toList();

    }

    private ReservationEntity saveReservation(ReservationRequest pData, List<TableEntity> tableEntities){
        ReservationEntity entity = modelMapper.map(pData, ReservationEntity.class);
        entity.setTableEntity(tableEntities);
        return repReservation.save(entity);
    }


}
