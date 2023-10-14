package hu.yokudlela.functions.table.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal azonosító")
public class TableIdRequest {
    @Schema(description = "Az asztal belső azonosítója")
    @TableIdExist(message = "error.table.notexist")
    @TableAvailable(value="true", message = "error.table.available.false", groups = {TableAvailableIsTrueGroup.class})
    @TableAvailable(value="false", message = "error.table.available.true", groups = {TableAvailableIsFalseGroup.class})
    private long id;
}


