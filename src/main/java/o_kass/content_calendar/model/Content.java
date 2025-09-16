package o_kass.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Content(
        @Id
        Integer id,
        @NotBlank
        String title,
        @NotBlank
        String desc,
        Status status,
        Type contentType,
        @NotBlank
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
}
