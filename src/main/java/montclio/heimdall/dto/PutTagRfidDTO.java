package montclio.heimdall.dto;

import jakarta.validation.constraints.NotNull;
import montclio.heimdall.model.Motorcycle;

public record PutTagRfidDTO(
        @NotNull(message = "O ID da tag é obrigatório")
        Long id,
        String frequencia,
        String banda,
        String aplicacao,
        Motorcycle motorcycle
) {
}
