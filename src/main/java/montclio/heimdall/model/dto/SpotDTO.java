package montclio.heimdall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotDTO {
    private Long idSpot;
    private String CodSpot;
    private Boolean isFilled;

}
