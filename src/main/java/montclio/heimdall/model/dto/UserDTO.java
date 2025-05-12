package montclio.heimdall.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long idUser;
    private String Username;
    private String lastName;
    private Date birthDate;
    private int cpf;
    private String email;
    private String password;

}
