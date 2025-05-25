package montclio.heimdall.dto.MotorcycleDTO;

import montclio.heimdall.model.MotorcycleType;

public record MotorcycleFilter(
        String plate,
        String chassiNumber,
        MotorcycleType motorcycleType,
        Long tagId
) {
}
