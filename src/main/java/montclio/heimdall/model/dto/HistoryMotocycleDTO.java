package montclio.heimdall.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryMotocycleDTO {
    private Long idHistoryMotocycle;
    private Date dateActivity;
    private String activity;

}
