package montclio.heimdall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCategoryDTO {
    private Long idUserCategory;    
    private String category;
}
