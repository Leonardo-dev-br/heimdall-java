package montclio.heimdall.dto;

import montclio.heimdall.model.UserCategory;

public record UserFilter(
        String name,
        String middleName,
        String cpf,
        String email,
        Long userCategoryId
) {
}
