package montclio.heimdall.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import montclio.heimdall.dto.PostTagRfidDTO;
import montclio.heimdall.dto.PutTagRfidDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_HDL_TAG_IDENTIFICACAO")
public class TagRfId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String frequencia;
    private String banda;
    private String aplicacao;
    @JoinColumn(name = "motorcycle_id", referencedColumnName = "id", unique = true)
    private Motorcycle motorcycle;

    // Construtor que recebe um DTO para criação
    public TagRfId(PostTagRfidDTO dto) {
        this.id = dto.id();
        this.frequencia = dto.frequencia();
        this.banda = dto.banda();
        this.aplicacao = dto.aplicacao();

        if (dto.motorcycle() != null) {
            this.motorcycle = dto.motorcycle();
            this.motorcycle.setTag(this);
        }
    }

    // Método para atualizar os dados baseado em um DTO de update
    public void updateData(PutTagRfidDTO dto) {
        if (dto.frequencia() != null) {
            this.frequencia = dto.frequencia();
        }
        if (dto.banda() != null) {
            this.banda = dto.banda();
        }
        if (dto.aplicacao() != null) {
            this.aplicacao = dto.aplicacao();
        }
        if (dto.motorcycle() != null) {
            this.motorcycle = dto.motorcycle();
            this.motorcycle.setTag(this);
        }
    }

}
