package hu.yokudlela.reservation;

import hu.yokudlela.Name;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RestController("reservation")
public class ReservationRest {

    @GetMapping(path = "/")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400", description = "Rossz hívási paraméterek"),
                    @ApiResponse(responseCode = "500", description = "Működési hiba"),
            }
    )
    @Operation(summary = "Aktív rendelések lekérdezése", description = "a megadott két intervallum között lekérdezi az aktív asztalfoglalásokat")
    public List<Reservation> get(@Valid TimeIntervallRequest pTime){
        return Stream.of(new Reservation()).collect(Collectors.toList());
    }

    @PostMapping(path = "/")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400", description = "Rossz hívási paraméterek"),
                    @ApiResponse(responseCode = "500", description = "Működési hiba"),
            }
    )
    @Operation(summary = "Új foglalás rögzítése", description = "....")
    public void add(@Valid ReservationRequest pRes){
//        return Stream.of(new Reservation()).collect(Collectors.toList());
    }

}
