package hu.yokudlela.functions.hello;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Name {
    @Schema(description = "First Name")
    private String fname;
    @Schema(description = "Last Name")
    private String lname;
}
