package montclio.heimdall.dto.ParkingSpaceDTO;

import montclio.heimdall.model.ParkingSpace;
public record ParkingSpaceFilter(Long idZona, Boolean preenchida, String codVaga) {}