package montclio.heimdall.dto;

import montclio.heimdall.model.MotorcycleType;

public record PutMotorcycleDTO(Long id, String plate, MotorcycleType motorcycleType) {
}
