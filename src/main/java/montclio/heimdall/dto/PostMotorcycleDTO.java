package montclio.heimdall.dto;

import montclio.heimdall.model.MotorcycleType;

public record PostMotorcycleDTO(Long id, String plate, String chassiNumber, MotorcycleType motorcycleType) {

}
