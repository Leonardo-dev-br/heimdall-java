package montclio.heimdall.dto;

import montclio.heimdall.model.MotorcycleType;

public record PutMotorcycleDTO(
        String plate,
        MotorcycleType motorcycleType,
        Long tagId
) {
}
