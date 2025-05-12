package montclio.heimdall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeMotocycleDTO {
    private Long idTypeMotocycle;
    private String modelMotocycle;
    private Boolean isCombustion;

}
