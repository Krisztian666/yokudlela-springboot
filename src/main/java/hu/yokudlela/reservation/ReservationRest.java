package hu.yokudlela.reservation;

import hu.yokudlela.Name;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RestController("reservation")
public class ReservationRest {

    @GetMapping(path = "/reservations")
    @Operation(summary = "Aktív rendelések lekérdezése", description = "a megadott két intervallum között lekérdezi az aktív asztalfoglalásokat")
    public List<Reservation> getReservations(TimeIntervallRequest pTime){
        return Stream.of(new Reservation()).collect(Collectors.toList());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "500", description = "Rossz hívási paraméter")
            }
    )
    @GetMapping(path = "/hello/{path}")
    public String hello(
            @NotBlank @Parameter(description = "query name ") @RequestParam(name="name") String pName,
            @Parameter(description = "Path name ") @PathVariable(name = "path")String pPath){
        return "HELLO ".concat(pName).concat(pPath).concat("!");
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400", description = "Rossz hívási paraméter")
            }
    )
    @Operation(summary = "Összetett helló", description = "Nagyon profi hello")
    @GetMapping(path = "/helloobject")
    public String hello(@Valid Name pName){
        return "HELLO ".concat(pName.getLname()).concat(pName.getFname()).concat("!");
    }

}
