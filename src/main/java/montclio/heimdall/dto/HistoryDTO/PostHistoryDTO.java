package montclio.heimdall.dto.HistoryDTO;

import jakarta.validation.constraints.NotBlank;
import montclio.heimdall.model.MotorcycleType;

public record PostHistoryDTO(
        Long id,
        Long idMoto,
        java.time.LocalDate dataAtividade,
        String atividade
) {

}
