package hu.yokudlela.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal")
@jakarta.persistence.Table(name = "itafula")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Az asztal belső azonosítója")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Schema(description = "Asztal elnevezése", example = "Terasz 1")
    @NotBlank(message = "error.table.name.notset")
    @NotNull(message = "error.table.name.notset")
    @Size(max=20, message = "error.table.name.long")
    private String name;

    @Schema(description = "Aktuálisan használható", example = "true")
    @JsonIgnore
    private boolean available = true;

    @Schema(description = "Székek száma az asztalnál", example = "3")
    @Min(value = 2, message = "error.table.capacity.min")
    private byte capacity;


}


