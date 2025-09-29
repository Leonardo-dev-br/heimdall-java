package montclio.heimdall.dto.HistoryDTO;

import montclio.heimdall.model.MotorcycleType;

public record PutHistoryDTO(
        Long idMoto,
        java.time.LocalDate dataAtividade,
        String atividade
) {
}
