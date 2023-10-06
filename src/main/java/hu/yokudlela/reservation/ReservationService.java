package hu.yokudlela.reservation;

import hu.yokudlela.table.Table;
import hu.yokudlela.table.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private TableRepository  repTable;

    @Autowired
    private ReservationRepository repReservation;

    @Autowired
    private ModelMapper modelMapper;

    public void bookingTables(ReservationRequest pData){
        List<Table> tables=repTable.findByAvailableTrue();
        List<Reservation> reservations=repReservation.findByBeginBetweenOrEndBetween(pData.getBegin(),pData.getEnd(),pData.getBegin(),pData.getEnd());
        reservations.forEach(reservation -> {tables.remove(reservation.getTable());});
        List<Table> res;
        res = oneTableEqualOrMoreCapacity(tables, pData.getPerson());
        res = multipleTables(tables,pData.getPerson());
        saveReservation(pData,res);
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

    private void saveReservation(ReservationRequest pData, List<Table> tables){
        Reservation entity = modelMapper.map(pData, Reservation.class);
        entity.setTable(tables);
        repReservation.save(entity);
    }
}
