package hu.yokudlela.functions.reservation;

import hu.yokudlela.app.error.ApiError;
import hu.yokudlela.functions.reservation.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController("reservation")
@RequestMapping("/reservation")
@CrossOrigin//(origins = {"http://localhost:8080","http://localhost:4200"})
public class ReservationController {

    @Autowired
    private ReservationComponent servReservation;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReservationRepository repReservation;

    @GetMapping(path = "/byTimeIntervall")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400", description = "Rossz hívási paraméterek"),
                    @ApiResponse(responseCode = "500", description = "Működési hiba"),
            }
    )
    @Operation(summary = "Aktív rendelések lekérdezése", description = "a megadott két intervallum között lekérdezi az aktív asztalfoglalásokat")
    public List<ReservationResponse> get(@Valid TimeIntervallRequest pTime) {
        return this.repReservation.findByBeginBetweenOrEndBetween(pTime.getBegin(),pTime.getEnd(),pTime.getBegin(),pTime.getEnd())
                .stream()
                .map(src->modelMapper.map(src,ReservationResponse.class)).toList();
    }

    @PostMapping(path = "")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400",
                            description = "Rossz hívási paraméterek",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ApiError.class))}),
                    @ApiResponse(responseCode = "500", description = "Működési hiba", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))})
            }
    )
    @Operation(summary = "Új foglalás rögzítése", description = "A felételeknek megfeleleően lefoglal egy vagy több asztalt")
    public ReservationIdResponse add(@Valid @RequestBody ReservationRequest pRes) {
        return modelMapper.map(this.servReservation.bookingTables(pRes), ReservationIdResponse.class);
    }

    @DeleteMapping(path = "")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sikeres hívás"),
                    @ApiResponse(responseCode = "400",
                            description = "Rossz hívási paraméterek",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ApiError.class))}),
                    @ApiResponse(responseCode = "500", description = "Működési hiba", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))})
            }
    )
    @Operation(summary = "Meglévő foglalás törlése", description = "Felszabadít egy vagy több asztalt")
    public void delete(@Valid ReservationIdRequest pId){
        this.repReservation.deleteById(pId.getId());
    }

}
