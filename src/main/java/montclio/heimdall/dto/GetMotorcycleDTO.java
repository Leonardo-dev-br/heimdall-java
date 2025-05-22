package montclio.heimdall.dto;

import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.MotorcycleType;

public record GetMotorcycleDTO(
        Long id, String plate, String chassiNumber, MotorcycleType motorcycleType, GetTagRfidDTO tag
) {
    public GetMotorcycleDTO (Motorcycle motorcycle) {
        this(
                motorcycle.getId(),
                motorcycle.getPlate(),
                motorcycle.getChassiNumber(),
                motorcycle.getMotorcycleType(),
                motorcycle.getTag() != null ? new GetTagRfidDTO(motorcycle.getTag()) : null

        );
    }

}
