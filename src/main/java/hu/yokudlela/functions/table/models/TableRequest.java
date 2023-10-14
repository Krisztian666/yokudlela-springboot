package hu.yokudlela.functions.table.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal")
public class TableRequest {

    @Schema(description = "Asztal elnevezése", example = "Terasz 1")
    @NotEmpty(message = "error.table.name.notset")
    @Size(max=20, message = "error.table.name.long")
    private String name;

    @Schema(description = "Székek száma az asztalnál", example = "3")
    @NotNull(message = "error.table.capacity.notset")
    @Min(value = 2, message = "error.table.capacity.min")
    private byte capacity;


}


