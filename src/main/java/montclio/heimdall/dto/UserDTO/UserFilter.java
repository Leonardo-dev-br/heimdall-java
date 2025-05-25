package montclio.heimdall.dto.UserDTO;

public record UserFilter(
        String name,
        String middleName,
        String cpf,
        String email,
        Long userCategoryId
) {
}
