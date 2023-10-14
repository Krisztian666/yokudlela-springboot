package hu.yokudlela.functions.table.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal azonosító")
public class TableIdResponse {
    @Schema(description = "Az asztal belső azonosítója")
    private long id;
}


