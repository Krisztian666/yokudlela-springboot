package hu.yokudlela.functions.table.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal")
@jakarta.persistence.Table(name = "itafula")
@EqualsAndHashCode(exclude = {"name","available","capacity"})
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Az asztal belső azonosítója")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Schema(description = "Asztal elnevezése", example = "Terasz 1")
    @NotEmpty(message = "error.table.name.notset")
    @Size(max=20, message = "error.table.name.long")
    private String name;

    @JsonIgnore
    private boolean available = true;

    @Schema(description = "Székek száma az asztalnál", example = "3")
    @Min(value = 2, message = "error.table.capacity.min")
    @NotNull(message = "error.table.capacity.notset")
    private byte capacity;


}


