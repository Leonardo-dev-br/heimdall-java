package montclio.heimdall.dto;

import java.time.LocalDate;

public record GetUserDTO(
        Long id,
        String name,
        String middleName,
        LocalDate birthday,
        String cpf,
        String email,
        Long category
) {
    public GetUserDTO(montclio.heimdall.model.User user) {
        this(
                user.getId(),
                user.getName(),
                user.getMiddleName(),
                user.getBirthday(),
                user.getCpf(),
                user.getEmail(),
                user.getUserCategory() != null ? user.getUserCategory().getId() : null
        );
    }
}
