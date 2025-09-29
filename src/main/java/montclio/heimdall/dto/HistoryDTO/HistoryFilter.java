package montclio.heimdall.dto.HistoryDTO;

import montclio.heimdall.model.HistoryMoto;

public record HistoryFilter(
        Long idMoto,
        java.time.LocalDate startDate,
        java.time.LocalDate endDate,
        String atividade
) {
}
