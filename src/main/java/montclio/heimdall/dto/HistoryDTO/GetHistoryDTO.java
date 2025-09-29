package montclio.heimdall.dto.HistoryDTO;

import montclio.heimdall.model.HistoryMoto;

import java.time.LocalDate;

public record GetHistoryDTO(
        Long idHistorico, Long idMoto, java.time.LocalDate dataAtividade, String atividade
) {
    public GetHistoryDTO(HistoryMoto h) {
        this(h.getIdHistorico(), h.getIdMoto(), h.getDataAtividade(), h.getAtividade());
    }
}
