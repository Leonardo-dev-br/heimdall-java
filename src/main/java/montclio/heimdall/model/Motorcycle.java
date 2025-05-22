package montclio.heimdall.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import montclio.heimdall.dto.PostMotorcycleDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_HDL_MOTO")
public class Motorcycle {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    private String chassiNumber;
    private MotorcycleType motorcycleType;
    @OneToOne(mappedBy = "motorcycle")
    private TagRfId tag;

    public Motorcycle(PostMotorcycleDTO motorcycleDTO) {
        this.id = motorcycleDTO.id();
        this.motorcycleType = motorcycleDTO.motorcycleType();
        this.chassiNumber = motorcycleDTO.chassiNumber();
        this.plate = motorcycleDTO.plate();

        if (motorcycleDTO.tagId() != null) {
            TagRfId tag = new TagRfId();
            tag.setId(motorcycleDTO.tagId());
            tag.setMotorcycle(this); // Define o vínculo 1:1
            this.tag = tag;
        }
    }



    public void updateData(PutMotorcycleDTO dto) {
        if (dto.plate() != null) {
            this.plate = dto.plate();
        }
        if (dto.motorcycleType() != null) {
            this.motorcycleType = dto.motorcycleType();
        }
        if (dto.tagId() != null) {
            if (this.tag == null) {
                this.tag = new TagRfId();
            }
            this.tag.setId(dto.tagId());
            this.tag.setMotorcycle(this);
        }
    }
}
