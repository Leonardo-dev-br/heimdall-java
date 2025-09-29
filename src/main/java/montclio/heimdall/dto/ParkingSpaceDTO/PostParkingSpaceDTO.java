package montclio.heimdall.dto.ParkingSpaceDTO;

import montclio.heimdall.model.ParkingSpace;

public record PostParkingSpaceDTO(Long id, Long idZona, Boolean preenchida, String codVaga) {}