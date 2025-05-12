package montclio.heimdall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotocycleDTO {
    private Long idMotocycle;
    private String plate;
    private String chassiNumber;

}
