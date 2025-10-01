package montclio.heimdall.dto.HistoryDTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public record PutHistoryDTO(
        Long idMoto,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAtividade,
        String atividade
) {
}
