package montclio.heimdall.dto.HistoryDTO;

import montclio.heimdall.model.HistoryMoto;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record GetHistoryDTO(
        Long idHistorico,
        Long idMoto,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAtividade,
        String atividade
) {
    public GetHistoryDTO(HistoryMoto h) {
        this(
            h.getIdHistorico(),
            h.getIdMoto(),
            h.getDataAtividade(),
            h.getAtividade()
        );
    }
}
