package hu.yokudlela.reservation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EarlierDate(earlier = "begin", later = "end", message = "error.reservation.beginlaterend")
public class ReservationRequest {

    @Schema(description = "Foglaló neve")
    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    @Size(max = 20, min = 3, message = "error.reservation.name.long")
    private String name;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    private String contact;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime begin;

    @Schema(description = "Foglalás vége", example = "2021-10-10T10:00:10")
    @Future(message = "error.reservation.begin.past")
    @Column(name = "endtime")
    private LocalDateTime end;

    @Schema(description = "Személyek száma")
    @Min(value = 1, message = "error.reservation.person.few")
    private byte person;
}
