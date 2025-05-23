package montclio.heimdall.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import montclio.heimdall.dto.PostUserDTO;
import montclio.heimdall.dto.PutUserDTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_HDL_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String middleName;
    private LocalDate birthday;
    private String cpf;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private UserCategory userCategory;


    public User(PostUserDTO dto) {
        this.name = dto.name();
        this.middleName = dto.middleName();
        this.birthday = dto.birthday();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.password = dto.password();
    }

    public void updateData(PutUserDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.middleName() != null) {
            this.middleName = dto.middleName();
        }
        if (dto.birthday() != null) {
            this.birthday = dto.birthday();
        }
        if (dto.cpf() != null) {
            this.cpf = dto.cpf();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.password() != null) {
            this.password = dto.password();
        }
    }
}
