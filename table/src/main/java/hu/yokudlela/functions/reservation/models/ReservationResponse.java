package hu.yokudlela.functions.reservation.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hu.yokudlela.app.serializer.ContactSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Egy foglalás
 * @author (K)risztián
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode()
@Schema(description = "Egy foglalás")
public class ReservationResponse {

    @Schema(description = "Foglalás azonosítója")
    private String id;

    @Schema(description = "Foglaló neve")
    private String name;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @JsonSerialize(using = ContactSerializer.class)
    private String contact;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime begin;

    @Schema(description = "Foglalás vége", example = "2021-10-10T10:00:10")
    private LocalDateTime end;

    @Schema(description = "Lefoglalt asztal")
    private List<String> tableName;

    @Schema(description = "Személyek száma")
    private byte person;
}