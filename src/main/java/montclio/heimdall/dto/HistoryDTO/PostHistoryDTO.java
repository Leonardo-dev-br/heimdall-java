package montclio.heimdall.dto.HistoryDTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;


public record PostHistoryDTO(
        Long id,
        Long idMoto,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAtividade,
        String atividade
) {

}
