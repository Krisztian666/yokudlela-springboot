package hu.yokudlela.functions.reservation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Egy foglalás
 * @author (K)risztián
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode()
@Schema(description = "Foglalás")
@Entity
@jakarta.persistence.Table(name = "ukubhuka")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservationEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Schema(description = "Foglaló neve")
    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    @Size(max = 20, min = 3, message = "error.reservation.name.long")
    private String name;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    @JsonSerialize(using = ContactSerializer.class)
    private String contact;

    @Schema(description = "Foglalás kezdete", example = "2021-10-10T10:00:10")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "begintime")
    private LocalDateTime begin;

    @Schema(description = "Foglalás vége", example = "2021-10-10T10:00:10")
    @Future(message = "error.reservation.begin.past")
    @Column(name = "endtime")
    private LocalDateTime end;

    @Schema(description = "Lefoglalt asztal")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private List<hu.yokudlela.functions.table.Table> table;

    @Schema(description = "Személyek száma")
    @Min(value = 1, message = "error.reservation.person.few")
    private byte person;
}