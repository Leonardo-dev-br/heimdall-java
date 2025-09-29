package montclio.heimdall.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import montclio.heimdall.dto.HistoryDTO.PostHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.PutHistoryDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_HDL_HISTORICO_MOTO")
public class HistoryMoto {

    @Id
    @Column(name = "id_historico")
    private Long idHistorico;

    @Column(name = "id_moto", nullable = false)
    private Long idMoto;

    @Column(name = "data_atividade")
    private LocalDate dataAtividade;

    @Column(name = "atividade", length = 100)
    private String atividade;

    public HistoryMoto(PostHistoryDTO dto) {
        this.idHistorico = dto.id();
        this.idMoto = dto.idMoto();
        this.dataAtividade = dto.dataAtividade();
        this.atividade = dto.atividade();
    }

    public void updateData(PutHistoryDTO dto) {
        if (dto.dataAtividade() != null) {
            this.dataAtividade = dto.dataAtividade();
        }
        if (dto.atividade() != null) {
            this.atividade = dto.atividade();
        }
        if (dto.idMoto() != null) {
            this.idMoto = dto.idMoto();
        }
    }
}
