package montclio.heimdall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagIdDTO {
    private Long idTagId;
    private String frequencyBands;
    private String band;
    private float range;
    private String aplication;

}
