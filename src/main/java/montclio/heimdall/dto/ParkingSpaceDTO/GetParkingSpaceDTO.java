package montclio.heimdall.dto.ParkingSpaceDTO;

import montclio.heimdall.model.ParkingSpace;

import java.time.LocalDate;

public record GetParkingSpaceDTO(Long idVaga, Long idZona, Boolean preenchida, String codVaga) {
    public GetParkingSpaceDTO(ParkingSpace parkingSpace) {
        this(parkingSpace.getIdVaga(), parkingSpace.getIdZona(), parkingSpace.isPreenchida(), parkingSpace.getCodVaga());
    }
}