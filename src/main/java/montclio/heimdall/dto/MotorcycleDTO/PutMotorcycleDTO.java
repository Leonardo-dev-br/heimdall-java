package montclio.heimdall.dto.MotorcycleDTO;

import montclio.heimdall.model.MotorcycleType;

public record PutMotorcycleDTO(
        String plate,
        MotorcycleType motorcycleType,
        Long tagId
) {
}
