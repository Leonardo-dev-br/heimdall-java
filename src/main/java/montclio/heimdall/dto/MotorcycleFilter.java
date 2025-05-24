package montclio.heimdall.dto;

import montclio.heimdall.model.MotorcycleType;
import montclio.heimdall.model.TagRfId;

public record MotorcycleFilter(
        String plate,
        String chassiNumber,
        MotorcycleType motorcycleType,
        Long tagId
) {
}
