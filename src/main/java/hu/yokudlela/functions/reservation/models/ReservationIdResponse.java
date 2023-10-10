package hu.yokudlela.functions.reservation.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationIdResponse {

    @Schema(description = "Foglalás azonosítója")
    private String id;

}
