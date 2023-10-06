package hu.yokudlela;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Name {
    @NotEmpty
    @Schema(description = "Firs name")
    private String fname;
    @NotEmpty
    @Schema(description = "Last name")
    private String lname;

}
