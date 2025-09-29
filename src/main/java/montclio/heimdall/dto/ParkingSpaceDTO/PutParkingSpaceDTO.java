package montclio.heimdall.dto.ParkingSpaceDTO;

import montclio.heimdall.model.ParkingSpace;

public record PutParkingSpaceDTO(Long idZona, Boolean preenchida, String codVaga) {}