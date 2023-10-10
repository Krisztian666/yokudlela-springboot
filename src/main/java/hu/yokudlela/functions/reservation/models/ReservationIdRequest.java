package hu.yokudlela.functions.reservation.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReservationIdRequest {

    @Schema(description = "Foglalás azonosítója")
    @NotEmpty(message = "error.reservation.id.notset")
    @ReservationIdExist(message = "error.reservation.id.notexist" )
    private String id;

}
