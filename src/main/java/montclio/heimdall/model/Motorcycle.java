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
    @NotBlank
    private String plate;
    @NotBlank
    private String chassiNumber;
    private MotorcycleType motorcycleType;

    public Motorcycle(PostMotorcycleDTO motorcycleDTO) {
        this.id = motorcycleDTO.id();
        this.motorcycleType = motorcycleDTO.motorcycleType();
        this.chassiNumber = motorcycleDTO.chassiNumber();
        this.plate = motorcycleDTO.plate();
    }


    public void updateData(PutMotorcycleDTO dto) {
        if (dto.plate() != null) {
            this.plate = dto.plate();
        }
        if (dto.motorcycleType() != null) {
            this.motorcycleType = dto.motorcycleType();
        }
    }
}
