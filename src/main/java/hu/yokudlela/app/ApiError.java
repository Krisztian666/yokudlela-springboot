package hu.yokudlela.app;

import java.util.LinkedHashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author (K)risztian
 */
@Data
@NoArgsConstructor
public class ApiError {
    @Schema(description = "url")
    private String path;
    @Schema(description = "error message", example = "error.business")
    private String message;
    @Schema(description = "error description")
    private Set<String> errors = new LinkedHashSet<>();


    @Builder
    public ApiError(String path, String message) {
        this.path = path;
        this.message = message;
    }


}
