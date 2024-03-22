package hu.yokudlela.functions.table;

import hu.yokudlela.functions.table.models.TableEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "table", path = "table")
@Repository
public interface TableRepository extends CrudRepository<TableEntity, Long> {
    @Operation(summary = "Find Table by Name", description = "Get table from database by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Response",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal System Error",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No table with this name",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public TableEntity findByName(@Param(value = "name") String name);

    @Operation(summary = "Find Tables by Capacity ", description = "Get table from database by capacity equal or greater")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Response",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal System Error",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No table with this capacity",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public List<TableEntity> findByCapacityGreaterThanEqual(@Param(value = "capacity") Byte capacity);

    @Operation(summary = "Find Tables by Available ", description = "Get table from database by Available equal true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Response",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal System Error",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No table with this capacity",
                    content = @Content(schema = @Schema(implementation = TableEntity.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public List<TableEntity> findByAvailable(boolean pAvailable);

    public List<TableEntity> getAllByAvailable(boolean pAvailable);

}
