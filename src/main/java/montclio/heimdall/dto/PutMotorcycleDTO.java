package montclio.heimdall.dto;

import jakarta.validation.constraints.NotNull;
import montclio.heimdall.model.MotorcycleType;

public record PutMotorcycleDTO(
        @NotNull(message = "O ID da moto é obrigatório")
        Long id,
        String plate,
        MotorcycleType motorcycleType,
        Long tagId
) {
}
