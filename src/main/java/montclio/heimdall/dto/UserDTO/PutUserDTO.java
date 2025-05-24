package montclio.heimdall.dto.UserDTO;



import java.time.LocalDate;

public record PutUserDTO(
        String name,
        String middleName,
        LocalDate birthday,
        String cpf,
        String email,
        String password) {
}
