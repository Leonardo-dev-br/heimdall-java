package montclio.heimdall.dto.MotorcycleDTO;

import jakarta.validation.constraints.NotBlank;
import montclio.heimdall.model.MotorcycleType;

public record PostMotorcycleDTO(
        Long id,
        @NotBlank(message = "A placa é obrigatória")
        String plate,
        @NotBlank(message = "O número do chassi é obrigatório")
        String chassiNumber,
        MotorcycleType motorcycleType,
        Long tagId
) {

}
