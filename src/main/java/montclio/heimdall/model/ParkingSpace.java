package montclio.heimdall.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import montclio.heimdall.dto.ParkingSpaceDTO.PostParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PutParkingSpaceDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_HDL_VAGA")
public class ParkingSpace {

    @Id
    @Column(name = "id_vaga")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vaga")
    @SequenceGenerator(name = "seq_vaga", sequenceName = "SEQ_TB_HDL_VAGA", allocationSize = 1)
    private Long idVaga;

    @Column(name = "id_zona", nullable = false)
    private Long idZona;

    @Column(name = "preenchida", nullable = false)
    private Integer preenchida;

    @Column(name = "cod_vaga", length = 10)
    private String codVaga;

    public ParkingSpace(PostParkingSpaceDTO dto) {
        this.idVaga = dto.id();
        this.idZona = dto.idZona();
        this.codVaga = dto.codVaga();
        this.preenchida = dto.preenchida() != null && dto.preenchida() ? 1 : 0;
    }

    public void updateData(PutParkingSpaceDTO dto) {
        if (dto.idZona() != null) {
            this.idZona = dto.idZona();
        }
        if (dto.codVaga() != null) {
            this.codVaga = dto.codVaga();
        }
        if (dto.preenchida() != null) {
            this.preenchida = dto.preenchida() ? 1 : 0;
        }
    }

    public boolean isPreenchida() {
        return this.preenchida != null && this.preenchida == 1;
    }
    
    public Long getIdVaga() {
        return this.idVaga;
    }
}
